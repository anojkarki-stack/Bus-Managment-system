package view;

import control.TicketControl;
import modeel.Ticket;
import util.BusData;
import util.BusData.BusInfo;
import javafx.collections.FXCollections;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class BookingView {

    public VBox getView() {

        TicketControl control = new TicketControl();

        TextField name = new TextField();
        name.setPromptText("Passenger Name");

        ComboBox<String> routeBox = new ComboBox<>();
        routeBox.setItems(FXCollections.observableArrayList(
                BusData.getRoutes().keySet()
        ));

        Label details = new Label("Bus Details: -");
        Label priceLabel = new Label("Price: -");

        ComboBox<String> paymentBox = new ComboBox<>();
        paymentBox.getItems().addAll("Cash", "eSewa", "Khalti");
        paymentBox.setPromptText("Select Payment Method");

        // Route selection
        routeBox.setOnAction(_ -> {
            String route = routeBox.getValue();
            if (route != null) {
                BusInfo info = BusData.getRoutes().get(route);

                details.setText(
                        "Time: " + info.time +
                        " | Type: " + info.type +
                        " | Operator: " + info.operator
                );

                priceLabel.setText("Price: Rs " + info.price);
            }
        });

        // Seats
        ToggleGroup group = new ToggleGroup();
        GridPane seats = new GridPane();
        seats.setHgap(10);
        seats.setVgap(10);

        for (int i = 0; i < 12; i++) {
            RadioButton seat = new RadioButton("S" + (i + 1));
            seat.setToggleGroup(group);
            seats.add(seat, i % 4, i / 4);
        }

        Button bookBtn = new Button("Confirm Ticket");

        bookBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");

        bookBtn.setOnAction(_ -> {

            if (name.getText().isEmpty() ||
                routeBox.getValue() == null ||
                group.getSelectedToggle() == null ||
                paymentBox.getValue() == null) {

                new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
                return;
            }

            RadioButton selectedSeat = (RadioButton) group.getSelectedToggle();
            String route = routeBox.getValue();
            BusInfo info = BusData.getRoutes().get(route);

            Ticket t = new Ticket(
                    name.getText(),
                    route,
                    selectedSeat.getText(),
                    info.price,
                    paymentBox.getValue()
            );

            control.saveTicket(t);

            showETicket(t, info);
        });

        VBox box = new VBox(15,
                name,
                routeBox,
                details,
                priceLabel,
                seats,
                paymentBox,
                bookBtn
        );

        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20));

        return box;
    }

    // 🎟 E-Ticket UI
    private void showETicket(Ticket t, BusInfo info) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("E-Ticket");

        alert.setHeaderText("🎟 Booking Confirmed");

        alert.setContentText(
                "Passenger: " + t.getName() +
                "\nRoute: " + t.getRoute() +
                "\nSeat: " + t.getSeat() +
                "\nTime: " + info.time +
                "\nBus Type: " + info.type +
                "\nOperator: " + info.operator +
                "\nPrice: Rs " + t.getPrice() +
                "\nPayment: " + t.getPayment()
        );

        alert.show();
    }
}