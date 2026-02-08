package entities;

import java.time.LocalDate;

public class FeatureTask extends Task {
    public FeatureTask(String title, LocalDate deadline, Boolean status) {
        super(title, status, deadline);
    }

    public FeatureTask(int id, String name, Boolean status, LocalDate createdAt, LocalDate finishAt, int idProject, int idUser){
        super(id, name, status, createdAt, finishAt, idProject, idUser);
    }

    @Override
    public String getType() {
        return "feature";
    }
}
