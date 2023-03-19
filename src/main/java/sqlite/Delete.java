package sqlite;

import java.sql.*;

public class Delete {
    private Connection connect() {
        String url = "jdbc:sqlite:sq.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll() {

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
             stmt.execute("DELETE from Users");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        Delete app = new Delete();
        app.selectAll();
    }
}
