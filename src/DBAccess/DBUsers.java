package DBAccess;

import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Db users.
 */
public class DBUsers {

    /**
     * Gets all users.
     *
     * @return the all users
     */
    public static ObservableList<Integer> getAllUsers() {
        ObservableList<Integer> userIDs = FXCollections.observableArrayList();
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

    /**
     * Gets user id by name.
     *
     * @param username the username
     * @return the user id by name
     */
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