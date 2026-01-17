package entities;

public class Project {
    private int id;
    private static int idGen;
    private String name;

    public Project(){
        this.id = 0;
    }
    public Project(int id, String name){
        this.id = id;
        setName(name);
    }
    public void setName(String name){
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name can't be null");
        }
        this.name = name;
    }



    public String toString(){
        return "Id: " + id + "\nName: " + name;
    }
}

