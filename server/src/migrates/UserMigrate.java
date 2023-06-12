package migrates;

import services.ConnectDatabase;

import java.sql.Connection;

public class UserMigrate {
    private String table = "users";

    private Connection connection = ConnectDatabase.getConnection();
    private String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + table + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT NOT NULL," +
            "username TEXT NOT NULL," +
            "password TEXT NOT NULL," +
            "score REAL NOT NULL DEFAULT 0," +
            "id_avatar INTEGER NOT NULL DEFAULT 1" +
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
