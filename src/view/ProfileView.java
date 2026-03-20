package view;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class ProfileView {

    private String username;

    // You can pass logged-in username/email here
    public ProfileView(String username) {
        this.username = username;
    }

    public VBox getView() {

        // ===== Title =====
        Label title = new Label("User Profile");
        title.setFont(Font.font("Arial", 22));
        title.setStyle("-fx-text-fill: white;");

        // ===== Profile Card =====
        Label nameLabel = new Label("Name: " + username);
        Label emailLabel = new Label("Email: " + username);
        Label roleLabel = new Label("Role: User");

        nameLabel.setStyle("-fx-text-fill: white;");
        emailLabel.setStyle("-fx-text-fill: white;");
        roleLabel.setStyle("-fx-text-fill: white;");

        VBox card = new VBox(10, title, nameLabel, emailLabel, roleLabel);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(20));
        card.setMaxWidth(300);

        card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.15);" +
                "-fx-background-radius: 15;" +
                "-fx-padding: 20;"
        );

        // ===== Background =====
        StackPane root = new StackPane(card);
        root.setAlignment(Pos.CENTER);
        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #1e3c72, #2a5298);"
        );

        VBox wrapper = new VBox(root);
        wrapper.setAlignment(Pos.CENTER);

        return wrapper;
    }
}