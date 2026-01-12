package entities;

public class User {
    private int id;
    private int idGen;
    private String name;
    private String email;


    public User(String name, String email){
        id = idGen;
        idGen++;
        setName(name);
        setEmail(email);
    }

    public void setName(String name){
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name can't be null");
        }
        this.name = name;
    }
    public void setEmail(String email){
        if(email.isEmpty()){
            throw new IllegalArgumentException("Name can't be null");
        }
        this.email = email;
    }
    public String getName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }
    public String getEmail(){
        return this.email;
    }




    public String toString(){
        return "Id: " + id + "\nName: " + name + "\nEmail: " + email;
    }
}
