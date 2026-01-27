package services;

import entities.Project;

import java.time.LocalDate;

public class ProjectBuilder {
    private String name;
    private String description;
    private LocalDate deadline;
    private int ownerId;



    public ProjectBuilder setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Name cannot exceed 50 characters");
        }
        this.name = name.trim();
        return this;
    }

    public ProjectBuilder setDescription(String description) {
        if (description != null) {
            this.description = description.trim();
        } else {
            this.description = null;
        }
        return this;
    }

    public ProjectBuilder setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }


    public ProjectBuilder setOwnerId(int ownerId) {
        if (ownerId <= 0) {
            throw new IllegalArgumentException("Owner ID must be positive");
        }
        this.ownerId = ownerId;
        return this;
    }

    public Project build(){
        return new Project(name, description, deadline, ownerId);
    }
}
