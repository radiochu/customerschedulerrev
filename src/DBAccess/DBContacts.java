package DBAccess;

import DBConnection.JDBC;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Db contacts.
 */
public class DBContacts {

    /**
     * The Contacts.
     */
    static final ObservableList<String> contacts = FXCollections.observableArrayList();

    /**
     * Gets all contacts.
     *
     * @return the all contacts
     */
    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> contacts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Contact_Name, contact_id FROM contacts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            contacts.clear();
            while (rs.next()) {
                Contacts c = new Contacts(rs.getString(1), rs.getInt(2));
                contacts.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacts;
    }

    /**
     * Gets contact name by id.
     *
     * @param apptContact the appt contact
     * @return the contact name by id
     */
    public static Contacts getContactByID(int apptContact) {
        String contactName = null;
        int contactID = 0;
        Contacts c = null;
        try {
            String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, apptContact);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                contactName = rs.getString("Contact_Name");
                contactID = rs.getInt("contact_id");
                c = new Contacts(contactName, contactID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Gets contact id by name.
     *
     * @param contactName the contact name
     * @return the contact id by name
     */
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

    public static ObservableList<String> getScheduleByContact() {
        ObservableList<String> contactAppts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT c.contact_name, a.start, a.end, a.appointment_id, a.title FROM contacts as c, appointments as a WHERE c.contact_id = a.contact_id ORDER BY c.contact_name, a.start";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                contactAppts.add(rs.getString(1) + " - " + rs.getTimestamp(2) + " to " + rs.getTimestamp(3) + " - Appointment ID " + rs.getInt(4) + " - " + rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(contactAppts);
        return contactAppts;
    }
}
