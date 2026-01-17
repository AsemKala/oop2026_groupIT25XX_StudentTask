package entities;

import java.security.PublicKey;

public class User {
    private int id;
    private String name;
    private String email;
    private String group;

    public User() {
        this.id = 0;
    }


    public User(int id, String name, String email, String group) {
        this.id = id;
        setName(name);
        setEmail(email);
        setGroup(group);
    }


    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can't be blank");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Name cannot exceed 100 characters");
        }
        this.name = name;
    }

    public void setEmail(String email){
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain @");
        }
        if (email.length() > 100) {
            throw new IllegalArgumentException("Email cannot exceed 100 characters");
        }
        this.email = email;
    }

    public void setGroup(String group) {
        if (group == null || group.isBlank()) {
            throw new IllegalArgumentException("Group cannot be blank");
        }
        if (group.length() > 50) {
            throw new IllegalArgumentException("Group cannot exceed 50 characters");
        }
        this.group = group;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() { return group; }
    public String getName() { return this.name; }
    public int getId() { return this.id;}
    public String getEmail() { return this.email;}




    public String toString(){
        return "Id: " + id + "\nName: " + name + "\nEmail: " + email;
    }
}
