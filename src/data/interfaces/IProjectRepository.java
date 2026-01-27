package data.interfaces;

import entities.Project;

import java.util.List;

public interface IProjectRepository extends IRepository<Project> {
    List<Project> findByOwnerId(int ownerId);
}
