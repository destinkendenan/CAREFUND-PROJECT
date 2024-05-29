package carefund.project.controller;

import carefund.project.config.DatabaseConnection;
import carefund.project.model.History;
import carefund.project.model.User;
// import carefund.project.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarefundController {
    private User loggedInUser;

    public void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user ("
                + " username TEXT NOT NULL,"
                + " email TEXT NOT NULL,"
                + " password TEXT NOT NULL"
                + ");";
        try (Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabel user berhasil dibuat.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createHistoryTable() {
        String sql = "CREATE TABLE IF NOT EXISTS history ("
                + " yayasan TEXT NOT NULL,"
                + " nominal REAL NOT NULL,"
                + " metode TEXT NOT NULL"
                + ");";
        try (Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabel history berhasil dibuat.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        String sql = "SELECT username, email, password FROM user WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                loggedInUser = new User(
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password") // Consider hashing and not storing in plain text
                );
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void logout() {
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean cekUser(String username) {
        String sql = "SELECT username FROM user WHERE username = ?";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void register(String username, String email, String password) {
        createUserTable();
        String sql = "INSERT INTO user(username, email, password) VALUES(?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            System.out.println("Data user berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void donate(String yayasan, double nominal, String metode) {
        createHistoryTable();
        String sql = "INSERT INTO history(yayasan, nominal, metode) VALUES(?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, yayasan);
            pstmt.setDouble(2, nominal);
            pstmt.setString(3, metode);
            pstmt.executeUpdate();
            System.out.println("Data history berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // public ObservableList<User> selectAll() {
    // String sql = "SELECT username, email, password FROM user";
    // ObservableList<User> data = FXCollections.observableArrayList();

    // try (Connection conn = DatabaseConnection.connect();
    // Statement stmt = conn.createStatement();
    // ResultSet rs = stmt.executeQuery(sql)) {

    // while (rs.next()) {
    // User user = new User(
    // rs.getString("username"),
    // rs.getString("email"),
    // rs.getString("password")
    // );
    // data.add(user);
    // }
    // } catch (SQLException e) {
    // System.out.println(e.getMessage());
    // }
    // return data;
    // }

    public ObservableList<History> selectAll2() {
        String sql = "SELECT * FROM history";
        ObservableList<History> data = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                History history = new History(
                        rs.getString("yayasan"),
                        rs.getDouble("nominal"),
                        rs.getString("metode"));
                data.add(history);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public void update(String username, String email, String password) {
        String sql = "UPDATE user SET username = ?, password = ? WHERE email = ?";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
