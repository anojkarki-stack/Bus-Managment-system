package modeel;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnectionTest {

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/bus_system?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String pass = "1234";

            Connection conn = DriverManager.getConnection(url, user, pass);

            System.out.println("Database Connected!");
            return conn;

        } catch (Exception e) {
            System.out.println("❌ Connection Error:");
            e.printStackTrace();  // 🔴 THIS shows the real problem
            return null;
        }
    }
}