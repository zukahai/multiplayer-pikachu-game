package configs;

public class Configs {
    // Config Mysql
    public static final String MSQL_HOST = "localhost";
    public static final String MSQL_PORT = "3306";
    public static final String MSQL_USER = "root";
    public static final String MSQL_PASSWORD = "12345";
    public static final String MSQL_DATABASE = "pikachu_game";

    // Config Server
    public static final int SERVER_PORT = 8080;
    
    // Config Game
    public static final int NROOM = 10;
    public static final int MAX_LEVEL = 36;
    public static final int LEVEL = 10; // 1 -> 36
    public static final int SCORE = 100;
    public static final int SCORE_BONUS = 72;
    public static final int SUB_SCORE_BONUS = 1;

    // user
    public static final int ID_USER_FAILER = -999;
}
