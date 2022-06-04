package Controller;

import Main.Main;
import Model.*;
import Utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class ModifyAppointmentController implements Initializable {
    /**
     * This static variable holds the selected Appointment information on the previous screen
     */
    private static Appointments receivedAppointment;
    @FXML
    public TextField appointmentIDField;
    @FXML
    public TextField titleTextBox;
    @FXML
    public TextField descriptionField;
    @FXML
    public TextField locationField;
    @FXML
    public TextField typeField;

    @FXML
    public ComboBox <LocalTime> startTimeComboBox;
    @FXML
    public ComboBox <LocalTime> endTimeCombo;
    @FXML
    public ComboBox <Integer> customerIDCombo;
    @FXML
    public ComboBox <Integer> userIdCombo;
    @FXML
    public ComboBox <Integer> contactIdComboBox;
    @FXML
    public DatePicker startEndDateCombo;

    //NAVIGATION

    /**
     * this method takes the UI back to the appointment screen
     * @param event triggers the transition of switching UI screens to occur
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    public void toAppointmentScreen(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/appointmentsScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 500);
        Stage stage = new Stage();
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * this methods receives the original appointment for modifications
     * @param appointment original appointment information to be populated here for modification
     */
    @FXML
    public static void ReceivedAppointment(Appointments appointment) {
        receivedAppointment = appointment;
    }

    /**
     * this method saves the modified appointment to the DB
     * @param event triggers the appointment to be saved
     */
    @FXML
    public void toSave(ActionEvent event) {
        if (!validSelect(titleTextBox.getText())) {
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
        } else if (startEndDateCombo.getValue() == null) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select a Start/End Date");
            alert1.showAndWait();
        } else if (startTimeComboBox.getValue() == null) {
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
        } else if (contactIdComboBox.getValue() == null) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select a contactID");
            alert1.showAndWait();
        } else if (userIdCombo.getValue() == null) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Warning");
            alert1.setHeaderText("Please select a userID");
            alert1.showAndWait();
        } else {


            try {
                LocalDateTime start = LocalDateTime.of(startEndDateCombo.getValue(), startTimeComboBox.getValue());
                LocalDateTime end = LocalDateTime.of(startEndDateCombo.getValue(), endTimeCombo.getValue());

                if(!AppointmentsQuery.checkConflictingAppointments(start, end, customerIDCombo.getValue(), -1)){
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error");
                    alert1.setHeaderText("Overlapping Appointments");
                    alert1.showAndWait();
                    return;
                }

                if (AppointmentsQuery.updateAppointment(titleTextBox.getText(), descriptionField.getText(), locationField.getText(), typeField.getText(), start, end, customerIDCombo.getValue(), contactIdComboBox.getValue(), userIdCombo.getValue(),receivedAppointment.getAppointment_ID() )) {

                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    stage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/appointmentsScreen.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1100, 500);
                    Stage stage1 = new Stage();
                    stage1.setTitle("Appointments");
                    stage1.setScene(scene);
                    stage1.show();

                } else {
                    System.out.println("Appointment wasn't added");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    //populate ComboBoxes
    /**
     * this method populates the customer Combo box with the received appointment's customerID
     * @param event triggers ID to populate
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
     * this method populates the StartTimeCombo box with the received appointment start Date/Time
     * @param event triggers all times to populate
     */
    @FXML
    public void populateStartTimeCombo(MouseEvent event) {
        startTimeComboBox.setItems(TimeHelper.getStartTimes());
        endTimeCombo.setItems(TimeHelper.getEndTimes());
    }

    /**
     * this method populates the StartTimeCombo box with the received appointment start Date/Time
     * @param event triggers the population to occur
     */
    @FXML
    public void populateContactIDCombo(MouseEvent event) {
        ObservableList<Contacts> contactIDSearch;
        ObservableList <Integer> contactIDs = FXCollections.observableArrayList();

        try{
            contactIDSearch = ContactsQuery.getContacts();
            for(Contacts contacts : contactIDSearch){
                contactIDs.add(contacts.getContact_ID());
            }
            contactIdComboBox.setItems(contactIDs);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * this method populates the User ID Combo box
     * @param event triggers the population to occur
     * @throws SQLException provides information on an error accessing the DB
     */
    @FXML
    public void populateUserIDCombo(MouseEvent event) throws SQLException {
        ObservableList <Users> userSearch = UsersQuery.getUsers();
        ObservableList <Integer> userIDs = FXCollections.observableArrayList();

        for(Users users1 : userSearch){
            userIDs.add(users1.getUser_ID());
        }
        userIdCombo.setItems(userIDs);
    }

    //METHODS

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
    public void initialize(URL url, ResourceBundle resourceBundle){
            //Populate TextFields
            appointmentIDField.setPromptText(String.valueOf(receivedAppointment.getAppointment_ID()));
            titleTextBox.setText(receivedAppointment.getTitle());
            descriptionField.setText(receivedAppointment.getDescription());
            locationField.setText(receivedAppointment.getLocation());
            typeField.setText(receivedAppointment.getType());
            //Populate DatePicker
            startEndDateCombo.setValue(receivedAppointment.getStart().toLocalDate());

            //Populate Time ComboBoxes
            startTimeComboBox.setValue(LocalTime.from(receivedAppointment.getStart()));
            endTimeCombo.setValue(receivedAppointment.getEnd().toLocalTime());

            //Populate ID ComboBoxes
            customerIDCombo.setValue(receivedAppointment.getCustomer_ID());
            contactIdComboBox.setValue(receivedAppointment.getContact_ID());
            userIdCombo.setValue(receivedAppointment.getUser_ID());


    }

}
