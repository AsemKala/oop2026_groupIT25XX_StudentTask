package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Comment {
    private int id;
    private String content;
    private String createdAt;
    private int taskId;
    private int userId;


    public Comment() {
        this.id = 0;
    }

    public Comment(String content, int taskId, int userId){
        this.id = 0;
        setContent(content);
        setTaskId(taskId);
        setUserId(userId);
    }

    public Comment(){
        this.id = 0;
    }
    public Comment(String message){
        setMessage(message);
    }
    public void setMessage(String message){
        if(message.isEmpty()){
            throw new NullPointerException("Value is null!");
        }
        this.content = content;
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

    public void setTaskId(int taskId) {
        if (taskId <= 0) {
            throw new IllegalArgumentException("Task ID must be positive");
        }
        this.taskId = taskId;
    }

    public void setUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }
        this.userId = userId;
    }

    public int getId() { return id; }
    public String getContent() { return content; }
    public int getTaskId() { return taskId; }
    public int getUserId() { return userId; }
    public String getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", taskId=" + taskId +
                ", userId=" + userId +
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
