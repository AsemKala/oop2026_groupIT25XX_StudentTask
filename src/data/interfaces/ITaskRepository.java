package data.interfaces;

import entities.Task;

public interface ITaskRepository {
    Task findById(int id);
    void create(Task task);
    void changeStatus(Task task);

}