package repositories;

import data.interfaces.ICommentRepository;
import data.interfaces.IDBPool;
import entities.Comment;
import exceptions.DatabaseOperationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository implements ICommentRepository {
    private final IDBPool databasePool = SuperbaseDB.getInstance();

    @Override
    public void create(Comment comment) {
        String sql = "INSERT INTO comments (content, task_id, user_id) VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, comment.getContent());
            statement.setInt(2, comment.getTaskId());
            statement.setInt(3, comment.getUserId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        comment.setId(generatedKeys.getInt("id"));
                        comment.setCreatedAt(generatedKeys.getDate("created_at").toLocalDate());
                    }
                }
            }

            statement.close();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create comment: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                databasePool.releaseConnection(conn);
            }
        }
    }

    @Override
    public Comment findById(int id) {
        String sql = "SELECT id, content, created_at, task_id, user_id FROM comments WHERE id = ?";
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Comment(
                            rs.getInt("id"),
                            rs.getString("content"),
                            rs.getDate("created_at").toLocalDate(),
                            rs.getInt("task_id"),
                            rs.getInt("user_id")
                    );
                }
            }

            statement.close();

            return null;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to find comment by ID: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                databasePool.releaseConnection(conn);
            }
        }
    }

    @Override
    public List<Comment> findByTaskId(int taskId) {
        String sql = "SELECT id, content, created_at, task_id, user_id FROM comments WHERE task_id = ?";
        List<Comment> comments = new ArrayList<>();
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, taskId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Comment comment = new Comment(
                            rs.getInt("id"),
                            rs.getString("content"),
                            rs.getDate("created_at").toLocalDate(),
                            rs.getInt("task_id"),
                            rs.getInt("user_id")
                    );
                    comments.add(comment);
                }
            }

            statement.close();

            return comments;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to find comments by task: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                databasePool.releaseConnection(conn);
            }
        }
    }

    @Override
    public List<Comment> findAll() {
        String sql = "SELECT id, content, created_at, task_id, user_id FROM comments";
        List<Comment> comments = new ArrayList<>();
        Connection conn = null;

        try {
            conn = databasePool.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getDate("created_at").toLocalDate(),
                        rs.getInt("task_id"),
                        rs.getInt("user_id")
                );
                comments.add(comment);
            }

            statement.close();

            return comments;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get all comments: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                databasePool.releaseConnection(conn);
            }
        }
    }
}
