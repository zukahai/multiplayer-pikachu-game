package utils;

import java.util.HashMap;

import models.Server;
import models.User;

public class Test {

    public static void main(String[] args) {
        Server.score[0] = new HashMap<User, Integer>();
        
        User user1 = new User(1, "Phan Đức Hải", "HaiZuka", "a", 0, 4);
        User user2 = new User(1, "Phan Đức Hải", "HaiZuka", "a", 0, 4);
        Server.score[0].put(user2, 10);
        Server.score[0].put(user1, 100);
        System.out.println(Server.score[0].get(user1));
    }
}
