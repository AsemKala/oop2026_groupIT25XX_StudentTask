package services;

import data.interfaces.IProjectRepository;
import data.interfaces.ITaskRepository;
import data.interfaces.IUserRepository;
import entities.Project;
import entities.Task;
import entities.User;
import exceptions.ProjectNotFoundException;
import exceptions.TaskAlreadyCompletedException;
import exceptions.TaskNotFoundException;
import exceptions.UserNotFoundException;

public class TaskService {
    private final ITaskRepository taskRepository;
    private final IUserRepository userRepository;
    private final IProjectRepository projectRepository;

    public TaskService(ITaskRepository taskRepository, IUserRepository userRepository, IProjectRepository projectRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public void createTask(Task task) {
        User accountable = userRepository.findById(task.getIdUser());
        if (accountable == null) {
            throw new UserNotFoundException("Accountable user with ID " + task.getIdUser() + " not found");
        }

        Project assignedProject = projectRepository.findById(task.getIdProject());
        if (assignedProject == null) {
            throw new ProjectNotFoundException("Assigned project with ID " + task.getIdProject() + " not found");
        }

        taskRepository.create(task);
    }

    public Task getTaskById(int id) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException("Task with ID " + id + " not found");
        }

        return task;
    }

    public void changeStatus(Task task) {
        boolean currentStatus = task.getStatus();
        if (currentStatus) {
            throw new TaskAlreadyCompletedException("Task is already completed");
        }

        taskRepository.changeStatus(task);
    }
}