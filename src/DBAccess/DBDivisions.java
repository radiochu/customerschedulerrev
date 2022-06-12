package DBAccess;


import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivisions {

    public static ObservableList<String> getAllDivisions() {
        ObservableList<String> divisions = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Division FROM first_level_divisions";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                divisions.add(rs.getString("Division"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisions;
    }

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