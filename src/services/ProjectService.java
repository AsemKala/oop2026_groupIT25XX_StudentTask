package services;

import data.interfaces.IProjectRepository;
import data.interfaces.IUserRepository;
import entities.Project;
import entities.User;
import exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProjectService {
    private IProjectRepository projectRepository;
    private IUserRepository userRepository;  // ✓ Need this to validate owner exists

    public ProjectService(IProjectRepository projectRepository, IUserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public void createProject(Project project) {

    public void addProject(int id,String name){
        Project project = new Project(id,name);
        projects.add(project);
    }

    public Project getProjectById(int id) {
        Project project = projectRepository.findById(id);
        if (project == null) {
            throw new ProjectNotFoundException("Project with ID " + id + " not found");
        }
        return project;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectsByOwner(int ownerId) {
        User owner = userRepository.findById(ownerId);
        if (owner == null) {
            throw new UserNotFoundException("User with ID " + ownerId + " not found");
        }

        return projectRepository.findByOwnerId(ownerId);
    }

    private boolean isDeadlineInPast(String deadlineStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate deadline = LocalDate.parse(deadlineStr, formatter);
            LocalDate today = LocalDate.now();
            return deadline.isBefore(today);
        } catch (Exception e) {
            return false;
        }
    }
}