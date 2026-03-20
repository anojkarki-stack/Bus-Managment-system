package view;

import util.BusData;
import util.BusData.BusInfo;
import javafx.collections.FXCollections;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SearchView {

    public VBox getView(StackPane contentArea) {

        Label title = new Label("Search Bus Routes");
        title.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        ComboBox<String> routeBox = new ComboBox<>();
        routeBox.setPromptText("Select Route");
        routeBox.setItems(FXCollections.observableArrayList(
                BusData.getRoutes().keySet()
        ));

        Label details = new Label("Details: -");
        Label priceLabel = new Label("Price: -");

        details.setStyle("-fx-text-fill: white;");
        priceLabel.setStyle("-fx-text-fill: white;");

        Button checkBtn = new Button("Check");
        Button bookBtn = new Button("Book Ticket");

        checkBtn.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;");
        bookBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");

        // Show bus details
        checkBtn.setOnAction(_ -> {
            String route = routeBox.getValue();

            if (route != null) {
                BusInfo info = BusData.getRoutes().get(route);

                details.setText(
                        "Time: " + info.time +
                        "\nType: " + info.type +
                        "\nOperator: " + info.operator
                );

                priceLabel.setText("Price: Rs " + info.price);
            }
        });

        // ✅ Navigate to Booking View
        bookBtn.setOnAction(_ -> {
            String route = routeBox.getValue();

            if (route == null) {
                new Alert(Alert.AlertType.ERROR, "Select a route first").show();
                return;
            }

            SeatBookingView bookingView = new SeatBookingView(route);
            contentArea.getChildren().setAll(bookingView.getView());
        });

        VBox card = new VBox(15,
                title,
                routeBox,
                checkBtn,
                details,
                priceLabel,
                bookBtn
        );

        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setMaxWidth(400);

        card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.15);" +
                "-fx-background-radius: 15;" +
                "-fx-padding: 20;"
        );

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