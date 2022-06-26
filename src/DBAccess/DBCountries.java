package DBAccess;

import DBConnection.JDBC;
import Model.Countries;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Db countries.
 */
public class DBCountries {
    /**
     * Gets all countries.
     *
     * @return the all countries
     */
    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countries = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Country, country_id FROM countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String countryName = rs.getString(1);
                int countryID = rs.getInt(2);
                countries.add(new Countries(countryName, countryID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    /**
     * Gets country id by name.
     *
     * @param countryName the country name
     * @return the country id by name
     */
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
