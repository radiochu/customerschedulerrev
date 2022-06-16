package DBAccess;

import DBConnection.JDBC;
import Model.Customers;
import Utilities.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCustomers {
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> cList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT c.customer_id, " +
                    "c.customer_name, " +
                    "c.address, " +
                    "c.division_id, " +
                    "c.postal_code, " +
                    "c.phone, " +
                    "f.division, " +
                    "f.country_id, " +
                    "co.country " +
                    "FROM customers AS c " +
                    "INNER JOIN first_level_divisions AS f " +
                    "INNER JOIN countries AS co " +
                    "WHERE c.Division_ID = f.Division_ID AND f.country_id = co.Country_ID " +
                    "ORDER BY c.customer_id";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerFLD = rs.getString("Division");
                String customerPostCode = rs.getString("Postal_Code");
                String customerCountry = rs.getString("Country");
                String customerPhone = rs.getString("Phone");
                Customers c = new Customers(customerID, customerName, customerAddress, customerFLD, customerPostCode, customerCountry, customerPhone);
                cList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.print(cList);
        return cList;
    }

    public static int addCustomer(Customers customer) {
        int divisionID = DBDivisions.getDivisionIDByName(customer.getFld());
        int newCustID = 0;
        try {
            String sql = "INSERT INTO customers VALUES (NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPostCode());
            ps.setString(4, customer.getPhoneNumber());
            ps.setInt(5, divisionID);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            newCustID = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newCustID;
    }

    public static boolean deleteCustomer(int custID) {
        Boolean isDeleted = false;
        if (DBAppointments.getApptsByCustID(custID).size() != 0) {
            Alerts.existingAppts();
        }
        else {
            try {
                String sql = "DELETE FROM customers WHERE customer_id = ?";
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
                ps.setInt(1, custID);
                ps.execute();

                isDeleted = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isDeleted;
    }

    public static void modifyCustomer(Customers customerToMod) {
        int divisionID = DBDivisions.getDivisionIDByName(customerToMod.getFld());
        try {
            String sql = "UPDATE customers " +
                    "SET customer_name = ?, " +
                    "address = ?, " +
                    "postal_code = ?, " +
                    "phone = ?, " +
                    "division_id = ? " +
                    "WHERE customer_id = ?";                    ;
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, customerToMod.getName());
            ps.setString(2, customerToMod.getAddress());
            ps.setString(3, customerToMod.getPostCode());
            ps.setString(4, customerToMod.getPhoneNumber());
            ps.setInt(5, divisionID);
            ps.setInt(6, customerToMod.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean customerExists(int custID) {
        boolean b = false;
        try {
            String sql = "SELECT * FROM customers WHERE customer_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, custID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                b = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return b;
    }
}
