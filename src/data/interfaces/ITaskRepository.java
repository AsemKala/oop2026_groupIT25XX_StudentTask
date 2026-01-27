package data.interfaces;

import entities.Task;

public interface ITaskRepository extends IRepository<Task> {
    void changeStatus(Task task);
}