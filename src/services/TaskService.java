package services;

import com.sun.jdi.request.DuplicateRequestException;
import data.interfaces.IDB;
import data.interfaces.ITaskRepository;
import entities.Project;
import entities.Task;
import exceptions.TaskNotFoundException;
import repositories.TaskRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TaskService {
    private ITaskRepository TaskRepository;
    public TaskService(ITaskRepository TaskRepository){
        this.TaskRepository = TaskRepository;
    }

    public void AddTAsk(String name, String finish_at, int id_project, int user_id){
        Task task = TaskRepository.AddTask(name,finish_at,id_project,user_id);
    }
    public void FindById(int id){
        Task task = TaskRepository.FindTaskById(id);
    }
    public void changeStatus(int id, boolean status){
        Task task = TaskRepository.ChangeStatus(id, status);
    }


}
