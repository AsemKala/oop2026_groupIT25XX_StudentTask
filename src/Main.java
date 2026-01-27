import data.interfaces.IDBPool;
import data.interfaces.IUserRepository;
import entities.User;
import repositories.SuperbaseDB;
import repositories.UserRepository;
import services.UserService;

import java.time.format.DateTimeFormatter;

public class Main {
    static void main(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu");

        User myUser = new User();
        myUser.setName("Azamat");
        myUser.setGroup("IT-2508");
        myUser.setEmail("azamat@example.com");

        IUserRepository userRepo = new UserRepository();
        UserService userService = new UserService(userRepo);

        userService.creteUser(myUser);

    }
}
