package models;

import java.io.Serializable;

public class LeaveRoom implements Serializable{
    private String content;
    private int roomID;

    public LeaveRoom(String content, int roomID) {
        this.content = content;
        this.roomID = roomID;
    }

    public String toString() {
        return "JoinRoom{" +
                "content='" + content + '\'' +
                ", roomID=" + roomID +
                '}';
    }

    public String getContent() {
        return content;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
}
