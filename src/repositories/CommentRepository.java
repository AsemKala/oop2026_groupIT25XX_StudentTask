package repositories;

import data.interfaces.ICommentRepository;
import data.interfaces.IDB;
import entities.Comment;
import entities.Project;
import entities.User;
import exceptions.DatabaseOperationException;
import exceptions.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository implements ICommentRepository {
    private final IDB database;
    
    public CommentRepository(IDB database) {
        if (database == null) {
            throw new IllegalArgumentException("Database cannot be null");
        }

        this.database = database;
    }

    @Override
    public void create(Comment comment) {
        String sql = "INSERT INTO comments (content, task_id, user_id) VALUES (?, ?, ?)";

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, comment.getContent());
            statement.setInt(2, comment.getTaskId());
            statement.setInt(3, comment.getUserId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        comment.setId(generatedKeys.getInt("id"));
                        comment.setCreatedAt(generatedKeys.getString("created_at"));
                    }
                }
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create comment: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Comment> findByTaskId(int taskId) {
        String sql = "SELECT id, content, created_at, task_id, user_id FROM comments WHERE task_id = ?";
        List<Comment> comments = new ArrayList<>();

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, taskId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Comment comment = new Comment(
                            rs.getInt("id"),
                            rs.getString("content"),
                            rs.getString("created_at"),
                            rs.getInt("task_id"),
                            rs.getInt("user_id")
                    );
                    comments.add(comment);
                }
            }

            return comments;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to find comments by task: " + e.getMessage(), e);
        }
    }
}
