package view;

import control.CancelControl;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CancelView {

    public VBox getView() {

        CancelControl control = new CancelControl();

        TextField ticketId = new TextField();
        ticketId.setPromptText("Enter Ticket ID");

        Button cancelBtn = new Button("Cancel Ticket");

        cancelBtn.setOnAction(_ -> {
            int id = Integer.parseInt(ticketId.getText());
            control.cancelTicket(id);
            new Alert(Alert.AlertType.INFORMATION, "Ticket Cancelled").show();
        });

        VBox box = new VBox(15, ticketId, cancelBtn);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20));

        return box;
    }
}