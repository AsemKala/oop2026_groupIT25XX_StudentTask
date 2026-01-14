package entities;

public class Task {
    private int id;
    private String taskName;
    private Project project;
    private Boolean status;

    public Task(String taskName, Project project, boolean status){
        this.id = id;
        setTaskName(taskName);
        setProject(project);
        this.status = status;
    }
    public void setTaskName(String taskName){
        if(taskName.isEmpty()){
            throw new IllegalArgumentException("taskName can't be null");
        }
        this.taskName = taskName;
    }

    public void setProject(Project project){
        if(project == null){
            throw new IllegalArgumentException("Project must be specified");
        }
        this.project = project;
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
