package Controller;

import Main.Main;
import Model.Appointments;
import Model.Contacts;
import Model.Customer;
import Model.Users;
import Utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    public TextField titleField;
    public TextField descriptionField;
    public TextField locationField;
    public TextField typeField;

    public DatePicker startEndDate;
    public ComboBox<LocalTime> startTimeCombo;
    public ComboBox<LocalTime> endTimeCombo;
    public ComboBox<Integer> customerIDCombo;
    public ComboBox<Integer> contactIDCombo;
    public ComboBox<Integer> userIDCombo;
    public TextField appointmentID;

    /**
     *  retrieves input from all fields and adds to appointments screen as well as SQL table
     * @param event when mouse is clicked on button in UI this method is started
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    @FXML
    public void addAppointment(MouseEvent event) throws SQLException {
        if (!validSelect(titleField.getText())) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please enter a title");
            alert1.showAndWait();
        } else if (!validSelect(descriptionField.getText())) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please enter a description");
            alert1.showAndWait();
        } else if (!validSelect(locationField.getText())) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please enter a location");
            alert1.showAndWait();
        } else if (!validSelect(typeField.getText())) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please enter a type");
            alert1.showAndWait();
        } else if (startEndDate.getValue() == null) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select a Start/End Date");
            alert1.showAndWait();
        } else if (startTimeCombo.getValue() == null) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select a start time");
            alert1.showAndWait();
        } else if (endTimeCombo.getValue() == null) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select an end time");
            alert1.showAndWait();
        } else if (customerIDCombo.getValue() == null) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select a customerID");
            alert1.showAndWait();
        } else if (contactIDCombo.getValue() == null) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select a contactID");
            alert1.showAndWait();
        } else if (userIDCombo.getValue() == null) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select a userID");
            alert1.showAndWait();
        } else {


            try {
                LocalDateTime NAS = LocalDateTime.of(startEndDate.getValue(), startTimeCombo.getValue());
                LocalDateTime NAE = LocalDateTime.of(startEndDate.getValue(), endTimeCombo.getValue());

                if(!AppointmentsQuery.checkConflictingAppointments(NAS, NAE, customerIDCombo.getValue(), -1)){
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error");
                    alert1.setHeaderText("Overlapping Appointments");
                    alert1.showAndWait();
                    return;
                }

                if (AppointmentsQuery.addAppointment(titleField.getText(), descriptionField.getText(), locationField.getText(), typeField.getText(), NAS, NAE, customerIDCombo.getValue(), contactIDCombo.getValue(), userIDCombo.getValue())) {

                    //ADD IF ELSE TO SEE IF A APPOINTMENT EXISTS AT THE SAME TIME AND DATE


                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    stage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/appointmentsScreen.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1100, 500);
                    Stage stage1 = new Stage();
                    stage1.setTitle("Appointments");
                    stage1.setScene(scene);
                    stage1.show();

                    }
                } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     *  returns the UI screen from the Add Appointment screen back to the Appointments screen
     * @param event when mouse is clicked on button in UI this method is started
     */
    @FXML
    public void toAppointmentScreen(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/appointmentsScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 500);
        Stage stage1 = new Stage();
        stage1.setTitle("Appointments");
        stage1.setScene(scene);
        stage1.show();
    }

    //Populate ComboBoxex
    /**
     *  this method populates the CustomerID combo box in the UI
     * @param event when mouse is clicked on button in UI this method is started
     */
    @FXML
    public void populateCustomerIDCombo(MouseEvent event) {
        ObservableList<Customer> customerIDSearch;
        ObservableList<Integer> customerIds = FXCollections.observableArrayList();

        try {
            customerIDSearch = CustomersQuery.getCustomers();
            for (Customer customer1 : customerIDSearch) {
                customerIds.add(customer1.getCustomer_ID());
            }
            customerIDCombo.setItems(customerIds);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     *  this method populates the ContactID combo box in the UI
     * @param event when mouse is clicked on button in UI this method is started
     */
    @FXML
    public void populateContactIDBox(MouseEvent event) {
        ObservableList<Contacts> contactIDSearch;
        ObservableList <Integer> contactIDs = FXCollections.observableArrayList();

        try{
            contactIDSearch = ContactsQuery.getContacts();
            for(Contacts contacts : contactIDSearch){
                contactIDs.add(contacts.getContact_ID());
            }
            contactIDCombo.setItems(contactIDs);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     *  this method populates the UserID combo box in the UI
     * @param event when mouse is clicked on button in UI this method is started
     */
    @FXML
    public void populateUserIDBox(MouseEvent event) throws SQLException {
        ObservableList <Users> userSearch = UsersQuery.getUsers();
        ObservableList <Integer> userIDs = FXCollections.observableArrayList();

        for(Users users1 : userSearch){
            userIDs.add(users1.getUser_ID());
        }
        userIDCombo.setItems(userIDs);

    }

    /**
     * this method populates the StartDateTime combo box in the UI
     * @param event when mouse is clicked on button in UI this method is started
     */
    @FXML
    public void populateStartTimeCombo(MouseEvent event) {
        startTimeCombo.setItems(TimeHelper.getStartTimes());
        endTimeCombo.setItems(TimeHelper.getEndTimes());
    }

    /**
     *
     * @param input this validates that input fields aren't left empty
     * @return return a true or false (true if input fields contact data, false if they are empty)
     */
    public Boolean validSelect(String input) {
        if(input.isEmpty()){
            return false;
        }
        return true;
    }


    /**
     *  This method initialize the controller
     * @param url points to a location of files or web files
     * @param resourceBundle a library used to return messages
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }


}
