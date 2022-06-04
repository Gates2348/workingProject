package Controller;

import Main.Main;
import Model.Appointments;
import Model.Contacts;
import Utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ReportsController implements Initializable {

    //Combo Boxes
    @FXML
    public ComboBox contactComboButton;
    @FXML
    public ComboBox monthComboBox;

    //Table Views
    @FXML
    public TableView<Appointments> contactTableView;
    public TableView customerTableView;
    public TableView customerContactTableView;

    //Table Colums
    public TableColumn customerColType;
    public TableColumn customerColCount;
    public TableColumn customerContactIDCol;
    public TableColumn customerContactNameCol;
    public TableColumn customerContactAddressCol;
    public TableColumn customerContactZipcodeCol;
    public TableColumn customerContactPhoneCol;
    @FXML
    private TableColumn<?, ?> apptID;
    @FXML
    private TableColumn<?, ?> title;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> starteDateTime;
    @FXML
    private TableColumn<?, ?> endDateTime;
    @FXML
    private TableColumn<?, ?> custID;


    //Navigation
    /**
     * this method closed the application
     * @param event triggers the action to take place
     */
    @FXML
    void toExit(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * this method transitions the UI screen to the main screen
     * @param event triggers the transition between screens
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    void toMain(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/mainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        Stage stage = new Stage();
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    //Methods
    /**
     * populates the contactCombo with contact IDs
     * @param event triggers the contactCombo to populate
     */
    @FXML
    void contactCombo(MouseEvent event) {
        ObservableList<Contacts> contactIDSearch;
        ObservableList <Integer> contactIDs = FXCollections.observableArrayList();

        try{
            contactIDSearch = ContactsQuery.getContacts();
            for(Contacts contacts : contactIDSearch){
                contactIDs.add(contacts.getContact_ID());
            }
            contactComboButton.setItems(contactIDs);
        }catch(Exception e){
            System.out.println(e);
        }

    }

    /**
     * this method populates the contactAppointment Tableview with relevant data bases on the contact ID selected
     * @param event triggers the data to populate the above
     */
    @FXML
    public void populateContactAppointmentsTableView(ActionEvent event) {
        if(!(contactComboButton.getValue() == null)) {
            try {
                int contactID = (int) contactComboButton.getValue();
                contactTableView.setItems(AppointmentsQuery.getContactAppointments(contactID));
                contactTableView.getSelectionModel().clearSelection();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    /**
     * this methods sets the tableview for customers based on the month selected
     * @param event triggers the data to populate in the UI
     * @throws SQLException provides information on an error accessing the DB
     */
    @FXML
    private void populateMonthlyCombo(ActionEvent event) throws SQLException{
        try{
            customerTableView.setItems(AppointmentsQuery.getTypeNCountForReport(monthComboBox.getValue().toString()));
            customerColType.setCellValueFactory(new PropertyValueFactory<>("Type"));
            customerColCount.setCellValueFactory(new PropertyValueFactory<>("Count"));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * This method populates the monthlyCombo with the months that have appointments scheduled
     * @throws SQLException provides information on an error accessing the DB
     */
    private void populateMonthlyCombo() throws SQLException {
        ObservableList <String> months = FXCollections.observableArrayList();

        for(Appointments appointments: AppointmentsQuery.getAppointments()){
            if(!months.contains(String.valueOf(appointments.getStart().getMonth()))){
                months.add(String.valueOf(appointments.getStart().getMonth()));
            }
        }
        monthComboBox.setItems(months);

    }

    /**
     * this method populates the CustomerContact Tableview with relevant information about the customers
     * @throws SQLException provides information on an error accessing the DB
     */
    private void populateCustomerContactTableView() throws SQLException {
        customerContactTableView.setItems(CustomersQuery.getcustomerContactReport());

        try{
            customerContactIDCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            customerContactNameCol.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            customerContactAddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
            customerContactZipcodeCol.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
            customerContactPhoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        }catch (Exception e){
            System.out.println(e);
        }
    }



    //Initialize
    /**
     *  This method initialize the controller
     * @param url points to a location of files or web files
     * @param resourceBundle a library used to return messages
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            populateMonthlyCombo();
            populateCustomerContactTableView();

            apptID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
            title.setCellValueFactory(new PropertyValueFactory<>("Title"));
            type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            starteDateTime.setCellValueFactory(new PropertyValueFactory<>("Start"));
            endDateTime.setCellValueFactory(new PropertyValueFactory<>("End"));
            custID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));


        }catch (Exception e){
            System.out.println(e);
        }

}


}
