package data.interfaces;

import entities.Project;

import java.util.List;

public interface IProjectRepository {
    void create(Project project);
    List<Project> findAll();
    Project findById(int id);
    List<Project> findByOwnerId(int ownerId);
}
