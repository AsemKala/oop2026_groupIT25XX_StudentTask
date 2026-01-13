package services;

import entities.Project;
import entities.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskService {
    ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String name, Project project, Boolean status){
        Task task = new Task(name,project, status);
        tasks.add(task);
        System.out.println("Task was added");
    }
    public void ChangeStatus(int id, Boolean newStatus){
        for (Task task : tasks){
            if(task.getId() == id){
                task.setStatus(newStatus);
                System.out.println("Status was changed");
                return;
            }
        }
    }
}
