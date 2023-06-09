package migrates;

import services.ConnectDatabase;

import java.sql.Connection;

public class UserMigrate {
    private String table = "users";

    private Connection connection = ConnectDatabase.getConnection();
    private String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + table + " (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(255) NOT NULL," +
            "username VARCHAR(255) NOT NULL," +
            "password text NOT NULL," +
            "score FLOAT NOT NULL DEFAULT 0," +
            "id_avatar INT NOT NULL DEFAULT 1" +
            ")";


    public void run() {
        try {
            connection.prepareStatement(QUERY_CREATE_TABLE).execute();
            System.out.println("Created table " + table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserMigrate userMigrate = new UserMigrate();
        userMigrate.run();
    }
}
