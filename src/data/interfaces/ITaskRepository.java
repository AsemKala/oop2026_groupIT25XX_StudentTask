package data.interfaces;

import entities.Task;

public interface ITaskRepository {
    Task findById(int id);
    Task add(String name, String finish_at, int id_project, int user_id);
    Task changeStatus(int id, boolean status);

}