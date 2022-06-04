package Controller;

import Main.Main;
import Model.Appointments;
import Model.Users;
import Utils.AppointmentsQuery;
import Utils.UsersQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import java.time.ZoneId;

public class LoginController implements Initializable {

    public TextField userNameField;
    public TextField passwordTextField;
    public Button loginButton;
    public Button exitButton;
    public Label timeZOne;

    public Label passwordLabel;
    public Label userNameLabel;

    public ResourceBundle rb;

    //Navigation
    /**
     * transitions the UI screen to the main screen
     * @param event triggers the UI to transition screens
     * @throws IOException exception thrown while accessing information
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    @FXML
    void toMain(MouseEvent event) throws IOException, SQLException {

        if(UsersQuery.validateUserNamePassword(userNameField.getText(), passwordTextField.getText())){
            appointmentReminder();
            Locale.setDefault(new Locale("en", "US"));
            writeToLog(userNameField.getText(), true);
            Stage stage1 = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage1.close();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/mainScreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 550, 400);
            Stage stage = new Stage();
            stage.setTitle("Main Screen");
            stage.setScene(scene);
            stage.show();
        }else if(userNameField.getText().isEmpty() || passwordTextField.getText().isEmpty()){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setHeaderText(rb.getString("Warning"));
            alert1.setContentText(rb.getString("UserNamePassword"));
            alert1.show();
        }else{
            writeToLog(userNameField.getText(), false);
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setHeaderText(rb.getString("Warning"));
            alert1.setContentText(rb.getString("userNamePasswordIncorrect"));
            alert1.show();
        }

    }

    /**
     * This method exits the UI application
     * @param event triggers the above action
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    void exitApp(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //Methods
    /**
     * this method writes to a log every login attempt whether successful or unsuccessful
     * @param user retrieves the user who attempted login
     * @param result writes either successful or unsuccessful in the Login_Activity.txt
     */
    public void writeToLog(String user, boolean result){
        Path path = Paths.get("login_activity.txt");
        ZonedDateTime zone = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
        ZonedDateTime utcTime = zone.withZoneSameInstant(ZoneId.of("UTC"));
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSSSSS z");
        String outcome = "login Successful";
        if(!result)
            outcome = "login Unsuccessful";
        String writeToLog = String.format("User: %s - %s - %s\n", user, dateTimeFormat.format(utcTime), outcome);
        try {
            Files.writeString(path, writeToLog, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * this method generates an Information box if the user has an upcoming appointment within 15 minutes of login or informs the user of no upcoming appointments
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public void appointmentReminder() throws SQLException {
        ObservableList<Users>users = UsersQuery.getUsers();
        int id = 0;
        for(Users users1:users){
            if(users1.getUser_Name().equalsIgnoreCase(userNameField.getText())){
                id = users1.getUser_ID();
            }
        }

            ObservableList<Appointments> allCustomerAppointments = AppointmentsQuery.getAppointments();
            ObservableList<Appointments> upcomingUserAppointment = FXCollections.observableArrayList();

            for(Appointments userAppts : allCustomerAppointments){
                if(userAppts.getUser_ID() == id){
                    upcomingUserAppointment.add(userAppts);
                }
            }

            boolean upcomingAppts = false;

            for(Appointments appointments : upcomingUserAppointment) {
                LocalTime now = LocalTime.now();
                LocalTime nowPlus15 = now.plusMinutes(15);

                if(appointments.getStart().toLocalTime().isAfter(now) && appointments.getStart().toLocalTime().isBefore(nowPlus15)){
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setHeaderText(rb.getString("Attention"));
                    alert1.setContentText(rb.getString("upcoming") + rb.getString("id") + appointments.getAppointment_ID() + " " + rb.getString("at") + " " + appointments.getStart().toLocalDate() + " " + appointments.getStart().toLocalTime());
                    alert1.showAndWait();
                    upcomingAppts = true;
                    break;
                }
            }if(!upcomingAppts){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText(rb.getString("Attention"));
            alert1.setContentText(rb.getString("no"));
            alert1.showAndWait();
        }
    }

    /**
     *  This method initializes the controller
     * @param url points to a location of files or web files
     * @param resourceBundle a library used to return messages
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());


        passwordTextField.setPromptText(rb.getString("password"));
        loginButton.setText(rb.getString("login"));
        exitButton.setText(rb.getString("exit"));

        passwordLabel.setText(rb.getString("password"));
        userNameLabel.setText(rb.getString("username"));

        timeZOne.setText(ZoneId.systemDefault().toString());






    }

}

