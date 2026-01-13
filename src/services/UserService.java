package services;

import entities.User;
import java.util.ArrayList;

public class UserService {
    ArrayList<User> users = new ArrayList<>();
    public void addUser(String name, String email){
        User user = new User(name, email);
        users.add(user);
        System.out.println("User was added");
    }

    public void findUser(String name){
        for (User user: users){
            if(user.getName().equals(name)){
                System.out.println(user);
                break;
            }
        }
    }

}
