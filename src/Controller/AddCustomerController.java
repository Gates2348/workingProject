package Controller;

import Model.Countries;
import Model.Division;
import Utils.CountriesQuery;
import Utils.CustomersQuery;
import Utils.DivisionQuery;
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
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {


    @FXML
    private TextField customerName;
    @FXML
    private TextField address;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField phone;
    @FXML
    private ComboBox<Countries> countryCombo;
    @FXML
    private ComboBox<Division> divisionCombo;

    //Navigation Methods
    /**
     * This method navigates the UI to a screen listing all customer
     * @param event on Action even triggers the UI navigation
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    void toCustomerScreen(ActionEvent event) throws IOException {
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
     * This method saves the new customer to the DB
     * @param event triggers the save
     */
    @FXML
    public void toSave(ActionEvent event) {
        if (!validInput(customerName.getText())) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please enter a customer name");
            alert1.showAndWait();
        }else if(!validInput(address.getText())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please enter an address");
            alert1.showAndWait();
        }else if(!validInput(postalCode.getText())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please enter an zip code");
            alert1.showAndWait();
        }else if(!validInput(phone.getText())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please enter a phone number");
            alert1.showAndWait();
        }else if(!validInput(countryCombo.getValue().toString())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select a country");
            alert1.showAndWait();
        }else if(!validInput(divisionCombo.getValue().toString())){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select a division");
            alert1.showAndWait();
        }else {

            try {
                if (CustomersQuery.addCustomers(customerName.getText(), address.getText(), postalCode.getText(), phone.getText(), divisionCombo.getValue().toString())) {

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
                e.printStackTrace();
            }
        }

    }

    //Methods to Populate ComboBoxes
    /**
     * This method populates the countryComboBox with various countries that are in the DB
     * @throws SQLException provides information on an error accessing the DB
     */
    @FXML
    void populateCountryCombo() throws SQLException {
        countryCombo.setItems(CountriesQuery.getCountries());
    }

    /**
     * populates the divisionComboBox with specific divisions based on what country was selected
     * @param event triggers the box to be populated once a country is selected
     * @throws SQLException provides information on an error accessing the DB
     */
    @FXML
    public void populateDivisionCombo(ActionEvent event) throws SQLException {
        try {
            System.out.println("Country Changed");
            int countryID = countryCombo.getValue().getCountry_ID();
            divisionCombo.setItems(DivisionQuery.getDivisionsByCountry(countryID));
            divisionCombo.getSelectionModel().clearSelection();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Please select a country before attempting to select a division");
            Optional<ButtonType> button = alert.showAndWait();
            System.out.println(e);
        }
    }

    //Inout validation method
    /**
     * This method confirms that all textfields have a string or character (not blank)
     * @param input receives a string input to determine if the value is empty
     * @return returns true if the input provided is empty or false if the field contains any characters
     */
    public Boolean validInput(String input) {
        if(input.isEmpty()){
            return false;
        }
        return true;
    }

    //Initialize Method
    /**
     *  This method initialize the controller
     * @param url points to a location of files or web files
     * @param resourceBundle a library used to return messages
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateCountryCombo();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}

