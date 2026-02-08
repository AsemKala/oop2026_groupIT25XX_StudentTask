import data.interfaces.*;
import entities.Comment;
import entities.Project;
import entities.Task;
import entities.User;
import repositories.*;
import services.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static void main(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu");
        Scanner scanner = new Scanner(System.in);

        IUserRepository userRepo = new UserRepository(SuperbaseDB.getInstance());
        UserService userService = new UserService(userRepo);
        IProjectRepository projectRepo = new ProjectRepository(SuperbaseDB.getInstance());
        ProjectService projectService = new ProjectService(projectRepo, userRepo);
        ProjectBuilder projectBuilder = new ProjectBuilder();
        ITaskRepository taskRepo = new TaskRepository(SuperbaseDB.getInstance());
        TaskService taskService = new TaskService(taskRepo, userRepo, projectRepo);
        ICommentRepository commentRepo = new CommentRepository(SuperbaseDB.getInstance());
        CommentService commentService = new CommentService(userRepo, taskRepo, commentRepo);

        System.out.println(userService.getAllUsers());
        System.out.println("Choose user by id:");

        User currentUser = userService.getUserById(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Current user:" + currentUser);

        System.out.println("Creating new project");
        System.out.println("Project name:");
        String projectName = scanner.nextLine();
        System.out.println("Project deadline in MM/dd/yyyy:");
        LocalDate projectDeadline = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.println("Description:");
        String projectDescription = scanner.nextLine();
        System.out.println("Creating");
        projectBuilder.setName(projectName)
                .setDeadline(projectDeadline)
                .setDescription(projectDescription)
                .setOwnerId(currentUser.getId());
        Project currentProject = projectBuilder.build();
        projectService.createProject(currentProject);
        System.out.print("Added successfully:");
        System.out.println(projectService.getProjectById(currentProject.getId()));

        System.out.println("Adding new Task");
        System.out.println("Task type BUG / FEATURE / RESEARCH:");
        String taskType = scanner.nextLine().toLowerCase(Locale.ROOT);
        System.out.println("Task name:");
        String taskName = scanner.nextLine();
        System.out.println("Creating");
        Task currentTask = TaskFactory.createTask(taskType, taskName, false, null);
        taskService.createTask(currentTask);
        System.out.print("Added successfully:");
        System.out.println(taskService.getTaskById(currentTask.getId()));

        System.out.println("Adding new Comment");
        System.out.println("Comment content:");
        String commentContent = scanner.nextLine();
        System.out.println("Creating");
        Comment currentComment = new Comment(commentContent, currentTask.getId(), currentUser.getId());
        commentService.createComment(currentComment);
        System.out.print("Added successfully:");
        System.out.println(commentService.getCommentsByTask(currentTask.getId()));

        System.out.println("Change task status[y/n]?");
        if (Objects.equals(scanner.nextLine(), "y")) {
            taskService.changeStatus(currentTask);
            System.out.println("Changed for" + currentTask + ": " + currentTask.getStatus());
        }

        System.out.println("Show Overdue Projects[y/n]?");
        if (Objects.equals(scanner.nextLine(), "y")) {
            System.out.println(projectService.getOverdueProjects());
        }

        System.out.println("Show Projects Sorted By Deadline[y/n]?");
        if (Objects.equals(scanner.nextLine(), "y")) {
            System.out.println(projectService.getProjectsSortedByDeadline());
        }
        System.out.println("Ending...");
    }
}
