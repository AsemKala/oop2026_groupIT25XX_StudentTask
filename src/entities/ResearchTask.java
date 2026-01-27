package entities;

import java.time.LocalDate;

public class ResearchTask extends Task {
    public ResearchTask(String title, LocalDate deadline, Boolean status){
        super(title, status, deadline);
    }
}
