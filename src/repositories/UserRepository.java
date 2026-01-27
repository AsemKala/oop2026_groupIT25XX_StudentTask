package repositories;

import data.interfaces.IDBPool;
import data.interfaces.IUserRepository;
import entities.User;
import exceptions.DatabaseOperationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDBPool databasePool= SuperbaseDB.getInstance();

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users (name, email, \"group\") VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getGroup());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt("id"));
                    }
                }
            }

            statement.close();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create user", e);
        } finally {
            if (conn == null) {
                databasePool.releaseConnection(conn);
            }
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT id, name, email, \"group\" FROM users";
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("group"));
                users.add(user);
            }

            statement.close();

            return users;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get all users", e);
        } finally {
            if (conn == null) {
                databasePool.releaseConnection(conn);
            }
        }
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT id, name, email, \"group\" FROM users WHERE id = ?";
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("group"));
            }

            statement.close();

            return null;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get user", e);
        } finally {
            if (conn == null) {
                databasePool.releaseConnection(conn);
            }
        }
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT id, name, email, \"group\" FROM users WHERE email = ?";
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("group"));
            }

            statement.close();

            return null;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get user", e);
        } finally {
            if (conn == null) {
                databasePool.releaseConnection(conn);
            }
        }
    }
}
