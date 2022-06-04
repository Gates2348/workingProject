package Utils;

import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * this is the contact query class it queries the DB for contacts
 */
public class ContactsQuery {
    /**
     * gets all contacts from the DB
     * @return returns an array of all contacts
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList<Contacts> getContacts() throws SQLException {
        ObservableList<Contacts> contactsArray = FXCollections.observableArrayList();
        //Open DB connection
        Connection connection = DBConnection.openConnection();


        String statement = "SELECT * FROM contacts";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            int contact_ID = rs.getInt("Contact_ID");
            String contact_Name = rs.getNString("Contact_Name");
            String email = rs.getNString("Email");

            Contacts contacts1 = new Contacts(contact_ID, contact_Name, email);
            contactsArray.addAll(contacts1);
        }
        return contactsArray;
    }


}
