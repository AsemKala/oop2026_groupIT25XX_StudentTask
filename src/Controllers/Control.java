package Controllers;
import entities.Comment;
import entities.Project;
import entities.Task;
import entities.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Control {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<Project> projects = new ArrayList<>();
    ArrayList<Comment> comments = new ArrayList<>();



    public Control(){
        run();
    }

    public void run(){
        boolean work = true;
        while (work){
            System.out.println(
                    "1.Add Task\n" +
                    "2.Create Project\n" +
                    "3.Change Task Status\n" +
                    "4.Add Comments\n" +
                    "5.Quit"
            );
            System.out.println("\nEnter number: ");
            String answer = scanner.nextLine();
            switch (answer){
                case "1":
                    AddTask();
                    break;
                case "2":
                    CreateProject();
                    break;
                case "3":
                    ChangeTaskStatus();
                    break;
                case "4":
                    AddComments();
                    break;
                case "5":
                    work = false;
                    break;
            }
        }
    }
    public void AddTask(){
        System.out.println("Enter Name Task: ");
        String name = scanner.nextLine();
        System.out.println("Enter Name Proj: ");
        String nameProj = scanner.nextLine();
        Project project = new Project(nameProj);
        tasks.add(new Task(name, project , true));
        System.out.println(project);
    }
    public void CreateProject(){
        System.out.println("Enter Name Project: ");
        String name = scanner.nextLine();
        Project project = new Project(name);
        projects.add(project);
        System.out.println(project);
    }
    public void ChangeTaskStatus(){
        // status = !status
        System.out.println("Enter name task: ");
        String nameTask = scanner.nextLine();
        for (Task task: tasks){
            if(task.getName().equals(nameTask)){
                System.out.println(task);
            }
        }
    }
    public void AddComments(){
        System.out.println("Enter Message: ");
        String message = scanner.nextLine();
        Comment comment = new Comment(message);
        comments.add(comment);
        System.out.println(comment);
    }
}

