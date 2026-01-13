package services;

import entities.Student;
import java.util.ArrayList;

public class StudentService {
    ArrayList<Student> students = new ArrayList<>();
    public void addStudent(String name, String email){
        Student student = new Student(name, email);
        students.add(student);
        System.out.println("Student was added");
    }

    public void findStudent(String name){
        for (Student student: students){
            if(student.getName().equals(name)){
                System.out.println(student);
                break;
            }
        }
    }

}
