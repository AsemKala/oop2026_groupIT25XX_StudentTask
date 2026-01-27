package entities;

import java.time.LocalDate;

public class FeatureTask extends Task {
    public FeatureTask(String title, LocalDate deadline, Boolean status){
        super(title, status, deadline);
    }
}
