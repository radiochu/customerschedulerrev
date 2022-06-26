package DBAccess;

import DBConnection.JDBC;
import Model.Appointments;
import Model.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * The type Db appointments.
 */
public class DBAppointments {

    /**
     * Gets all appointments.
     *
     * @return the all appointments
     */
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


                Appointments a = new Appointments(apptID, apptTitle, apptDescription, apptLocation, DBContacts.getContactByID(apptContact), apptStart, apptEnd, apptType, custID, userID);
                aList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aList;
    }

    /**
     * Gets this month appts.
     *
     * @return the this month appts
     */
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

                Appointments a = new Appointments(apptID, apptTitle, apptDescription, apptLocation, DBContacts.getContactByID(apptContact), apptStart, apptEnd, apptType, custID, userID);
                currMonthAppts.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currMonthAppts;
    }

    /**
     * Gets this week appts.
     *
     * @return the this week appts
     */
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

                Appointments a = new Appointments(apptID, apptTitle, apptDescription, apptLocation, DBContacts.getContactByID(apptContact), apptStart, apptEnd, apptType, custID, userID);
                currWeekAppts.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currWeekAppts;
    }

    /**
     * Gets appts by user id.
     *
     * @param user the user
     * @return the appts by user id
     */
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

                Appointments a = new Appointments(apptID, apptTitle, apptDescription, apptLocation, DBContacts.getContactByID(apptContact), apptStart, apptEnd, apptType, custID, userID);
                userAppts.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAppts;
    }

    /**
     * Gets appts by cust id.
     *
     * @param customerID the customer id
     * @return the appts by cust id
     */
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

                Appointments a = new Appointments(apptID, apptTitle, apptDescription, apptLocation, DBContacts.getContactByID(apptContact), apptStart, apptEnd, apptType, custID, userID);
                custAppts.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return custAppts;
    }

    public static ObservableList<Appointments> getApptsByContact(int contactID) {
        ObservableList<Appointments> contactAppts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments WHERE contact_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, contactID);
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

                Appointments a = new Appointments(apptID, apptTitle, apptDescription, apptLocation, DBContacts.getContactByID(apptContact), apptStart, apptEnd, apptType, custID, userID);
                contactAppts.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactAppts;
    }

    public static int addAppointment(Appointments appointmentToAdd) {
        int newApptID = 0;
        try {
            String sql = "INSERT INTO appointments VALUES (NULL, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, appointmentToAdd.getApptTitle());
            ps.setString(2, appointmentToAdd.getApptDescription());
            ps.setString(3, appointmentToAdd.getApptLocation());
            ps.setString(4, appointmentToAdd.getApptType());
            ps.setTimestamp(5, Timestamp.valueOf(appointmentToAdd.getApptStart()));
            ps.setTimestamp(6, Timestamp.valueOf(appointmentToAdd.getApptEnd()));
            ps.setInt(7, appointmentToAdd.getCustID());
            ps.setInt(8, appointmentToAdd.getUserID());
            ps.setInt(9, appointmentToAdd.getApptContact().getId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            newApptID = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newApptID;
    }

    /**
     * Modify appointment.
     *
     * @param appointmentToMod the appointment to mod
     */
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
            ps.setInt(9, appointmentToMod.getApptContact().getId());
            ps.setInt(10, appointmentToMod.getApptID());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Delete appointment boolean.
     *
     * @param apptID the appt id
     * @return the boolean
     */
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

    /**
     * Delete appts by cust.
     *
     * @param custID the cust id
     */
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

    public static ObservableList<String> getApptsByMonth() {
        ObservableList<String> apptsByMonth = FXCollections.observableArrayList();
        for (int i = 1; i <= 12; i++) {
            try {
                String sql = "SELECT MONTHNAME(start) AS Month, YEAR(start) AS Year, COUNT(appointment_id) AS Total FROM appointments WHERE MONTH(start) = ?";
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
                ps.setInt(1, i);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    if (rs.getString(1) != null) {
                        apptsByMonth.add(rs.getString(1) + " " + rs.getString(2) + " - " + rs.getString(3) + "\n");
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return apptsByMonth;
    }

    public static ObservableList<Types> getApptTypesByMonth() {
        ObservableList<Types> apptTypesByMonth = FXCollections.observableArrayList();
        for (int i = 1; i <= 12; i++) {
            try {
                String sql = "SELECT CONCAT(MONTHNAME(start), ' ', YEAR(start)) as Month, Type, COUNT(type) AS Total FROM appointments WHERE MONTH(start) = ? GROUP BY type;";
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
                ps.setInt(1, i);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String month = rs.getString(1);
                    String type = rs.getString(2);
                    int count = rs.getInt(3);
                    Types newType = new Types(month, type, count);
                    apptTypesByMonth.add(newType);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return apptTypesByMonth;
    }
}
