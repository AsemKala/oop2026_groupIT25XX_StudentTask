package entities;

public class Project {
    private int id;
    private static int idGen;
    private String name;


    public Project(String name){
        id = idGen;
        idGen++;
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

