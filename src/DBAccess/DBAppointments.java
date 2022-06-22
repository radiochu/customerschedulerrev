package DBAccess;

import DBConnection.JDBC;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DBAppointments {

    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> aList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDescription = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                int apptContact = rs.getInt("Contact_ID");
                String apptType = rs.getString("Type");
                LocalDateTime apptStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEnd = rs.getTimestamp("End").toLocalDateTime();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");



                Appointments a = new Appointments(apptID, apptTitle, apptDescription, apptLocation, DBContacts.getContactNameByID(apptContact), apptStart, apptEnd, apptType, custID, userID);
                aList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aList;
    }

    public static ObservableList<Appointments> getThisMonthAppts() {
        ObservableList<Appointments> currMonthAppts = FXCollections.observableArrayList();
        try {
            String sql = ("SELECT * FROM appointments WHERE MONTH(start) = MONTH(CURRENT_DATE()) AND YEAR(start) = YEAR(CURRENT_DATE())");
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDescription = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                int apptContact = rs.getInt("Contact_ID");
                String apptType = rs.getString("Type");
                LocalDateTime apptStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEnd = rs.getTimestamp("End").toLocalDateTime();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");

                Appointments a = new Appointments(apptID, apptTitle, apptDescription, apptLocation, DBContacts.getContactNameByID(apptContact), apptStart, apptEnd, apptType, custID, userID);
                currMonthAppts.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currMonthAppts;
    }

    public static ObservableList<Appointments> getThisWeekAppts() {
        ObservableList<Appointments> currWeekAppts = FXCollections.observableArrayList();
        try {
            String sql = ("SELECT * FROM appointments WHERE WEEK(start) = WEEK(CURRENT_DATE()) AND YEAR(start) = YEAR(CURRENT_DATE())");
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDescription = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                int apptContact = rs.getInt("Contact_ID");
                String apptType = rs.getString("Type");
                LocalDateTime apptStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEnd = rs.getTimestamp("End").toLocalDateTime();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");

                Appointments a = new Appointments(apptID, apptTitle, apptDescription, apptLocation, DBContacts.getContactNameByID(apptContact), apptStart, apptEnd, apptType, custID, userID);
                currWeekAppts.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currWeekAppts;
    }

    public static ObservableList<Appointments> getApptsByUserID(int user) {
        ObservableList<Appointments> userAppts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments WHERE user_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, user);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDescription = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                int apptContact = rs.getInt("Contact_ID");
                String apptType = rs.getString("Type");
                LocalDateTime apptStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEnd = rs.getTimestamp("End").toLocalDateTime();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");

                Appointments a = new Appointments(apptID, apptTitle, apptDescription, apptLocation, DBContacts.getContactNameByID(apptContact), apptStart, apptEnd, apptType, custID, userID);
                userAppts.add(a);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userAppts;
    }

    public static ObservableList<Appointments> getApptsByCustID(int customerID) {
        ObservableList<Appointments> custAppts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments WHERE customer_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String apptTitle = rs.getString("Title");
                String apptDescription = rs.getString("Description");
                String apptLocation = rs.getString("Location");
                int apptContact = rs.getInt("Contact_ID");
                String apptType = rs.getString("Type");
                LocalDateTime apptStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime apptEnd = rs.getTimestamp("End").toLocalDateTime();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");

                Appointments a = new Appointments(apptID, apptTitle, apptDescription, apptLocation, DBContacts.getContactNameByID(apptContact), apptStart, apptEnd, apptType, custID, userID);
                custAppts.add(a);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return custAppts;
    }

    public static void modifyAppointment(Appointments appointmentToMod) {
        try {
            String sql = "UPDATE appointments " +
                         "SET title = ?, " +
                         "description = ?, " +
                         "location = ?, " +
                         "type = ?, " +
                         "start = ?, " +
                         "end = ?, " +
                         "customer_id = ?, " +
                         "user_id = ?, " +
                         "contact_id = ? " +
                         "WHERE appointment_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, appointmentToMod.getApptTitle());
            ps.setString(2, appointmentToMod.getApptDescription());
            ps.setString(3, appointmentToMod.getApptLocation());
            ps.setString(4, appointmentToMod.getApptType());
            ps.setTimestamp(5, Timestamp.valueOf(appointmentToMod.getApptStart()));
            ps.setTimestamp(6, Timestamp.valueOf(appointmentToMod.getApptEnd()));
            ps.setInt(7, appointmentToMod.getCustID());
            ps.setInt(8, appointmentToMod.getUserID());
            ps.setInt(9, DBContacts.getContactIDByName(appointmentToMod.getApptContact()));
            ps.setInt(10, appointmentToMod.getApptID());
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean deleteAppointment(int apptID) {
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM appointments WHERE appointment_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, apptID);
            ps.execute();

            isDeleted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }

    public static void deleteApptsByCust(int custID) {
        boolean isDeleted = false;
        try {
            String sql = "DELETE FROM appointments WHERE customer_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, custID);
            ps.execute();

            isDeleted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
