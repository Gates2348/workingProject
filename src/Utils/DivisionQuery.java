package Utils;

import Model.Countries;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Division query queries the DB division table
 */
public class DivisionQuery {
    /**
     * this method gets all divisions from the DB
     * @return returns an array containing all division
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList<Division> getDivisions() throws SQLException {
        ObservableList<Division> divisionsArray = FXCollections.observableArrayList();
        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM client_schedule.first_level_divisions;";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int division_ID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int country_ID = rs.getInt("Country_ID");

            Division division1 = new Division(division_ID, division, country_ID);
            divisionsArray.addAll(division1);
        }
        return divisionsArray;
    }

    /**
     * this method gets a division by country id
     * @param CountryID tells DB which country to get division for
     * @return returns an array of all divisions by specified country id
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList<Division> getDivisionsByCountry(int CountryID) throws SQLException {
        ObservableList<Division> divisionsArray = FXCollections.observableArrayList();
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM client_schedule.first_level_divisions WHERE Country_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, CountryID);
        preparedStatement.executeQuery();
        ResultSet rs = preparedStatement.getResultSet();

        while (rs.next()) {
            String division_Name = rs.getString("Division");
            int division_ID = rs.getInt("Division_ID");
            int country_ID = rs.getInt("Country_ID");

            Division division1 = new Division(division_ID, division_Name, country_ID);
            divisionsArray.add(division1);
        }
        return divisionsArray;

    }

    /**
     * this method gets divisions by division ID
     * @param divisionID specifies to the DB what to search by
     * @return returns an array of the division linked to the specified division id
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static Division getDivision(int divisionID) throws SQLException {
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM client_schedule.first_level_divisions WHERE Division_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, divisionID);
        preparedStatement.executeQuery();
        ResultSet rs = preparedStatement.getResultSet();

        while (rs.next()) {
            String division_Name = rs.getString("Division");
            int division_ID = rs.getInt("Division_ID");
            int country_ID = rs.getInt("Country_ID");

            Division division1 = new Division(division_ID, division_Name, country_ID);
            return division1;
        }
        return null;

    }

    /**
     * this method gets division id by division name
     * @param division specifies which division to search for the id of
     * @return returns an array for the division id of the specified division name
     * @throws SQLException
     */
    public static Division getDivisionID(String division) throws SQLException {

        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM client_schedule.first_level_divisions WHERE Division = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, division);
        preparedStatement.executeQuery();
        ResultSet rs = preparedStatement.getResultSet();

        while (rs.next()) {
            String divisionName = rs.getString("Division");
            int division_ID = rs.getInt("Division_ID");
            int country_ID = rs.getInt("Country_ID");

            Division division2 = new Division(division_ID, divisionName, country_ID);
            return division2;
        }
        return null;
    }
}



