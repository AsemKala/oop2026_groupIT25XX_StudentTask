package repositories;

import com.sun.jdi.request.DuplicateRequestException;
import data.interfaces.IDB;
import data.interfaces.ITaskRepository;
import entities.Task;
import exceptions.TaskNotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class TaskRepository implements ITaskRepository {
    private IDB database;

    public TaskRepository(IDB database) {
        this.database = database;
    }

    public TaskRepository(String name, String finishAt, int idProject, int userId) {
    }
    @Override
    public Task AddTask(String name, String finish_at, int id_project, int user_id) {

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
    public Task ChangeStatus(int id, boolean status) {
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
    public Task FindTaskById(int id) {
        try (Connection conn = database.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * from Tasks");
            if (res.next()) {
                int idTask = res.getInt("id");
                String taskname = res.getString("taskname");
                String finish_at = res.getString("finish_at");
                String created_at = res.getString("created_at");
                int idProject = res.getInt("id_project");
                int idUser = res.getInt("id_user");
                boolean status = res.getBoolean("status_at");
                return new Task(idTask, taskname, status, created_at, finish_at, idProject, idUser);
            }

            return null;
        }
        catch (Exception e) {
            throw new TaskNotFoundException("Task Not Found");
        }
    }
}
