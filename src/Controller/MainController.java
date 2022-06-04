package Controller;

import Main.Main;
import Model.Customer;
import Utils.CustomersQuery;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    /**
     * this method takes the UI to the appointmentScreen
     * @param event triggers the UI to transition between screens
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    void toAppointmentScreen(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/appointmentsScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 500);
        Stage stage1 = new Stage();
        stage1.setTitle("Appointments");
        stage1.setScene(scene);
        stage1.show();
    }

    /**
     * takes the UI to the Customer Screen
     * @param event triggers the UI to transition to a different screen
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    void toCustomerScreen(MouseEvent event) throws IOException {
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
     * takes the UI to the Reports screen
     * @param event triggers the UI to transition between screens
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    void toReportsScreen(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/reportsScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        Stage stage1 = new Stage();
        stage1.setTitle("Reports");
        stage1.setScene(scene);
        stage1.show();
    }

    /**
     * exits the UI application
     * @param event triggers the above action
     */
    @FXML
    void toExit(MouseEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     *  This method initializes the controller
     * @param url points to a location of files or web files
     * @param resourceBundle a library used to return messages
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}