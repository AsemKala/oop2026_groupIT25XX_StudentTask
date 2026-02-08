package entities;

import java.time.LocalDate;

public class BugTask extends Task {
    public BugTask(String title, LocalDate deadline, Boolean status) {
        super(title, status, deadline);
    }

    public BugTask(int id, String name, Boolean status, LocalDate createdAt, LocalDate finishAt, int idProject, int idUser){
        super(id, name, status, createdAt, finishAt, idProject, idUser);
    }

    @Override
    public String getType() {
        return "bug";
    }
}
