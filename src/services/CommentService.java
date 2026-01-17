package services;

import data.interfaces.ITaskRepository;
import data.interfaces.IUserRepository;
import entities.Comment;
import entities.Task;
import entities.User;
import exceptions.DeadlineInPastException;
import exceptions.TaskNotFoundException;
import exceptions.UserNotFoundException;
import repositories.CommentRepository;
import repositories.UserRepository;

import java.util.List;

public class CommentService {
    private IUserRepository userRepository;
    private ITaskRepository taskRepository;
    private CommentRepository commentRepository;

    public void createComment(Comment comment) {
        User user = userRepository.findById(comment.getUserId());
        if (user == null) {
            throw new UserNotFoundException("User with ID " + comment.getUserId() + " not found");
        }

        Task task = taskRepository.findById(comment.getTaskId());
        if (task == null) {
            throw new TaskNotFoundException("Task with ID " + comment.getTaskId() + " not found");
        }

        commentRepository.create(comment);
    }

    public List<Comment> getCommentsByTask(int taskId) {
        Task task = taskRepository.findById(taskId);
        if (task == null) {
            throw new TaskNotFoundException("Task with ID " + taskId + " not found");
        }

        return commentRepository.findByTaskId(taskId);
    }
}
