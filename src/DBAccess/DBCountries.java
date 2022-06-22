package DBAccess;

import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountries {
    public static ObservableList<String> getAllCountries() {
        ObservableList<String> countries = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Country FROM countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                countries.add(rs.getString("Country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    public static int getCountryIDByName(String countryName) {
        int countryID = 0;
        try {
            String sql = "SELECT country_id FROM countries WHERE country = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, countryName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                countryID = rs.getInt("country_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countryID;
    }
}
