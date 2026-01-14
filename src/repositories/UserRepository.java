package repositories;

import data.interfaces.IDB;
import data.interfaces.IUserRepository;
import entities.User;
import exceptions.DatabaseOperationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB database;

    public UserRepository(IDB database) {
        this.database = database;
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users (name, email, \"group\") VALUES (?, ?, ?)";

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getGroup());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create user", e);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT id, name, email, \"group\" FROM users";

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("group"));
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get all users", e);
        }
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT id, name, email, \"group\" FROM users WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id); // Use setInt, not setString
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("group"));
            }

            return null;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get user", e);
        }
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT id, name, email, \"group\" FROM users WHERE email = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("group"));
            }

            return null;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get user", e);
        }
    }
}
