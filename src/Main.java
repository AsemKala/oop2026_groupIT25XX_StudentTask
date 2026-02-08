import data.interfaces.IDBPool;
import data.interfaces.IProjectRepository;
import data.interfaces.IUserRepository;
import entities.User;
import repositories.ProjectRepository;
import repositories.SuperbaseDB;
import repositories.UserRepository;
import services.ProjectBuilder;
import services.ProjectService;
import services.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    static void main(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu");

//        User myUser = new User();
//        myUser.setName("Azamat");
//        myUser.setGroup("IT-2508");
//        myUser.setEmail("azamat@example.com");

        IUserRepository userRepo = new UserRepository(SuperbaseDB.getInstance());
        UserService userService = new UserService(userRepo);

        IProjectRepository projectRepo = new ProjectRepository(SuperbaseDB.getInstance());

//        userService.creteUser(myUser);

        // Builder
        ProjectBuilder projectBuilder = new ProjectBuilder()
                .setName("Good")
                .setDeadline(LocalDate.parse("01/02/2029", formatter))
                .setDescription("Cool")
                .setOwnerId(1);
        ProjectService projectService = new ProjectService(projectRepo, userRepo);

        projectService.createProject(projectBuilder.build());


        System.out.print("Added successfully");

    }
}
