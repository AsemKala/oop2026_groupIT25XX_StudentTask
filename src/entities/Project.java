package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Project {
    private int id;
    private String name;
    private String description;
    private LocalDate deadline;
    private LocalDate createdAt;
    private int ownerId;

    public Project(String name, String description, LocalDate deadline, int ownerId) {
        this.id = 0;
        this.name = name;
        this.description = description;
        this.deadline =deadline;
        this.createdAt = null;
        this.ownerId = ownerId;
    }

    public Project(int id, String name, String description, LocalDate deadline, LocalDate createdAt, int ownerId) {
        setId(id);
        this.name = name;
        this.description = description;
        this.deadline =deadline;
        setCreatedAt(createdAt);
        this.ownerId = ownerId;
    }

    public Project() {
        setId(id);
        setCreatedAt(createdAt);
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }




    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public LocalDate getDeadline() { return deadline; }
    public LocalDate getCreatedAt() { return createdAt; }
    public int getOwnerId() { return ownerId; }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline='" + deadline + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
}