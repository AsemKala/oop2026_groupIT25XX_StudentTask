package entities;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Control {
    Scanner scanner = new Scanner(System.in);
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<Project> projects = new ArrayList<>();
    ArrayList<Comment> comments = new ArrayList<>();



    public Control(){
        run();
    }

    public void run(){
        boolean work = true;
        while (work){
            System.out.println("" +
                    "1.Print All Users\n" +
                    "2.Add Task\n" +
                    "3.Create Project\n" +
                    "4.Change Task Status\n" +
                    "5.Add Comments\n" +
                    "6.Quit"
            );
            System.out.println("\nEnter number: ");
            String answer = scanner.nextLine();
            switch (answer){
                case "1":
                    PrintAllUsers();
                    break;
                case "2":
                    AddTask();
                    break;
                case "3":
                    CreateProject();
                    break;
                case "4":
                    ChangeTaskStatus();
                    break;
                case "5":
                    AddComments();
                    break;
                case "6":
                    work = false;
                    break;
            }
        }
    }
    public void PrintAllUsers(){
        if(users.isEmpty()){
            return ; // break
        }
        for (User user: users){
            System.out.println(user);
        }
    }
    public void AddTask(){
        System.out.println("Enter Name Task: ");
        String name = scanner.nextLine();
        System.out.println("Enter Name Proj: ");
        String nameProj = scanner.nextLine();
        Project project = new Project(nameProj);
        tasks.add(new Task(name, project , true));
    }
    public void CreateProject(){
        System.out.println("Enter Name Project: ");
        String name = scanner.nextLine();
        Project project = new Project(name);
        projects.add(project);
    }
    public void ChangeTaskStatus(){
        // status = !status
        System.out.println("Enter name task: ");
        String nameTask = scanner.nextLine();
        for (Task task: tasks){
            if(task.getName().equals(nameTask)){
                task.ChangeStatus();
            }
        }
    }
    public void AddComments(){
        System.out.println("Enter Message: ");
        String message = scanner.nextLine();
        Comment comment = new Comment(message);
        comments.add(comment);
    }
}

//Control
//- Add Task, Create Project, Add Comments, Change Task Status



