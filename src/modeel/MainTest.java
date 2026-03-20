package modeel;

import java.sql.Connection;

public class MainTest {

    public static void main(String[] args) {

        Connection con = DbConnectionTest.getConnection();

        if (con != null) {
            System.out.println("✅ Database Connected Successfully!");
        } else {
            System.out.println("❌ Database Connection Failed!");
        }
    }
}