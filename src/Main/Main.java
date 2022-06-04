package Main;

import Utils.CountriesQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Utils.DBConnection;

import java.sql.*;

import java.io.IOException;
import java.util.Locale;

public class Main extends Application {

    /**
     * this method sates the main login screen in the UI
     * @param stage generates the primary window of the UI
     * @throws IOException exception thrown while accessing information
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Views/loginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LoginScreen");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This is the main method of the entire application this is the starting point
     * @param args contains arguments passed to the program
     * @throws SQLException provides information on an error accessing the DB
     */
    public static void main(String[] args) throws SQLException {
        //Locale.setDefault(new Locale("fr", "FR"));

        launch(args);
    }


}


