package control;

import modeel.DbConnectionTest;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.DashboardView;

import java.sql.*;

public class LoginControl {

    public void login(String email, String password, Stage stage) {

        try {
            Connection conn = DbConnectionTest.getConnection();

            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                stage.setScene(new Scene(new DashboardView(stage, sql).getView(), 900, 600));
            } else {
                System.out.println("Invalid login");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}