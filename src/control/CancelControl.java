package control;

import modeel.DbConnectionTest;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CancelControl {

    public void cancelTicket(int id) {

        try {
            Connection conn = DbConnectionTest.getConnection();

            String sql = "UPDATE tickets SET status='CANCELLED' WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}