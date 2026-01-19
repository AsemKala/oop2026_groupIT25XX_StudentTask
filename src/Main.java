import data.interfaces.IDB;
import data.interfaces.IProjectRepository;
import data.interfaces.IUserRepository;
import entities.Project;
import entities.User;
import exceptions.DuplicateEmailException;
import repositories.ProjectRepository;
import repositories.SuperbaseDB;
import repositories.UserRepository;
import services.ProjectService;
import services.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu");

        IDB database = new SuperbaseDB("jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres?sslmode=require",
                                                "postgres.jrosmhhjxqglqwbovgle",
                                            "Q2Fnxn3X1Oy2Gied");

        IUserRepository userRepo = new UserRepository(database);
        UserService userService = new UserService(userRepo);

        IProjectRepository projectRepo = new ProjectRepository(database);
        ProjectService projectService = new ProjectService(projectRepo, userRepo);

        try {
//            User user = new User();
//            user.setName("Zangar");
//            user.setEmail("zangar@example.com");
//            user.setGroup("IT-2508");
//            userService.creteUser(user);
//            System.out.println("Created successfully!");

            Project testProject = new Project("Music App",
                                            "App for listening and loading music",
                                            LocalDate.parse("12/12/2026", formatter),
                                            1);
            projectService.createProject(testProject);
            System.out.println("Created successfully!");
            System.out.println(projectService.getProjectById(testProject.getId()));
            System.out.println(projectService.getAllProjects());
            System.out.println(projectService.getProjectsByOwner(testProject.getOwnerId()));

        } catch (DuplicateEmailException e) {
            System.out.println("Failed to create user: " + e.getMessage());
        }

    }
}
