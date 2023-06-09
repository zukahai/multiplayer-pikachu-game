package services;

import interfaces.DAOInterface;
import models.Notification;
import models.User;

import java.sql.Connection;
import java.util.ArrayList;

public class UserService implements DAOInterface<User> {

    private Connection connection = ConnectDatabase.getConnection();
    // query create, findOne, findAll, update, delete, login, register
    private String[] columns = {"id", "name", "username", "password", "score", "id_avatar"};
    private String table = "users";

    public final String QUERY_CREATE = "INSERT INTO " + table + String.join(", ", columns) + " VALUES (?, ?, ?, ?, ?, ?)";
    public final String QUERY_FIND_ONE = "SELECT * FROM " + table + " WHERE id = ?";


    @Override
    public Notification<User> create(User data) {
        return null;
    }

    @Override
    public Notification<User> findOne(int id) {
        return null;
    }

    @Override
    public Notification<User> update(int id, User data) {
        return null;
    }

    @Override
    public ArrayList<User> findAll() {
        return null;
    }

    @Override
    public Notification<User> delete(int id) {
        return null;
    }
}
