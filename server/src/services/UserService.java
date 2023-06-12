package services;

import interfaces.DAOInterface;
import models.Notification;
import models.User;
import utils.Hash;

import java.sql.*;
import java.util.ArrayList;

public class UserService implements DAOInterface<User> {

    private final Connection connection = ConnectDatabase.getConnection();
    // query create, findOne, findAll, update, delete, login, register
    private String[] columns = {"id", "name", "username", "password", "score", "id_avatar"};
    private final String table = "users";

    public final String QUERY_CREATE = "INSERT INTO " + table + " (name, username, password, score, id_avatar) VALUES (?, ?, ?, ?, ?)";
    public final String QUERY_FIND_ONE = "SELECT * FROM " + table + " WHERE id = ?";
    public final String QUERY_FIND_ALL = "SELECT * FROM " + table;
    public final String QUERY_UPDATE = "UPDATE " + table + " SET name = ?, username = ?, password = ?, score = ?, id_avatar = ? WHERE id = ?";
    public final String QUERY_DELETE = "DELETE FROM " + table + " WHERE id = ?";
    public final String QUERY_LOGIN = "SELECT * FROM " + table + " WHERE username = ? AND password = ?";
    public final String QUERY_REGISTER = "INSERT INTO " + table + " (name, username, password) VALUES (?, ?, ?)";
    public final String FIND_BY_USERNAME = "SELECT * FROM " + table + " WHERE username = ?";
    public final String QUERY_UPDATE_SCORE = "UPDATE " + table + " SET score = ? WHERE id = ?";

    public User findByUsername(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                getUserFromResultSet(resultSet, user);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean checkExistUsername(String username) {
        User user = findByUsername(username);
        return user != null;
    }

    private void getUserFromResultSet(ResultSet resultSet, User user) throws SQLException {
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setScore(resultSet.getFloat("score"));
        user.setIdAvatar(resultSet.getInt("id_avatar"));
    }

    @Override
    public Notification<User> create(User data) {
        if (checkExistUsername(data.getUsername())) {
            return new Notification<>("Username is existed", null, false);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, data.getName());
            preparedStatement.setString(2, data.getUsername());
            preparedStatement.setString(3, Hash.hash(data.getPassword()));
            preparedStatement.setFloat(4, data.getScore());
            preparedStatement.setInt(5, data.getIdAvatar() == 0 ? 1 : data.getIdAvatar());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                data.setId(resultSet.getInt(1));
            }
            return new Notification<>("Created user successfully", data, true);


        } catch (Exception e) {
            e.printStackTrace();
            return new Notification<>("Created user failed", null, false);
        }

    }

    @Override
    public User findOne(int id) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_ONE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                getUserFromResultSet(resultSet, user);
            }
            return user;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Notification<User> update(int id, User data) {
        User user = findOne(id);
        if (user == null) {
            return new Notification<>("User not found", null, false);
        }
        User userByUsername = findByUsername(data.getUsername());
        if (userByUsername != null && userByUsername.getId() != user.getId()) {
            return new Notification<>("Username is existed", null, false);
        }
        if (user.getPassword() != null) {
            data.setPassword(Hash.hash(data.getPassword()));
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
            preparedStatement.setString(1, data.getName());
            preparedStatement.setString(2, data.getUsername());
            preparedStatement.setString(3, data.getPassword());
            preparedStatement.setFloat(4, data.getScore());
            preparedStatement.setInt(5, data.getIdAvatar());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return new Notification<>("Update failed", null, false);
        }
        return null;
    }

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                getUserFromResultSet(resultSet, user);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Notification<User> delete(int id) {
        User user = findOne(id);
        if (user == null) {
            return new Notification<>("User not found", null, false);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return new Notification<>("Delete failed", null, false);
        }
        return null;
    }

    public Notification<User> register(User user) {
        return create(user);
    }

    public Notification<User> login(User user) {
        User userByUsername = findByUsername(user.getUsername());
        System.out.println(user.getUsername());
        if (userByUsername == null || !Hash.compare(user.getPassword(), userByUsername.getPassword())) {
            return new Notification<>("Username or password is wrong", null, false);
        }
        return new Notification<>("Login success", userByUsername, true);
    }

    public Notification<User> updateScore(int id, float score) {
        User user = findOne(id);
        if (user == null) {
            return new Notification<>("User not found", null, false);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_SCORE);
            preparedStatement.setFloat(1, score);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return new Notification<>("Update score failed", null, false);
        }
        return null;
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = new User("namz2", "12345");
        user.setName("Nguyen Anh Minh");
        System.out.println(userService.register(user));
        Notification<User> notification = userService.login(user);
        System.out.println(notification);
    }
}
