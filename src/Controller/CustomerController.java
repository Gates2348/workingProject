package Controller;

import Model.Customer;
import Utils.AppointmentsQuery;
import Utils.CustomersQuery;
import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    @FXML
    private TableView<Customer> customersTableView;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> addressCol;
    @FXML
    private TableColumn<?, ?> zipCodeCol;
    @FXML
    private TableColumn<?, ?> phoneCol;
    @FXML
    private TableColumn<?, ?> countryCol;
    @FXML
    private TableColumn<?, ?> division_IDCol;

    //Navigation
    /**
     * takes the UI to a different screen, the addCustomerScreen
     * @param event triggers the transition of changing screen within the UI
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    void toAddCustomerScreen(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/addCustomerScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        Stage stage1 = new Stage();
        stage1.setTitle("Add Customer");
        stage1.setScene(scene);
        stage1.show();
    }

    /**
     * this method takes the UI back to the main screen
     * @param event triggers the transition to being within the UI
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    void toMain(MouseEvent event) throws IOException {
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
     * this method take a selected customer and transitions the UI to the ModifyCustomerScreen for adjustments
     * @param event triggers the transition to the modifyCustomer Screen
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    public void toModifyCustomerScreen(javafx.event.ActionEvent event) throws IOException {

        if (customersTableView.getSelectionModel().getSelectedItem() != null) {
            try {
                ModifyCustomerController.receiveCustomer(customersTableView.getSelectionModel().getSelectedItem());

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/modifyCustomerScreen.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                Stage stage1 = new Stage();
                stage1.setTitle("Modify Customer");
                stage1.setScene(scene);
                stage1.show();
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Error loading customer");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("No customer has been selected");
            alert.showAndWait();
        }
    }

    //Methods

    /**
     * this method deletes the selected customer from the DB
     * @param event triggers the deletion of a record from the DB
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     * @throws IOException exception thrown while accessing information
     */
    @FXML
    void deleteCustomer(MouseEvent event) throws SQLException, IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("Are you sure you want to delete this customer?");
        Optional<ButtonType> button = alert.showAndWait();

        if (button.get() == ButtonType.OK) {
            CustomersQuery.deleteCustomer(customersTableView.getSelectionModel().getSelectedItem().getCustomer_ID());
            customersTableView.setItems(CustomersQuery.getCustomers());
        }

    }

    //Initialize

    /**
     *  This method initializes the controller
     * @param url points to a location of files or web files
     * @param resourceBundle a library used to return messages
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Entry Initialize");
        try {
            customersTableView.setItems(CustomersQuery.getCustomers());
            System.out.println("Retrieve Customers");
            idCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
            zipCodeCol.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            countryCol.setCellValueFactory(new PropertyValueFactory<>("CountryName"));
            division_IDCol.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }






    }

}
