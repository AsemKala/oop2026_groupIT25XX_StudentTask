package data.interfaces;

import entities.Comment;

import java.util.List;

public interface ICommentRepository extends IRepository<Comment> {
    List<Comment> findByTaskId(int taskId);
}
