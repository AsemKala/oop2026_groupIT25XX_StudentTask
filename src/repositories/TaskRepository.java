package repositories;

import data.interfaces.IDBPool;
import data.interfaces.ITaskRepository;
import entities.Task;
import exceptions.DatabaseOperationException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class TaskRepository implements ITaskRepository {
    private final IDBPool databasePool = SuperbaseDB.getInstance();

    @Override
    public void create(Task task) {
        String sql = "INSERT INTO tasks (name, finish_at, id_project, id_user) VALUES (?,?,?,?)";
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, task.getName());
            statement.setDate(2, java.sql.Date.valueOf(task.getFinishAt()));
            statement.setInt(3, task.getIdProject());
            statement.setInt(4, task.getIdUser());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        task.setId(generatedKeys.getInt("id"));
                        task.setStatus(generatedKeys.getBoolean("status"));
                        task.setCreatedAt(generatedKeys.getDate("created_at").toLocalDate());
                    }
                }
            }

            statement.close();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create task: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                databasePool.releaseConnection(conn);
            }
        }
    }
    @Override
    public void changeStatus(Task task) {
        String sql = "UPDATE tasks SET status = ? WHERE id = ?";
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            boolean newStatus = !task.getStatus();
            statement.setBoolean(1, newStatus);
            statement.setInt(2, task.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                task.setStatus(newStatus);
            }

            finish(task);

            statement.close();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to change task status: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                databasePool.releaseConnection(conn);
            }
        }
    }

    public Task findById(int id) {
        String sql = "SELECT * from tasks WHERE id = ?";
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);

            try (ResultSet res = statement.executeQuery()) {
                if (res.next()) {
                    int idTask = res.getInt("id");
                    String taskName = res.getString("name");
                    LocalDate finish_at = res.getDate("finish_at").toLocalDate();
                    LocalDate created_at = res.getDate("created_at").toLocalDate();
                    int idProject = res.getInt("id_project");
                    int idUser = res.getInt("id_user");
                    boolean status = res.getBoolean("status");
                    return new Task(idTask, taskName, status, created_at, finish_at, idProject, idUser);
                }
            }

            statement.close();

            return null;

        }
        catch (SQLException e) {
            throw new DatabaseOperationException("Failed to find task by ID: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                databasePool.releaseConnection(conn);
            }
        }
    }

    @Override
    public List<Task> findAll() {
        String sql = "SELECT id, content, created_at, task_id, user_id FROM tasks";
        List<Task> tasks = new ArrayList<>();
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("status"),
                        rs.getDate("created_at").toLocalDate(),
                        rs.getDate("finish_at").toLocalDate(),
                        rs.getInt("id_project"),
                        rs.getInt("id_user")
                );
                tasks.add(task);
            }

            statement.close();

            return tasks;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get all tasks: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                databasePool.releaseConnection(conn);
            }
        }
    }
//    public List<Task> getAll(){
//        return tasks;
//    }

    private void finish(Task task) {
        String sql = "UPDATE tasks SET finish_at = CURRENT_DATE WHERE id = ?";
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, task.getId());

            int affected_rows = statement.executeUpdate();

            if (affected_rows > 0) {
                task.setFinishAt(LocalDate.now());
            }

            statement.close();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update finished date: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                databasePool.releaseConnection(conn);
            }
        }
    }
}