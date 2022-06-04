package Controller;

import Model.Countries;
import Model.Customer;
import Model.Division;
import Utils.*;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {

    /**
     * this static variable gets the customer information from the customer screen to populate the data for modification
     */
    public static Customer receivedCustomer;
    @FXML
    private ComboBox<Countries> countryCombo;
    @FXML
    private ComboBox<Division> divisionCombo;

    @FXML
    public TextField customerNameField;
    @FXML
    public TextField addressField;
    @FXML
    public TextField zipCodeField;
    @FXML
    public TextField phoneField;
    @FXML
    public Button saveButton;

    //Navigation
    /**
     * this method transitions the UI to the customer screen within the UI
     * @param event triggers the transition between screens
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    public void toCustomerScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/customerScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1250, 600);
        Stage stage1 = new Stage();
        stage1.setTitle("Customers");
        stage1.setScene(scene);
        stage1.show();
    }

    /**
     * this method saves the modified customer information to the DB
     * @param event triggers the UI to save the info to the DB
     * @throws SQLException provides information on an error accessing the DB
     */
    @FXML
    public void toSave(ActionEvent event) throws SQLException {
        if (!validSelect(customerNameField.getText())) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please enter a customer name");
            alert1.showAndWait();
        }else if(!validSelect(addressField.getText())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please enter an address");
            alert1.showAndWait();
        }else if(!validSelect(zipCodeField.getText())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please enter an zip code");
            alert1.showAndWait();
        }else if(!validSelect(phoneField.getText())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please enter a phone number");
            alert1.showAndWait();
        }else if(!validSelect(countryCombo.getValue().toString())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select a country");
            alert1.showAndWait();
        }else if(!validSelect(divisionCombo.getValue().toString())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select a division");
            alert1.showAndWait();
        }else {

            try {
                if (CustomersQuery.updatedCustomer(receivedCustomer.getCustomer_ID(), customerNameField.getText(), addressField.getText(), zipCodeField.getText(), phoneField.getText(), divisionCombo.getValue().toString())) {

                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    stage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/customerScreen.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1250, 600);
                    Stage stage1 = new Stage();
                    stage1.setTitle("Customers");
                    stage1.setScene(scene);
                    stage1.show();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    //ComboBoxes
    /**
     * this method populates the countrycombo box with the customer's original country first, then allows for modifications
     * @throws SQLException provides information on an error accessing the DB
     */
    @FXML
    private void populateCountryCombo() throws SQLException {
        countryCombo.setItems(CountriesQuery.getCountries());
        Countries country = CountriesQuery.getCountryByDivision(receivedCustomer.getDivision_ID());
        countryCombo.setValue(country);
    }

    /**
     * This method populates the divisioncombo based on the country selected
     * @param event triggers the population to occur in the UI
     * @throws SQLException provides information on an error accessing the DB
     */
    @FXML
    public void populateDivisionCombo(ActionEvent event) throws SQLException {
            try {
            populateDivisionCombo();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Please select a country before attempting to select a division");
            Optional<ButtonType> button = alert.showAndWait();
            System.out.println(e);
        }
    }

    /**
     * this method populates the divisionCombo based on the previous customer's division
     */
    public void populateDivisionCombo() {
        try {
            System.out.println("Country Changed");
            int countryID = countryCombo.getValue().getCountry_ID();
            divisionCombo.setItems(DivisionQuery.getDivisionsByCountry(countryID));
            divisionCombo.getSelectionModel().clearSelection();
            divisionCombo.setValue(DivisionQuery.getDivision(receivedCustomer.getDivision_ID()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Methods
    /**
     * this method populates the modifycustomer screen with information from the previously selected customer
     * @param customer holds the customer information from the selected customer on the previous screen
     */
    @FXML
    public static void receiveCustomer(Customer customer) {
        receivedCustomer = customer;

    }

    /**
     * This method confirms that all textfields have a string or character (not blank)
     * @param input receives a string input to determine if the value is empty
     * @return returns true if the input provided is empty or false if the field contains any characters
     */
    public Boolean validSelect(String input) {
        if(input.isEmpty()){
           return false;
        }
        return true;
    }

    //Initialize
    /**
     *  This method initialize the controller
     * @param url points to a location of files or web files
     * @param resourceBundle a library used to return messages
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            populateCountryCombo();
            populateDivisionCombo();


            customerNameField.setText(receivedCustomer.getCustomer_Name());
            addressField.setText(receivedCustomer.getAddress());
            zipCodeField.setText(receivedCustomer.getPostal_Code());
            phoneField.setText(receivedCustomer.getPhone());

        } catch (Exception e){
            System.out.println(e);
        }



    }




}
