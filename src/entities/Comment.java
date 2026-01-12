package entities;

public class Comment {
    private int id;
    private static int idGen;
    private String message;

    public Comment(String message){
        id = idGen;
        idGen++;
        setMessage(message);
    }
    public void setMessage(String message){
        if(message.isEmpty()){
            throw new IllegalArgumentException("message can't be null");
        }
        this.message = message;
    }


    public String toString(){
        return "Id: " + id + "\nmessage: " + message;
    }
}
