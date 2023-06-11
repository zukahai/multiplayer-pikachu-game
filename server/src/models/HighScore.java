package models;

import java.io.Serializable;
import java.util.ArrayList;

public class HighScore implements Serializable{
    private ArrayList<User> users = new ArrayList<>();

     public HighScore(){
        this.users = new ArrayList<>();
    }

    public HighScore(ArrayList<User> users){
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
