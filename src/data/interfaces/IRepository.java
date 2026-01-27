package data.interfaces;

import java.util.List;

public interface IRepository<T> {
    void create(T entity);
    T findById(int id);
    List<T> findAll();
}
