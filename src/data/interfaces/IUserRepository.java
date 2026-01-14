package data.interfaces;

import entities.User;

import java.util.List;

public interface IUserRepository {
    void create (User user);
    List<User> findAll();
    User findById(int id);
    User findByEmail(String email);
}
