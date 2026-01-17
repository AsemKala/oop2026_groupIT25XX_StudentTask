package services;

import entities.Project;
import java.util.ArrayList;

public class ProjectService {
    ArrayList<Project> projects = new ArrayList<>();

    public void addProject(int id,String name){
        Project project = new Project(id,name);
        projects.add(project);
    }

}
