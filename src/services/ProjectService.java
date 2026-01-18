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
    private final IProjectRepository projectRepository;
    private final IUserRepository userRepository;

    public ProjectService(IProjectRepository projectRepository, IUserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public void createProject(Project project) {

        User owner = userRepository.findById(project.getOwnerId());
        if (owner == null) {
            throw new UserNotFoundException("Owner user with ID " + project.getOwnerId() + " not found");
        }

        if (isDeadlineInPast(project.getDeadline())) {
            throw new DeadlineInPastException("Project deadline cannot be in the past");
        }

        List<Project> ownerProjects = projectRepository.findByOwnerId(project.getOwnerId());
        for (Project existingProject : ownerProjects) {
            if (existingProject.getName().equalsIgnoreCase(project.getName())) {
                throw new DuplicateProjectNameException("You already have a project named '" + project.getName() + "'");
            }
        }

        projectRepository.create(project);
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