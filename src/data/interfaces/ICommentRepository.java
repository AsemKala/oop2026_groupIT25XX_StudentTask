package data.interfaces;

import entities.Comment;

import java.util.List;

public interface ICommentRepository {
    void create(Comment comment);
    List<Comment> findByTaskId(int taskId);
}
