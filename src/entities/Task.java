package entities;

public class Task {
    private int id;
    private String taskName;
    private String createdAt;
    private String finishAt;
    private int idProject;
    private int idUser;
    private Boolean status;

    public Task(){
        this.id = 0;
    }
    public Task(int id,String name, Boolean status, String createdAt, String finishAt, int idProject, int idUser){
        this.id = id;
        setTaskName(name);
        this.status = status;
        this.createdAt = createdAt;
        this.finishAt = finishAt;
        this.idProject = idProject;
        this.idUser = idUser;
    }
    public void setTaskName(String taskName){
        if(taskName.isEmpty()){
            throw new NullPointerException("taskName is null");
        }
        this.taskName = taskName;
    }

    public String getName(){
        return this.taskName;
    }
    public int getId(){
        return this.id;
    }

    public Boolean getStatus(){
        return  this.status;
    }
    public void setStatus(Boolean status){
        this.status = status;
    }


    public String toString(){
        return "Id: " + id + "\ntaskName: " + taskName;
    }
}
