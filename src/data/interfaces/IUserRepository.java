package data.interfaces;

import entities.User;

import java.util.List;

public interface IUserRepository extends IRepository<User> {
    User findByEmail(String email);
}
