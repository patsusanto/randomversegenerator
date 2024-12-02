package sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static String url = "jdbc:sqlite:C:/sqlite/db/KJV.db";
    private static Connection con;
    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Failed to create the database connection");
        }
        return con;
    }

}
