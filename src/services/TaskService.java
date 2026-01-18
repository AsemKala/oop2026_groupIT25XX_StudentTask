package services;

import data.interfaces.ITaskRepository;
import entities.Task;

public class TaskService {
    private ITaskRepository TaskRepository;
    public TaskService(ITaskRepository TaskRepository){
        this.TaskRepository = TaskRepository;
    }

    public void AddTAsk(String name, String finish_at, int id_project, int user_id){

    }
    public void FindById(int id){
        Task task = TaskRepository.findById(id);
    }
    public void changeStatus(int id, boolean status){

    }

}