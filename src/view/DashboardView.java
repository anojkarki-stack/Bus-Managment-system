package view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DashboardView {

    private Stage stage;
    private String username;

    // Pass logged-in username from Login
    public DashboardView(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
    }

    public BorderPane getView() {

        // ===== Sidebar =====
        VBox menu = new VBox(15);
        menu.setPadding(new Insets(20));
        menu.setPrefWidth(200);
        menu.setStyle("-fx-background-color: #1e2a38;");

        Label title = new Label("🚍 Bus System");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        Button searchBtn = new Button("🚌 Search Bus");
        Button bookBtn = new Button("🎟 Book Ticket");
        Button ticketsBtn = new Button("📄 My Tickets");
        Button profileBtn = new Button("👤 Profile");
        Button logoutBtn = new Button("🚪 Logout");

        styleButton(searchBtn);
        styleButton(bookBtn);
        styleButton(ticketsBtn);
        styleButton(profileBtn);
        styleButton(logoutBtn);

        searchBtn.setStyle(searchBtn.getStyle() + "-fx-background-color: #3498db;");
        bookBtn.setStyle(bookBtn.getStyle() + "-fx-background-color: #27ae60;");
        ticketsBtn.setStyle(ticketsBtn.getStyle() + "-fx-background-color: #8e44ad;");
        profileBtn.setStyle(profileBtn.getStyle() + "-fx-background-color: #f39c12;");
        logoutBtn.setStyle(logoutBtn.getStyle() + "-fx-background-color: #e74c3c;");

        menu.getChildren().addAll(title, searchBtn, bookBtn, ticketsBtn, profileBtn, logoutBtn);

        // ===== Content Area =====
        StackPane content = new StackPane();
        content.setPadding(new Insets(20));

        Label welcome = new Label("Welcome " + username + " 🚍");
        welcome.setStyle("-fx-font-size: 22px;");
        content.getChildren().add(welcome);

        // ===== Actions =====

        // Search
        searchBtn.setOnAction(_ ->
                content.getChildren().setAll(
                        new SearchView().getView(content)
                )
        );

        // Booking (message)
        bookBtn.setOnAction(_ ->
                content.getChildren().setAll(
                        new Label("👉 Select a route from Search to book ticket")
                )
        );

        // Tickets
        ticketsBtn.setOnAction(_ ->
                content.getChildren().setAll(
                        new TicketView().getView()
                )
        );

        // Profile 👤
        profileBtn.setOnAction(_ ->
                content.getChildren().setAll(
                        new ProfileView(username).getView()
                )
        );

        // Logout
        logoutBtn.setOnAction(_ -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("Do you want to logout?");

            if (alert.showAndWait().isPresent()) {
                Scene scene = new Scene(LoginView.getView(stage), 800, 600);
                stage.setScene(scene);
                stage.setTitle("Login");
            }
        });

        // ===== Layout =====
        BorderPane root = new BorderPane();
        root.setLeft(menu);
        root.setCenter(content);

        return root;
    }

    // 🎨 Button Style
    private void styleButton(Button btn) {
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-padding: 10;"
        );

        btn.setOnMouseEntered(_ -> btn.setStyle(btn.getStyle() + "-fx-opacity: 0.85;"));
        btn.setOnMouseExited(_ -> btn.setStyle(btn.getStyle().replace("-fx-opacity: 0.85;", "")));
    }
}