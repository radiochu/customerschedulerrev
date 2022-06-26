package DBAccess;


import DBConnection.JDBC;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Db divisions.
 */
public class DBDivisions {

    /**
     * Gets all divisions.
     *
     * @return the all divisions
     */
    public static ObservableList<Divisions> getAllDivisions() {
        ObservableList<Divisions> divisions = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Division, Country_id FROM first_level_divisions";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String divName = rs.getString(1);
                int countryID = rs.getInt(2);
                divisions.add(new Divisions(divName, countryID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisions;
    }

    /**
     * Gets us divisions.
     *
     * @return the us divisions
     */
    public static ObservableList<String> getUSDivisions() {
        ObservableList<String> USDivisions = FXCollections.observableArrayList();
        try {
            String sql = "Select Division FROM first_level_divisions WHERE country_id = 1";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                USDivisions.add(rs.getString("Division"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return USDivisions;
    }

    /**
     * Gets uk divisions.
     *
     * @return the uk divisions
     */
    public static ObservableList<String> getUKDivisions() {
        ObservableList<String> UKDivisions = FXCollections.observableArrayList();
        try {
            String sql = "Select Division FROM first_level_divisions WHERE country_id = 2";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UKDivisions.add(rs.getString("Division"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return UKDivisions;
    }

    /**
     * Gets canada divisions.
     *
     * @return the canada divisions
     */
    public static ObservableList<String> getCanadaDivisions() {
        ObservableList<String> canadaDivisions = FXCollections.observableArrayList();
        try {
            String sql = "Select Division FROM first_level_divisions WHERE country_id = 3";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                canadaDivisions.add(rs.getString("Division"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canadaDivisions;
    }

    public static ObservableList<Divisions> getDivisionsByCountry(int country_id) {
        ObservableList<Divisions> divisions = FXCollections.observableArrayList();
        try {
            String sql = "Select Division FROM first_level_divisions WHERE country_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, country_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String divName = rs.getString(1);
                int countryID = rs.getInt(2);
                divisions.add(new Divisions(divName, countryID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisions;
    }

    /**
     * Gets division id by name.
     *
     * @param divisionName the division name
     * @return the division id by name
     */
    public static int getDivisionIDByName(String divisionName) {
        int divID = 0;
        try {
            String sql = "SELECT division_id FROM first_level_divisions WHERE division = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, divisionName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                divID = rs.getInt("division_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divID;
    }
}