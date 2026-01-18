package repositories;

import data.interfaces.IDB;
import data.interfaces.IProjectRepository;
import entities.Project;
import exceptions.DatabaseOperationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements IProjectRepository {
    private final IDB database;

    public ProjectRepository(IDB database) {
        this.database = database;
    }

    @Override
    public void create(Project project) {
        String sql = "INSERT INTO projects (name, description, deadline, owner_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setString(3, project.getDeadline());
            statement.setInt(4, project.getOwnerId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        project.setId(generatedKeys.getInt("id"));
                        project.setCreatedAt(generatedKeys.getString("created_at"));
                    }
                }
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create project: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Project> findAll() {
        String sql = "SELECT id, name, description, deadline, created_at, owner_id FROM projects";
        List<Project> projects = new ArrayList<>();

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Project project = new Project(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("deadline"),
                        rs.getString("created_at"),
                        rs.getInt("owner_id")
                );
                projects.add(project);
            }

            return projects;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get all projects: " + e.getMessage(), e);
        }
    }

    @Override
    public Project findById(int id) {
        String sql = "SELECT id, name, description, deadline, created_at, owner_id FROM projects WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Project(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("deadline"),
                            rs.getString("created_at"),
                            rs.getInt("owner_id")
                    );
                }
            }

            return null;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to find project by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Project> findByOwnerId(int ownerId) {
        String sql = "SELECT id, name, description, deadline, created_at, owner_id FROM projects WHERE owner_id = ?";
        List<Project> projects = new ArrayList<>();

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, ownerId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("deadline"),
                            rs.getString("created_at"),
                            rs.getInt("owner_id")
                    );
                    projects.add(project);
                }
            }

            return projects;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to find projects by owner: " + e.getMessage(), e);
        }
    }
}