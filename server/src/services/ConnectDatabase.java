package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    public static Connection connection;

    static String  databasePath = "jdbc:sqlite:src/main/resources/database/pikachu.db";


    public static Connection getConnection() {
        return connection;
    }

    static {
        try {
            connection = DriverManager.getConnection(databasePath);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
