package services;

import entities.Task;

import java.time.LocalDate;

public class TaskFactory {
    public static Task createTask(String type, String title, LocalDate deadline){
        switch(type){
            case "bug":
                return new BugTask();
            case "feature":
                return new FeatureTask();
            case "research":
                return new ResearchTask();
        }
        throw new IllegalArgumentException("unknown task type");
    }
}
