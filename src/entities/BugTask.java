package entities;

import java.time.LocalDate;

public class BugTask extends Task {
    public BugTask(String title, LocalDate deadline, Boolean status){
        super(title, status, deadline);
    }
}
