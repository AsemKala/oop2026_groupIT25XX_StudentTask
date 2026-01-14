import data.interfaces.IDB;
import data.interfaces.IUserRepository;
import entities.User;
import exceptions.DuplicateEmailException;
import repositories.SuperbaseDB;
import repositories.UserRepository;
import services.UserService;

public class Main {
    public static void main(String[] args){

        IDB database = new SuperbaseDB("jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres?sslmode=require",
                                                "postgres.jrosmhhjxqglqwbovgle",
                                            "Q2Fnxn3X1Oy2Gied");

        IUserRepository userRepo = new UserRepository(database);
        UserService userService = new UserService(userRepo);
        try {
            User user = new User();
            user.setName("Zangar");
            user.setEmail("ernur@example.com");
            user.setGroup("IT-2508");
            userService.creteUser(user);
            System.out.println("Created successfully!");
        } catch (DuplicateEmailException e) {
            System.out.println("Failed to create user: " + e.getMessage());
        }

    }
}
