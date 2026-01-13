package services;

import entities.Project;
import java.util.ArrayList;

public class ProjectService {
    ArrayList<Project> projects = new ArrayList<>();

    public void addProject(String name){
        Project project = new Project(name);
        projects.add(project);
    }

}
