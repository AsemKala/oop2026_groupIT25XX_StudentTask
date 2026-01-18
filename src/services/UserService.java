package services;


import com.sun.jdi.request.DuplicateRequestException;
import data.interfaces.IUserRepository;
import entities.User;
import exceptions.DuplicateEmailException;
import exceptions.UserNotFoundException;

import java.util.List;

public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void creteUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null || user.getName().isBlank()) {
            throw new DuplicateEmailException("User with email " + user.getEmail() + " already exists");
        }

        userRepository.create(user);
    }

    public User getUserById(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        return user;
    }

    public User getUserByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
