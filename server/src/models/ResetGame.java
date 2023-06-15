package models;

import java.io.Serializable;

public class ResetGame implements Serializable{
    private int roomID;
    private User user;

    public ResetGame(int roomID, User user) {
        this.roomID = roomID;
        this.user = user;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString(){
        return "ResetGame{" +
                "roomID=" + roomID +
                '}';
    }
}
