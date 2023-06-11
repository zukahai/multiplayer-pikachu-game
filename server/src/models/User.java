package models;

import java.io.Serializable;
import java.util.HashMap;

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

    public int getIdAvatar() {
        return id_avatar;
    }

    public void setIdAvatar(int id_avatar) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.username == ((User) o).username;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode() * 27 + this.password.hashCode() * 12;
    }

    public static void main(String[] args) {
        User user1 = new User("hai", "1");
        User user2 = new User("hai", "1");
        System.out.println(user1 == user2);;
        HashMap<User, String> users = new HashMap<>();
        users.put(user2, "a");
        users.put(user1, "aaa");
        System.out.println(user1.hashCode() + " " + user2.hashCode());
        System.out.println(users.size());
    }
}
