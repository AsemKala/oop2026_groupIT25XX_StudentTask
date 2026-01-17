package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Project {
    private int id;
    private String name;
    private String description;
    private String deadline;
    private String createdAt;
    private int ownerId;

    public Project(String name, String description, String deadline, int ownerId) {
        this.id = 0;
        setName(name);
        setDescription(description);
        setDeadline(deadline);
        this.createdAt = null;
        setOwnerId(ownerId);
    }

    public Project(int id, String name, String description, String deadline, String createdAt, int ownerId) {
        this.id = id;
        setName(name);
        setDescription(description);
        setDeadline(deadline);
        setCreatedAt(createdAt);
        setOwnerId(ownerId);
    }

    public Project() {
        this.id = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Name cannot exceed 50 characters");
        }
        this.name = name.trim();
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description.trim();
        } else {
            this.description = null;
        }
    }

    public void setDeadline(String deadline) {
        if (deadline == null || deadline.isBlank()) {
            throw new IllegalArgumentException("Deadline must be specified");
        }
        if (!isValidDate(deadline)) {
            throw new IllegalArgumentException("Invalid date format. Use MM/dd/yyyy");
        }
        this.deadline = deadline;
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

    public void setOwnerId(int ownerId) {
        if (ownerId <= 0) {
            throw new IllegalArgumentException("Owner ID must be positive");
        }
        this.ownerId = ownerId;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDeadline() { return deadline; }
    public String getCreatedAt() { return createdAt; }
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