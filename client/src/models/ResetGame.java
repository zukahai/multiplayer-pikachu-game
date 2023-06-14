package models;

import java.io.Serializable;

public class ResetGame implements Serializable{
    private int roomID;

    public ResetGame(int roomID) {
        this.roomID = roomID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String toString(){
        return "ResetGame{" +
                "roomID=" + roomID +
                '}';
    }
}
