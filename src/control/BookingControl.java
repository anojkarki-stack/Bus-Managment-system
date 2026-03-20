package control;

import modeel.DbConnectionTest;
import modeel.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookingControl {

    public void saveTicket(Ticket t) {

        try {
            Connection conn = DbConnectionTest.getConnection();

            if (conn == null) {
                System.out.println("Database connection failed!");
                return;
            }

            String sql = "INSERT INTO tickets(name, route, seat, price, payment) VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, t.getName());
            ps.setString(2, t.getRoute());
            ps.setString(3, t.getSeat());
            ps.setInt(4, t.getPrice());
            ps.setString(5, t.getPayment());

            ps.executeUpdate();

            ps.executeUpdate();

            System.out.println("✅ Ticket Saved Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}