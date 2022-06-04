package Utils;

import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * this method queries the DB for countries or data pertaining to countries
 */
public class CountriesQuery {
    /**
     * this gets all countries in the DB
     * @return returns an array of all countries
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList<Countries> getCountries() throws SQLException {
        ObservableList<Countries> countriesArray = FXCollections.observableArrayList();
        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM countries;";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);

        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            int country_id = rs.getInt("Country_ID");
            String country = rs.getString("Country");

            Countries countries1 = new Countries(country_id, country);
            countriesArray.add(countries1);
        }
        return countriesArray;
    }

    /**
     * this method gets a country by the division id
     * @param divisionID specifies the division id
     * @return returns the country name for the given division id
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static Countries getCountryByDivision(int divisionID) throws SQLException {
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM countries as c INNER JOIN First_Level_Divisions as d on d.division_ID = ? and c.country_ID = d.country_ID";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, divisionID);
        preparedStatement.executeQuery();
        ResultSet rs = preparedStatement.getResultSet();

        while(rs.next()){
            int country_id = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");

            return new Countries(country_id, countryName);
        }
        return null;

    }

}
