package salinanproject.carefund.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:project.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Koneksi berhasil ke SQLite");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static String getEmailByUsername(String username) {
        String email = null;
        String sql = "SELECT email FROM user WHERE username = ?";

        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return email;
    }
}
