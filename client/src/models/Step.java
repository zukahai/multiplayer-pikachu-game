package models;

import java.awt.Point;
import java.io.Serializable;

public class Step implements Serializable {

    private Point position1, position2;

    private int roomID;
    
    private User user;

    public Step() {
        this.position1 = new Point(0, 0);
        this.position2 = new Point(0, 0);
        this.user = new User("a", "b");
    }

    public Step(Point position1, Point position2, int roomID) {
        this.position1 = position1;
        this.position2 = position2;
        this.roomID = roomID;
    }

    public void setPosition1(Point position1) {
        this.position1 = position1;
    }

    public void setPosition2(Point position2) {
        this.position2 = position2;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Point getPosition1() {
        return position1;
    }

    public Point getPosition2() {
        return position2;
    }

    public int getRoomID() {
        return roomID;
    }

    public User getUser() {
        return user;
    }

    public String toString() {
        return "Step{" +
                "position1=" + position1 +
                ", position2=" + position2 +
                ", roomID=" + roomID +
                ", user=" + user +
                '}';
    }

    public static void main(String[] args) {
        new Step();
    }
}
