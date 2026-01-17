package data.interfaces;

import entities.Task;

import java.util.List;

public interface ITaskRepository {
    Task FindTaskById(int id);
    Task AddTask(String name, String finish_at, int id_project, int user_id);
    Task ChangeStatus(int id, boolean status);

}
