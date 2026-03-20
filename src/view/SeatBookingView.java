package view;

import control.BookingControl;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import modeel.Ticket;
import util.BusData;
import util.BusData.BusInfo;

public class SeatBookingView {

    private String route;

    public SeatBookingView(String route) {
        this.route = route;
    }

    public VBox getView() {

        BookingControl control = new BookingControl();

        // ===== Title =====
        Label title = new Label("Bus Ticket Booking");
        title.setFont(Font.font("Arial", 24));
        title.setStyle("-fx-text-fill: white;");

        // ===== Passenger Name =====
        TextField name = new TextField();
        name.setPromptText("Passenger Name");
        name.setMaxWidth(250);

        // ===== Route Info =====
        BusInfo info = BusData.getRoutes().get(route);

        Label routeLabel = new Label("Route: " + route);
        Label priceLabel = new Label("Price: Rs " + info.price);

        routeLabel.setStyle("-fx-text-fill: white;");
        priceLabel.setStyle("-fx-text-fill: white;");

        // ===== Payment =====
        ComboBox<String> paymentBox = new ComboBox<>();
        paymentBox.getItems().addAll("Cash", "eSewa", "Khalti");
        paymentBox.setPromptText("Select Payment");
        paymentBox.setMaxWidth(250);

        // ===== Seats =====
        ToggleGroup group = new ToggleGroup();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        for (int i = 0; i < 20; i++) {
            RadioButton seat = new RadioButton("S" + (i + 1));
            seat.setToggleGroup(group);
            grid.add(seat, i % 4, i / 4);
        }

        // ===== Button =====
        Button book = new Button("Confirm Booking");
        book.setStyle(
                "-fx-background-color: #2ecc71;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 8px;"
        );

        book.setOnAction(_ -> {

            if (name.getText().isEmpty() ||
                group.getSelectedToggle() == null ||
                paymentBox.getValue() == null) {

                new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
                return;
            }

            RadioButton selected = (RadioButton) group.getSelectedToggle();

            Ticket t = new Ticket(
                    name.getText(),
                    route,
                    selected.getText(),
                    info.price,
                    paymentBox.getValue()
            );

            control.saveTicket(t);

            new Alert(Alert.AlertType.INFORMATION, "🎟 Ticket Booked Successfully!").show();
        });

        // ===== Card Layout =====
        VBox card = new VBox(15,
                title,
                name,
                routeLabel,
                priceLabel,
                new Label("Select Seat:"),
                grid,
                paymentBox,
                book
        );

        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setMaxWidth(400);
        card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.15);" +
                "-fx-background-radius: 15px;" +
                "-fx-padding: 20;"
        );

        // ===== Background Gradient =====
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