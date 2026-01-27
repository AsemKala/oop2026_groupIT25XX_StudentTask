package data.interfaces;

import entities.Task;

import java.util.List;

public interface ITaskRepository<T> {
    T findById(int id);
    void create(T task);
    void changeStatus(T task);
    List<T> getAll();

}