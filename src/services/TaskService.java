package services;

import com.sun.jdi.request.DuplicateRequestException;
import entities.Project;
import entities.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskService {



    private String name = "";
    private String link = "";
    private String password = "";

    public void AddTask(String name, String finish_at, int id_prokect, int user_id){

        try(Connection conn = DriverManager.getConnection(link, name, password)){
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks");
            while (resultSet.next()){
                if(!resultSet.getString(name).isEmpty()){
                    throw new DuplicateRequestException("This task already exists");
                }
                else{
                    int row = statement.executeUpdate("insert Tasks(name, finish_at, id_project, id_user) VALUES (?,?,?,?)");
                    System.out.println("Task was added");
                }
            }
        }catch(Exception e){
            throw new IllegalArgumentException("Error 505");
        }
    }
    public void ChangeStatus(int id, boolean status){
        try(Connection conn = DriverManager.getConnection(link, name, password)){
            Statement statement = conn.createStatement();
            int rows = statement.executeUpdate("Update Tasks SET status = status where id = id");
            System.out.println("Status was changed");
        }catch(Exception e){
            throw new IllegalArgumentException("Error");
        }
    }
}
