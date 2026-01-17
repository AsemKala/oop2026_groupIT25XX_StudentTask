package entities;

import java.util.EmptyStackException;

public class Comment {
    private int id;
    private static int idGen;
    private String message;

    public Comment(){
        this.id = 0;
    }
    public Comment(String message){
        setMessage(message);
    }
    public void setMessage(String message){
        if(message.isEmpty()){
            throw new NullPointerException("Value is null!");
        }
        this.message = message;
    }


    public String toString(){
        return "Id: " + id + "\nmessage: " + message;
    }
}
