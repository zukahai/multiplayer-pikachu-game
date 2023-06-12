package models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListRoom implements Serializable {
    private Game rooms[];

    public ListRoom() {
        this.rooms = new Game[0];
    }

    public ListRoom(Game rooms[]) {
        this.rooms = rooms;
    }

    public Game[] getRooms() {
        return rooms;
    }

    public ArrayList<Game> getArrayListRoom() {
        ArrayList<Game> arrayList = new ArrayList<>();
        for (int i = 0; i < this.rooms.length; i++) {
            arrayList.add(this.rooms[i]);
        }
        return arrayList;
    }

    public void setRooms(Game[] rooms) {
        this.rooms = rooms;
    }
}
