package view;

import control.RegisterControl;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RegisterView {

    private Stage stage;

    public RegisterView(Stage stage) {
        this.stage = stage;
    }

    public StackPane getView() {

        RegisterControl control = new RegisterControl();

        Label title = new Label("Create Account");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        TextField email = new TextField();
        email.setPromptText("Email");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");

        Button registerBtn = new Button("Register");

        Button backBtn = new Button("Back to Login");

        registerBtn.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;");
        backBtn.setStyle("-fx-background-color: #7f8c8d; -fx-text-fill: white;");

        registerBtn.setOnAction(_ -> {

            String pass = password.getText();
            String confirm = confirmPassword.getText();

            // ✅ PASSWORD MATCH VALIDATION
            if(!pass.equals(confirm)) {
                new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
                return;
            }

            control.register(email.getText(), pass, stage);
        });

        backBtn.setOnAction(_ -> {
            new LoginView();
			stage.getScene().setRoot(LoginView.getView(stage));
        });

        VBox box = new VBox(15, title, email, password, confirmPassword, registerBtn, backBtn);
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
}