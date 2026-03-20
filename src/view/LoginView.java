package view;

import control.LoginControl;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginView {

    public static StackPane getView(Stage stage) {

        LoginControl control = new LoginControl();

        Label title = new Label("🚌 Bus Ticket System");
        title.setStyle("-fx-font-size: 26px; -fx-text-fill: white;");

        TextField email = new TextField();
        email.setPromptText("Email");

        PasswordField pass = new PasswordField();
        pass.setPromptText("Password");

        Button loginBtn = new Button("Login");
        Button registerBtn = new Button("Register");

        loginBtn.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;");
        registerBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");

        loginBtn.setOnAction(_ ->
            control.login(email.getText(), pass.getText(), stage)
        );

        registerBtn.setOnAction(_ -> {
            stage.getScene().setRoot(new RegisterView(stage).getView());
        });

        VBox box = new VBox(15, title, email, pass, loginBtn, registerBtn);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(30));

        box.setStyle(
            "-fx-background-color: rgba(0,0,0,0.6);" +
            "-fx-background-radius: 15;"
        );

        StackPane root = new StackPane(box);
        root.setStyle("-fx-background-color: linear-gradient(to right, #3498db, #2c3e50);");

        return root;
    }
    

	public Parent getView1(Stage stage) {
		// TODO Auto-generated method stub
		return null;
	}
}