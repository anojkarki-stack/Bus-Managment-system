package view;

import modeel.DbConnectionTest;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import modeel.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TicketView {

    @SuppressWarnings("unchecked")
	public VBox getView() {

        TableView<Ticket> table = new TableView<>();

        // ===== Columns =====
        TableColumn<Ticket, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getName())
        );

        TableColumn<Ticket, String> routeCol = new TableColumn<>("Route");
        routeCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getRoute())
        );

        TableColumn<Ticket, String> seatCol = new TableColumn<>("Seat");
        seatCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getSeat())
        );

        TableColumn<Ticket, String> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(c.getValue().getPrice())
                )
        );

        TableColumn<Ticket, String> paymentCol = new TableColumn<>("Payment");
        paymentCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getPayment())
        );

        table.getColumns().addAll(nameCol, routeCol, seatCol, priceCol, paymentCol);

        ObservableList<Ticket> data = FXCollections.observableArrayList();

        // ===== Load Data =====
        loadData(data);

        table.setItems(data);

        // ===== Cancel Button =====
        Button cancelBtn = new Button("Cancel Ticket");
        cancelBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");

        cancelBtn.setOnAction(_ -> {

            Ticket selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                new Alert(Alert.AlertType.ERROR, "Select a ticket first").show();
                return;
            }

            try {
                Connection conn = DbConnectionTest.getConnection();

                String sql = "DELETE FROM tickets WHERE name=? AND route=? AND seat=?";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, selected.getName());
                ps.setString(2, selected.getRoute());
                ps.setString(3, selected.getSeat());

                ps.executeUpdate();

                new Alert(Alert.AlertType.INFORMATION, "Ticket Cancelled").show();

                // Refresh table
                data.clear();
                loadData(data);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox root = new VBox(10, table, cancelBtn);
        root.setPadding(new Insets(20));

        return root;
    }

    // ===== Load Data Method =====
    private void loadData(ObservableList<Ticket> data) {

        try {
            Connection conn = DbConnectionTest.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM tickets");

            while (rs.next()) {
                data.add(new Ticket(
                        rs.getString("name"),
                        rs.getString("route"),
                        rs.getString("seat"),
                        rs.getInt("price"),
                        rs.getString("payment")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}