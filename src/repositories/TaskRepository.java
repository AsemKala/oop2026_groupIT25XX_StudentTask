package repositories;

import data.interfaces.IDB;
import data.interfaces.ITaskRepository;
import entities.Task;
import exceptions.DatabaseOperationException;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TaskRepository implements ITaskRepository {
    private final IDB database;

    public TaskRepository(IDB database) {
        this.database = database;
    }

    @Override
    public void create(Task task) {
        String sql = "INSERT INTO tasks (name, finish_at, id_project, id_user) VALUES (?,?,?,?)";

        try (Connection conn = database.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, task.getName());
            statement.setString(2, task.getFinishAt());
            statement.setInt(3, task.getIdProject());
            statement.setInt(4, task.getIdUser());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        task.setId(generatedKeys.getInt("id"));
                        task.setStatus(generatedKeys.getBoolean("status"));
                        task.setCreatedAt(generatedKeys.getString("created_at"));
                    }
                }
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create task: " + e.getMessage(), e);
        }
    }
    @Override
    public void changeStatus(Task task) {
        String sql = "UPDATE tasks SET status = ? WHERE id = ?";

        try (Connection conn = database.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            boolean newStatus = !task.getStatus();
            statement.setBoolean(1, newStatus);
            statement.setInt(2, task.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                task.setStatus(newStatus);
            }

            finish(task);

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to change task status: " + e.getMessage(), e);
        }

    }

    @Override
    public Task findById(int id) {
        String sql = "SELECT * from tasks WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet res = statement.executeQuery()) {
                if (res.next()) {
                    int idTask = res.getInt("id");
                    String taskName = res.getString("name");
                    String finish_at = res.getString("finish_at");
                    String created_at = res.getString("created_at");
                    int idProject = res.getInt("id_project");
                    int idUser = res.getInt("id_user");
                    boolean status = res.getBoolean("status");
                    return new Task(idTask, taskName, status, created_at, finish_at, idProject, idUser);
                }
            }

            return null;

        }
        catch (SQLException e) {
            throw new DatabaseOperationException("Failed to find task by ID: " + e.getMessage(), e);
        }
    }

    private void finish(Task task) {
        String sql = "UPDATE tasks SET finish_at = CURRENT_DATE WHERE id = ?";

        try (Connection conn = database.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, task.getId());

            int affected_rows = statement.executeUpdate();

            if (affected_rows > 0) {
                task.setFinishAt(LocalDate.now().format(
                        DateTimeFormatter.ofPattern("MM/dd/yyyy")
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update finished date: " + e.getMessage(), e);
        }
    }
}