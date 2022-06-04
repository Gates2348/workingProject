package Controller;

import Model.Appointments;
import Main.Main;
import Utils.AppointmentsQuery;
import Utils.CustomersQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable{
    @FXML
    public TableView<Appointments> appointmentTableView;
    public ToggleGroup radioButtons;
    @FXML
    public RadioButton radioMonth;
    @FXML
    public RadioButton radioWeek;
    @FXML
    public RadioButton radioAll;


    @FXML
    private TableColumn<?, ?> AppointIDCol;
    @FXML
    private TableColumn<?, ?> titleCol;
    @FXML
    private TableColumn<?, ?> descriptionCol;
    @FXML
    private TableColumn<?, ?> locationCol;
    @FXML
    private TableColumn<?, ?> typeCol;
    @FXML
    private TableColumn<?, ?> startDateTimeCol;
    @FXML
    private TableColumn<?, ?> endDateTimeCol;
    @FXML
    private TableColumn<?, ?> customerIDCol;
    @FXML
    private TableColumn<?, ?> userIDCol;
    @FXML
    private TableColumn<?, ?> contactIdCol;


    //Navigation
    /**
     * transitions the UI to the modifyAppointment Screen
     * @param event triggers the transition within the UI to a different screen
     */
    @FXML
    public void toModifyAppointment(ActionEvent event){
        ModifyAppointmentController.ReceivedAppointment(appointmentTableView.getSelectionModel().getSelectedItem());

        if (appointmentTableView.getSelectionModel().getSelectedItem() != null) {
            try {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/ModifyAppointmentScreen.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                Stage stage1 = new Stage();
                stage1.setTitle("Modify Appointment");
                stage1.setScene(scene);
                stage1.show();
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Error loading customer");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("No appointment has been selected");
            alert.showAndWait();
        }
    }

    /**
     * this method deletes a selected appointment from the DB
     * @param event triggers the transition in the UI to a different screen
     * @throws SQLException provides information on an error while accessing the DB
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    public void deleteAppointment(ActionEvent event) throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("Are you sure you want to delete this appointment?");
        Optional<ButtonType> button = alert.showAndWait();

        if (button.get() == ButtonType.OK) {
            AppointmentsQuery.deleteAppointment(appointmentTableView.getSelectionModel().getSelectedItem().getAppointment_ID(), appointmentTableView.getSelectionModel().getSelectedItem().getType());
            appointmentTableView.setItems(AppointmentsQuery.getAppointments());
        }

    }

    /**
     * This method navigates the UI to the Main screen
     * @param event triggers the transition to to a different screen within the UI
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    public void ToMain(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/mainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        Stage stage1 = new Stage();
        stage1.setTitle("Main Screen");
        stage1.setScene(scene);
        stage1.show();
    }

    /**
     * This method navigates the UI to the addCustomer screen
     * @param event Action event that triggers the transition to a different screen
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    public void toAddAppointment(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/addAppointmentScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        Stage stage1 = new Stage();
        stage1.setTitle("Add Appointment");
        stage1.setScene(scene);
        stage1.show();
    }

    //Method to determine radio button selection
    /**
     * this method determines which radio button is selected and changes the output in the UI
     * @throws SQLException provides information on an error accessing the DB
     */
    @FXML
    void radioSelected() throws SQLException {
        if(radioButtons.getSelectedToggle().equals(radioAll)){
            appointmentTableView.setItems(AppointmentsQuery.getAppointments());
        }else if(radioButtons.getSelectedToggle().equals(radioWeek)){
            appointmentTableView.getSelectionModel().clearSelection();
            appointmentTableView.setItems(AppointmentsQuery.getAppointmentsByWeek());
        }else if(radioButtons.getSelectedToggle().equals(radioMonth)){
            appointmentTableView.getSelectionModel().clearSelection();
            appointmentTableView.setItems(AppointmentsQuery.getAppointmentsByMonth());
        }

    }


    /**
     *  This method initializes the controller
     * @param url points to a location of files or web files
     * @param resourceBundle a library used to return messages
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            radioSelected();

            AppointIDCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
            locationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
            startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
            endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("End"));
            customerIDCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            userIDCol.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
            contactIdCol.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));

        } catch (Exception e) {
            e.printStackTrace();
        }



    }


}
