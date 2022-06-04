package Utils;

import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * this class queries the DB relating to user data
 */
public class UsersQuery {
    /**
     * this method retrieves all user from the DB
     * @return returns an array of all users in the DB
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList<Users> getUsers() throws SQLException {
        ObservableList<Users> usersArray = FXCollections.observableArrayList();
        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.execute(statement);
        ResultSet rs = preparedStatement.getResultSet();

        while(rs.next()){
            int user_ID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");

            Users users1 = new Users(user_ID, userName);
            usersArray.addAll(users1);
        }
        return usersArray;
    }

    /**
     * this method validated username and password are in the DB and match inputs
     * @param user specifies the user name of the person logging in
     * @param password specifies the password of the person logging in
     * @return returns a boolean either true if the information matches or false if the information doesn't match
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static boolean validateUserNamePassword(String user, String password) throws SQLException {
        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM users WHERE User_Name = ? and Password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, password);

        preparedStatement.executeQuery();
        ResultSet rs = preparedStatement.getResultSet();

        //adjust
        while(rs.next()) {
            String userName = rs.getString("User_Name");
            String password1 = rs.getString("Password");
            if(userName.equals(user) && password1.equals(password)){
                return true;
            }
        }


        return false;
    }



}
