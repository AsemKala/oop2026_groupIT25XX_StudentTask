package services;

import entities.BugTask;
import entities.FeatureTask;
import entities.ResearchTask;
import entities.Task;

import java.time.LocalDate;

public class TaskFactory {
    public static Task createTask(String type, String name, Boolean status, LocalDate finishAt){
        switch(type){
            case "bug":
                return new BugTask(name, finishAt, status);
            case "feature":
                return new FeatureTask(name, finishAt, status);
            case "research":
                return new ResearchTask(name, finishAt, status);
        }
        throw new IllegalArgumentException("unknown task type");
    }

    public static Task createTask(String type,
                                  int id,
                                  String name,
                                  Boolean status,
                                  LocalDate createdAt,
                                  LocalDate finishAt,
                                  int idProject,
                                  int idUser){
        switch(type){
            case "bug":
                return new BugTask(id, name, status, createdAt, finishAt, idProject, idUser);
            case "feature":
                return new FeatureTask(id, name, status, createdAt, finishAt, idProject, idUser);
            case "research":
                return new ResearchTask(id, name, status, createdAt, finishAt, idProject, idUser);
        }
        throw new IllegalArgumentException("unknown task type");
    }
}
