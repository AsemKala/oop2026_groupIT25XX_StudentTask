package services;

import entities.BugTask;
import entities.FeatureTask;
import entities.ResearchTask;
import entities.Task;

import java.time.LocalDate;

public class TaskFactory {
    public static Task createTask(String type, String title, Boolean status, LocalDate finish_at){
        switch(type){
            case "bug":
                return new BugTask(title, finish_at, status);
            case "feature":
                return new FeatureTask(title, finish_at, status);
            case "research":
                return new ResearchTask(title, finish_at, status);
        }
        throw new IllegalArgumentException("unknown task type");
    }
}
