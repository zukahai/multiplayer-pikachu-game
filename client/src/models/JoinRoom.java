package models;

import java.io.Serializable;

public class JoinRoom implements Serializable {
    private String content;
    private int roomID;

    public JoinRoom(String content, int roomID) {
        this.content = content;
        this.roomID = roomID;
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
