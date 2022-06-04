package Utils;


import Model.Countries;
import Model.Customer;
import Model.CustomerContact;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * this class queries the DB as related to customer data
 */
public class CustomersQuery {

    /**
     * this method retrieves all customers from the DB
     * @return returns an array of all customers
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList<Customer> getCustomers() throws SQLException {
        ObservableList<Customer> customerArray = FXCollections.observableArrayList();

        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM customers";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int getID = rs.getInt("Customer_ID");
            String getName = rs.getString("Customer_Name");
            String getAddress = rs.getString("Address");
            String getPostal_Code = rs.getString("Postal_Code");
            String getPhone = rs.getString("Phone");
            int getDivision_ID = rs.getInt("Division_ID");

            Customer c1 = new Customer(getID, getName, getAddress, getPostal_Code, getPhone, getDivision_ID);
            customerArray.addAll(c1);
        }

        return customerArray;
    }

    /**
     * this method updates customer information in the DB
     * @param customerID specifies the customer id of the customer to be updated
     * @param customerName specifies the name of the customer to be updated
     * @param address  specifies the address of the customer to be updated
     * @param zipCode specifies the zip code of the customer to be updated
     * @param phone specifies the phone of the customer to be updated
     * @param division specifies the division of the customer to be updated
     * @return returns a boolean either true if updated or false if failed
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static boolean updatedCustomer(int customerID, String customerName, String address, String zipCode, String phone, String division) throws SQLException {
        Division updatedDivision = DivisionQuery.getDivisionID(division);

        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String updateStatement = "UPDATE customers SET Customer_Name=?,Address=?,Postal_Code=?,Phone=?,Division_ID=? WHERE Customer_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);

        preparedStatement.setString(1, customerName);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, zipCode);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, updatedDivision.getDivision_ID());
        preparedStatement.setInt(6, customerID);

        try {
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        if (preparedStatement.getUpdateCount() > 0) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Success");
            alert1.setHeaderText("Customer was successfully updated");
            alert1.showAndWait();
            return true;
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText("Customer was not successfully updated");
            alert1.showAndWait();
        }


        return false;
    }

    /**
     * this method adds a customer to the db
     * @param customerName specifies the name of the customer to be added
     * @param address specifies the address of the customer to be added
     * @param zipCode specifies the zip code of the customer to be added
     * @param phone specifies the phone of the customer to be added
     * @param division specifies the division of the customer to be added
     * @return returns a boolean either true if added or false if failed
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static boolean addCustomers(String customerName, String address, String zipCode, String phone, String division)throws SQLException {
        Division updatedDivision = DivisionQuery.getDivisionID(division);

         Connection connection = DBConnection.openConnection();

         String insertStatement = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
         PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
         preparedStatement.setString(1, insertStatement);

         LocalDateTime s = LocalDateTime.now();



         preparedStatement.setString(1, customerName);
         preparedStatement.setString(2, address);
         preparedStatement.setString(3, zipCode);
         preparedStatement.setString(4, phone);
         preparedStatement.setString(5, String.valueOf(s));
         preparedStatement.setString(6, "Admin");
         preparedStatement.setString(7, String.valueOf(s));
         preparedStatement.setString(8, "Admin");
         preparedStatement.setInt(9, updatedDivision.getDivision_ID());


        try {
            preparedStatement.execute();

        }catch(Exception e){
            System.out.println(e);
        }

        if (preparedStatement.getUpdateCount() > 0) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Success");
            alert1.setHeaderText("Customer was successfully added");
            alert1.showAndWait();
            return true;
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText("Customer was not successfully added");
            alert1.showAndWait();
        }

        return false;
    }

    /**
     * this method deletes selected customer
     * @param customerID specifies which customer is to be deleted
     * @return returns a boolean either true if deleted or false if failed
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static boolean deleteCustomer(int customerID) throws SQLException {
        Connection connection = DBConnection.openConnection();

        String deleteStatement = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
        preparedStatement.setString(1, deleteStatement);
        preparedStatement.setInt(1, customerID);

        try {
            preparedStatement.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
        if (preparedStatement.getUpdateCount() > 0) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Success");
            alert1.setHeaderText("Customer was successfully deleted");
            alert1.showAndWait();
            return true;
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText("Customer was not successfully deleted");
            alert1.showAndWait();
        }



        return false;
    }

    /**
     * this method gets customer name by customer id
     * @param customerID specifies the id of the customer whose name to retrieve from the DB
     * @return returns the customers name
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static Customer getCustomerByID(int customerID) throws SQLException {
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM customer WHERE Customer_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, customerID);
        preparedStatement.executeQuery();
        ResultSet rs = preparedStatement.getResultSet();

        while (rs.next()) {
            int getID = rs.getInt("Customer_ID");
            String getName = rs.getString("Customer_Name");
            String getAddress = rs.getString("Address");
            String getPostal_Code = rs.getString("Postal_Code");
            String getPhone = rs.getString("Phone");
            int getDivision_ID = rs.getInt("Division_ID");

            Customer c1 = new Customer(getID, getName, getAddress, getPostal_Code, getPhone, getDivision_ID);
            return c1;
        }

        return null;


    }

    /**
     * this method gets customer contact info from the DB
     * @return returns an array containing customer contact info
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList <CustomerContact> getcustomerContactReport() throws SQLException {
        ObservableList<CustomerContact> customerContactArray = FXCollections.observableArrayList();

        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM customers";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int getID = rs.getInt("Customer_ID");
            String getName = rs.getString("Customer_Name");
            String getAddress = rs.getString("Address");
            String getPostal_Code = rs.getString("Postal_Code");
            String getPhone = rs.getString("Phone");

            CustomerContact custContact1 = new CustomerContact(getID, getName, getAddress, getPostal_Code, getPhone);
            customerContactArray.addAll(custContact1);
        }

        return customerContactArray;


    }


}


