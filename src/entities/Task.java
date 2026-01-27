package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Task {
    private int id;
    private String name;
    private LocalDate createdAt;
    private LocalDate finishAt;
    private int idProject;
    private int idUser;
    private Boolean status;

    public Task(){
        this.id = 0;
    }

    public Task(String name, Boolean status, LocalDate finishAt){
        setId(id);
        setName(name);
        setStatus(status);
        setCreatedAt(createdAt);
        setFinishAt(finishAt);
    }
    public Task(int id, String name, Boolean status, LocalDate createdAt, LocalDate finishAt, int idProject, int idUser){
        setId(id);
        setName(name);
        setStatus(status);
        setCreatedAt(createdAt);
        setFinishAt(finishAt);
        setIdProject(idProject);
        setIdUser(idUser);
    }

    public void setId(int id) { this.id = id; }

    public void setName(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.name = name;
    }
    
    public void setStatus(Boolean status){
        this.status = status;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setFinishAt(LocalDate finishAt) {
        this.finishAt = finishAt;
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

    public String getName() { return this.name; }
    public int getId() { return this.id; }
    public Boolean getStatus() { return  this.status; }
    public LocalDate getCreatedAt() {return createdAt; }
    public LocalDate getFinishAt() { return finishAt; }
    public int getIdProject() { return idProject; }
    public int getIdUser() { return idUser; }

    public String toString(){
        return "Id: " + id + "\nname: " + name;
    }

}