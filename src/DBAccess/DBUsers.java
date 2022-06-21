package DBAccess;

import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers {
    public static ObservableList<Integer> userIDs = FXCollections.observableArrayList();

    public static ObservableList<Integer> getAllUsers() {
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("User_ID");
                userIDs.add(userID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIDs;
    }

    public static int getUserIDByName(String username) {
        int userID = 0;
        try {
            String sql = "SELECT * FROM users WHERE user_name = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userID = rs.getInt("User_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userID;
    }
}