package control;

import modeel.DbConnectionTest;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import view.LoginView;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterControl {

    public void register(String email, String password, Stage stage) {

        try {
            Connection conn = DbConnectionTest.getConnection();

            String sql = "INSERT INTO users(email, password) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ps.executeUpdate();

            new Alert(Alert.AlertType.INFORMATION, "Registered Successfully").show();

            new LoginView();
			stage.setScene(new Scene(LoginView.getView(stage), 800, 600));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}