package DBAccess;

import DBConnection.JDBC;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
import Utilities.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The type Db customers.
 */
public class DBCustomers {
    /**
     * Gets all customers.
     *
     * @return the all customers
     */
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
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerFLD = rs.getString("Division");
                String customerPostCode = rs.getString("Postal_Code");
                String countryName = rs.getString("Country");
                int countryID = rs.getInt("Country_Id");
                String customerPhone = rs.getString("Phone");
                Countries customerCountry = new Countries(countryName,  countryID);
                Divisions customerDivision = new Divisions(customerFLD, countryID);
                Customers c = new Customers(customerID, customerName, customerAddress, customerDivision, customerPostCode, customerCountry, customerPhone);
                cList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cList;
    }

    /**
     * Add customer int.
     *
     * @param customer the customer
     * @return the int
     */
    public static int addCustomer(Customers customer) {
        int divisionID = DBDivisions.getDivisionIDByName(customer.getFld().getDivName());
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

    /**
     * Delete customer boolean.
     *
     * @param custID the cust id
     * @return the boolean
     */
    public static boolean deleteCustomer(int custID) {
        boolean isDeleted = false;
        if (DBAppointments.getApptsByCustID(custID).size() != 0) {
            Alerts.existingAppts();
        } else {
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

    /**
     * Modify customer.
     *
     * @param customerToMod the customer to mod
     */
    public static void modifyCustomer(Customers customerToMod) {
        int divisionID = DBDivisions.getDivisionIDByName(customerToMod.getFld().getDivName());
        try {
            String sql = "UPDATE customers " +
                    "SET customer_name = ?, " +
                    "address = ?, " +
                    "postal_code = ?, " +
                    "phone = ?, " +
                    "division_id = ? " +
                    "WHERE customer_id = ?";
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

    /**
     * Customer exists boolean.
     *
     * @param custID the cust id
     * @return the boolean
     */
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return b;
    }

    /**
     * Gets customer name by id.
     *
     * @param id the id
     * @return the customer name by id
     */
    public static String getCustomerNameByID(int id) {
        String custName = null;
        try {
            String sql = "SELECT * FROM customers WHERE customer_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                custName = rs.getString("customer_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return custName;
    }


    /**
     * Update customer name.
     *
     * @param newValue   the new value
     * @param customerID the customer id
     */
    public static void updateCustomerName(String newValue, int customerID) {
        try {
            String sql = "UPDATE customers SET customer_name = ? WHERE customer_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, newValue);
            ps.setInt(2, customerID);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets customer by id.
     *
     * @param indexToMod the index to mod
     * @return the customer by id
     */
    public static Customers getCustomerByID(int indexToMod) {
        Customers c = null;
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
                    "WHERE customer_id = ? AND c.Division_ID = f.Division_ID AND f.country_id = co.Country_ID " +
                    "ORDER BY c.customer_id";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, indexToMod);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerFLD = rs.getString("Division");
                String customerPostCode = rs.getString("Postal_Code");
                String countryName = rs.getString("Country");
                int countryID = rs.getInt("country_id");
                String customerPhone = rs.getString("Phone");
                Countries customerCountry = new Countries(countryName,  countryID);
                Divisions customerDivision = new Divisions(customerFLD, countryID);
                c = new Customers(customerID, customerName, customerAddress, customerDivision, customerPostCode, customerCountry, customerPhone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    /**
     * Validate customer boolean.
     *
     * @param custID the cust id
     * @return the boolean
     */
    public boolean validateCustomer(int custID) {
        boolean b = false;
        if (customerExists(custID)) {
            b = true;
        } else {
            Alerts.invalidData("\nThe chosen customer does not exist.\n");
        }
        return b;
    }
}
