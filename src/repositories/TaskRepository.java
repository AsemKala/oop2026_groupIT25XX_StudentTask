package repositories;

import com.sun.jdi.request.DuplicateRequestException;
import data.interfaces.IDB;
import data.interfaces.ITaskRepository;
import entities.Task;
import exceptions.TaskNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class TaskRepository implements ITaskRepository {
    private IDB database;

    public TaskRepository(IDB database) {
        this.database = database;
    }

    @Override
    public Task add(String name, String finish_at, int id_project, int user_id) {

        try (Connection conn = database.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks");
            while (resultSet.next()) {
                if (!resultSet.getString(name).isEmpty()) {
                    throw new DuplicateRequestException("This task already exists");
                } else {
                    int row = statement.executeUpdate("insert Tasks(name, finish_at, id_project, id_user) VALUES (?,?,?,?)");
                    System.out.println("Task was added");
                }
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error 505");
        }
    }
    @Override
    public Task changeStatus(int id, boolean status) {
        try (Connection conn = database.getConnection()) {
            Statement statement = conn.createStatement();
            int rows = statement.executeUpdate("Update Tasks SET status = status where id = id");
            System.out.println("Status was changed");
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error");
        }

    }

    @Override
    public Task findById(int id) {
        String sql = "SELECT * from tasks WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
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

            return null;
        }
        catch (Exception e) {
            throw new TaskNotFoundException("Task Not Found");
        }
    }
}