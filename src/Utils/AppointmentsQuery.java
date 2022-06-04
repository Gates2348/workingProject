package Utils;

import Model.Customer;
import Model.TypeCountReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;

import Model.Appointments;
import javafx.scene.control.Alert;

/**
 * this class queries the DB regarding appointments
 */
public class AppointmentsQuery {
    /**
     * this method retrieves all appointments from the DB
     * @return returns array of all appointments
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList<Appointments> getAppointments() throws SQLException {
        ObservableList<Appointments> appointmentsArray = FXCollections.observableArrayList();

        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM appointments";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {

            int getID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description =  rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customer_ID = rs.getInt("Customer_ID");
            int user_ID = rs.getInt("User_ID");
            int contact_ID = rs.getInt("Contact_ID");

            Appointments appointments1 = new Appointments(getID,title,description,location,type,start,end,customer_ID,user_ID,contact_ID);
            appointmentsArray.addAll(appointments1);
        }

        return appointmentsArray;
    }

    /**
     * this method gets all customer ID specific appointments from the DB
     * @param customerID tell the DB what user to retrieve appointments for
     * @return returns an arraylist of appointments for given customer ID
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList<Appointments> getCustomerAppointments(int customerID) throws SQLException {
        ObservableList<Appointments> appointmentsArray = FXCollections.observableArrayList();

        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM appointments WHERE customer_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, customerID);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {

            int getID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description =  rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customer_ID = rs.getInt("Customer_ID");
            int user_ID = rs.getInt("User_ID");
            int contact_ID = rs.getInt("Contact_ID");

            Appointments appointments1 = new Appointments(getID,title,description,location,type,start,end,customer_ID,user_ID,contact_ID);
            appointmentsArray.addAll(appointments1);
        }

        return appointmentsArray;
    }

    /**
     * this method adds an appointment to the DB
     * @param title retrives title from addappointent screen
     * @param description retrieves description from add appointment screen
     * @param location retrieves location from add appointment screen
     * @param type retrieves type from add appointment screen
     * @param start retrieves start from add appointment screen
     * @param end retrieves end from add appointment screen
     * @param customerId retrieves customer id from add appointment screen
     * @param contactId retrieves contact id from add appointment screen
     * @param userId retrieves user id from add appointment screen
     * @return returns a boolen of true if appointment was added or false if appointment wasn't added
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static boolean addAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end,int customerId, int contactId, int userId) throws SQLException {

        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String updateStatement = "INSERT INTO appointments (Title,Description,Location,Type,Start, End,Customer_ID,Contact_ID, User_ID) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
        preparedStatement.setInt(7, customerId);
        preparedStatement.setInt(8,contactId);
        preparedStatement.setInt(9, userId);

        try {
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        if (preparedStatement.getUpdateCount() > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Appointment has been added");
            alert.showAndWait();
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointment has not been added");
            alert.showAndWait();
        }


        return false;
    }

    /**
     * this method adds an appointment to the DB
     * @param title retrives title from addappointent screen
     * @param description retrieves description from add appointment screen
     * @param location retrieves location from add appointment screen
     * @param type retrieves type from add appointment screen
     * @param start retrieves start from add appointment screen
     * @param end retrieves end from add appointment screen
     * @param customerId retrieves customer id from add appointment screen
     * @param contactId retrieves contact id from add appointment screen
     * @param userId retrieves user id from add appointment screen
     * @param appointmentID retrievs appointment id from add appointment screen
     * @return returns true if appointment was updated or false if failed
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static boolean updateAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int contactId, int userId, int appointmentID) throws SQLException {

        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String updateStatement = "UPDATE appointments SET Title=?,Description=?,Location=?,Type=?,Start=?,End=?,Customer_ID=?,Contact_ID=?,User_ID=? WHERE Appointment_ID = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
        preparedStatement.setInt(7, customerId);
        preparedStatement.setInt(8,contactId);
        preparedStatement.setInt(9, userId);
        preparedStatement.setInt(10, appointmentID);

        try {
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        if (preparedStatement.getUpdateCount() > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Appointment has been updated");
            alert.showAndWait();
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Appointment has not been updated");
            alert.showAndWait();
        }


        return false;
    }

    /**
     * this method removes an appointment from the DB
     * @param appointmentID specifies which appointment was selected in the UI
     * @param type specifies which type was selected in the UI
     * @return returns true if appointment deleted or false if failed
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static boolean deleteAppointment(int appointmentID, String type) throws SQLException {
        Connection connection = DBConnection.openConnection();

        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
        preparedStatement.setString(1, deleteStatement);
        preparedStatement.setInt(1, appointmentID);

        try {
            preparedStatement.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
        if (preparedStatement.getUpdateCount() > 0) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Success");
            alert1.setHeaderText("Appointment ID#" + appointmentID + " of type " + type + " was successfully deleted");
            alert1.showAndWait();
            return true;
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText("Appointment was not successfully deleted");
            alert1.showAndWait();
        }



        return false;
    }

    /**
     * this method gets appointments from the DB by week
     * @return returns appointments by week in an arraylist
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList<Appointments> getAppointmentsByWeek() throws SQLException {
        ObservableList<Appointments> weeklyAppointments = FXCollections.observableArrayList();

        //Open DB connection
        Connection connection = DBConnection.openConnection();
        //WHERE start between ? & ?
        String statement = "SELECT * FROM client_schedule.appointments WHERE WEEK(`Start`) = WEEK(CURRENT_DATE())";
        //add clause where and by now + 6 for week
        //for month you can specify month
        PreparedStatement preparedStatement = connection.prepareStatement(statement);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {

            int getID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description =  rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customer_ID = rs.getInt("Customer_ID");
            int user_ID = rs.getInt("User_ID");
            int contact_ID = rs.getInt("Contact_ID");

            Appointments appointments1 = new Appointments(getID,title,description,location,type,start,end,customer_ID,user_ID,contact_ID);
            weeklyAppointments.addAll(appointments1);
        }

        return weeklyAppointments;
    }

    /**
     * this method gets appointments by month from the DB
     * @return returns an array containing appointments by months
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList<Appointments> getAppointmentsByMonth() throws SQLException{
        ObservableList<Appointments> monthlyAppointments = FXCollections.observableArrayList();

        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM client_schedule.appointments WHERE MONTH(`Start`) = MONTH(CURRENT_DATE())";
        //add clause where and by now + 6 for week
        //for month you can specify month
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {

            int getID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description =  rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customer_ID = rs.getInt("Customer_ID");
            int user_ID = rs.getInt("User_ID");
            int contact_ID = rs.getInt("Contact_ID");

            Appointments appointments1 = new Appointments(getID,title,description,location,type,start,end,customer_ID,user_ID,contact_ID);
            monthlyAppointments.addAll(appointments1);
        }

        return monthlyAppointments;
    }

    /**
     * this method checks before adding or modifying an appointment to ensure no other appointmnets are scheduled at the same time
     * @param NAS new appointment start date and time
     * @param NAE new appointment end date and time
     * @param customerID customer id of customer whose appointment is being added or modified
     * @param appointmentID appointment id of appointment that is being added or modified
     * @return returns true if appointment conflicts with another existing appointment or false if it does not
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static boolean checkConflictingAppointments(LocalDateTime NAS, LocalDateTime NAE, int customerID, int appointmentID) throws SQLException {
        //appointment id -1

        ObservableList<Appointments> allAppointments = getCustomerAppointments(customerID);

        for(Appointments appointments : allAppointments) {

            if(appointments.getAppointment_ID() == appointmentID){
                continue; //skip this iteration
            }
            if(NAS.isEqual(appointments.getStart()) || NAE.isEqual(appointments.getEnd())){
                System.out.println("1");
                //GOOD
                return false;
            }else if(NAS.isBefore(appointments.getStart()) && NAE.isAfter(appointments.getEnd())){
                System.out.println("2");
                //GOOD
                return false;
            }else if(NAS.isAfter(appointments.getStart()) && NAS.isBefore(appointments.getEnd())){
                System.out.println("3");
                //GOOD
                return false;
            }else if(NAS.isBefore(appointments.getStart()) && NAE.isAfter(appointments.getStart())){
                System.out.println("4");
                //GOOD
                return false;
            }else if(NAS.isAfter(appointments.getStart()) && NAE.isBefore(appointments.getStart())){
                System.out.println("5");
                //UNSURE
                return false;
            }

        }

        return true;
    }

    /**
     * this method retrieves appointments by contact id
     * @param contactID tells DB which contact ID to get appointments for
     * @return returns array of appointments for specific contact id
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList<Appointments> getContactAppointments(int contactID) throws SQLException {
        ObservableList<Appointments> appointmentsArray = FXCollections.observableArrayList();

        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String statement = "SELECT * FROM appointments WHERE contact_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, contactID);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {

            int getID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description =  rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customer_ID = rs.getInt("Customer_ID");
            int user_ID = rs.getInt("User_ID");
            int contact_ID = rs.getInt("Contact_ID");

            Appointments appointments1 = new Appointments(getID,title,description,location,type,start,end,customer_ID,user_ID,contact_ID);
            appointmentsArray.addAll(appointments1);
        }

        return appointmentsArray;
    }

    /**
     * this method gets the all different appointment types and the count of how many times that type occurs in a specific month
     * @param month tells the DB which month to search
     * @return return an array of typecountreport that contains types of appointments and count of how many times they occur
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static ObservableList<TypeCountReport> getTypeNCountForReport(String month) throws SQLException {
        ObservableList<TypeCountReport> typeCountReportArray = FXCollections.observableArrayList();

        //Open DB connection
        Connection connection = DBConnection.openConnection();

        String statement = "select Type, count(*) from client_schedule.appointments WHERE monthname(Start) = ? group by Type order by Type desc;";

        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, month);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {

            int count = rs.getInt("count(*)");
            String type = rs.getString("Type");

            TypeCountReport typeCounts = new TypeCountReport(type,count);
            typeCountReportArray.addAll(typeCounts);
        }

        return typeCountReportArray;

    }

}
