package services;

import configs.Configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDatabase {
    public static Connection connection;
    static String url = "jdbc:mysql://" + Configs.MSQL_HOST + ":" + Configs.MSQL_PORT + "/" + Configs.MSQL_DATABASE;

    public static Connection getConnection() {
        return connection;
    }

    static {
        try {
            connection = DriverManager.getConnection(url, Configs.MSQL_USER, Configs.MSQL_PASSWORD);
            System.out.println("Connected to database");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
