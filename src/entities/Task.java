package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

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
    public Task(int id, String name, Boolean status, String createdAt, String finishAt, int idProject, int idUser){
        setId(id);
        setTaskName(name);
        setStatus(status);
        setCreatedAt(createdAt);
        setFinishAt(finishAt);
        setIdProject(idProject);
        setIdUser(idUser);
    }

    public void setId(int id) { this.id = id; }

    public void setTaskName(String taskName){
        if(taskName == null || taskName.isBlank()){
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.taskName = taskName;
    }
    
    public void setStatus(Boolean status){
        this.status = status;
    }

    public void setCreatedAt(String createdAt) {
        if (createdAt != null && !createdAt.isBlank()) {
            if (!isValidDate(createdAt)) {
                throw new IllegalArgumentException("Invalid creation date format. Use MM/dd/yyyy");
            }
            this.createdAt = createdAt;
        } else {
            this.createdAt = createdAt;
        }
    }

    public void setFinishAt(String finishAt) {
        if (finishAt != null && !finishAt.isBlank()) {
            if (!isValidDate(finishAt)) {
                throw new IllegalArgumentException("Invalid creation date format. Use MM/dd/yyyy");
            }
            this.finishAt = finishAt;
        } else {
            this.finishAt = finishAt;
        }
    }

    public void setIdProject(int idProject) {
        if (idProject <= 0) {
            throw new IllegalArgumentException("Project ID must be positive");
        }
        
        this.idProject = idProject;
    }

    public void setIdUser(int idUser) {
        if (idUser <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }
        
        this.idUser = idUser;
    }

    public String getName() { return this.taskName; }
    public int getId() { return this.id; }
    public Boolean getStatus() { return  this.status; }

    public String toString(){
        return "Id: " + id + "\ntaskName: " + taskName;
    }

    private boolean isValidDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}