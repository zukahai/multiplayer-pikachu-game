package models;

import java.io.Serializable;

public class User implements Serializable{
    private int id;
    private String name;
    private String username;
    private String password;
    private float score;
    private int id_avatar;

    public User() {
    }

    public User(int id, String name, String username, String password, float score, int id_avatar) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.score = score;
        this.id_avatar = id_avatar;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getId_avatar() {
        return id_avatar;
    }

    public void setId_avatar(int id_avatar) {
        this.id_avatar = id_avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                ", id_avatar=" + id_avatar +
                '}';
    }
}
