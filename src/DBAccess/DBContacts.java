package DBAccess;

import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {

    static ObservableList<String> contacts = FXCollections.observableArrayList();

    public static ObservableList<String> getAllContacts() {
        try {
            String sql = "SELECT Contact_Name FROM contacts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            contacts.clear();
            while (rs.next()) {
                contacts.add(rs.getString("Contact_Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacts;
    }

    public static String getContactNameByID(int apptContact) {
        String contactName = null;
        try {
            String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, apptContact);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                contactName = rs.getString("Contact_Name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactName;
    }

    public static int getContactIDByName(String contactName) {

        int contactID = 0;
        try {
            String sql = "SELECT * FROM contacts WHERE Contact_Name = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, contactName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                contactID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactID;

    }
}
