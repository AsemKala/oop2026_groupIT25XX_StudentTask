package entities;

public class Task {
    private int id;
    private static int idGen;
    private String taskName;
    private String link;
    private Project project;
    private String status;

    public Task(String taskName, Project project, String status){
        id = idGen;
        idGen++;
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

    public String getStatus(){
        return  this.status;
    }
    public void setStatus(String status){
        if(status == null || status.isEmpty()){
            throw new IllegalArgumentException("Project must be specified");
        }
        this.status = status;
    }


    public String toString(){
        return "Id: " + id + "\ntaskName: " + taskName;
    }
}
