package Model;

import Utils.CountriesQuery;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This is the appointments class that stores information about appointments
 */
public class Appointments {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;

    /**
     * This is the Appointment constructor that ensures appointments have all the relevant information
     * @param appointment_ID converts appointment ID from DB to int
     * @param title converts title from DB to String
     * @param description converts description from DB to String
     * @param location converts location from DB to String
     * @param type converts type from DB to String
     * @param start converts StateDateTime from DB to LocalDateTime
     * @param end converts endDateTime from DB to LocalDateTime
     * @param customer_ID converts customer ID from DB to int
     * @param user_ID converts user ID from DB to int
     * @param contact_ID converts contact ID from DB to int
     */
    public Appointments(int appointment_ID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customer_ID, int user_ID, int contact_ID) {
        this.Appointment_ID = appointment_ID;
        this.Title = title;
        this.Description = description;
        this.Location = location;
        this.Type = type;
        this.Start = start;
        this.End = end;
        this.Customer_ID = customer_ID;
        this.User_ID = user_ID;
        this.Contact_ID = contact_ID;
    }

    /**
     * this method gets the appointment ID
     * @return returns appointment ID
     */
    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /**
     * this method sets the appointment ID
     * @param appointment_ID int value of appointment ID
     */
    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    /**
     * gets title
     * @return returns title variable
     */
    public String getTitle() {
        return Title;
    }

    /**
     * sets title variable
     * @param title sets title variable to String
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * gets description
     * @return returns desciption variable
     */
    public String getDescription() {
        return Description;
    }

    /**
     * sets description
     * @param description sets description as string
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * gets location
     * @return returns location variable
     */
    public String getLocation() {
        return Location;
    }

    /**
     * sets locations
     * @param location sets location to string
     */
    public void setLocation(String location) {
        Location = location;
    }

    /**
     * gets tyoe
     * @return returns type variable
     */
    public String getType() {
        return Type;
    }

    /**
     * sets tyoe
     * @param type sets type to string
     */
    public void setType(String type) {
        Type = type;
    }

    /**
     * gets start variable
     * @return returns start variable
     */
    public LocalDateTime getStart() {
        return Start;
    }

    /**
     * sets start variable
     * @param start sets start variable as LocalDateTime
     */
    public void setStart(LocalDateTime start) {
        Start = start;
    }

    /**
     * gets end variable
     * @return returns end variable
     */
    public LocalDateTime getEnd() {
        return End;
    }

    /**
     * sets end variable
     * @param end sets end variable to LocalDateTime
     */
    public void setEnd(LocalDateTime end) {
        End = end;
    }

    /**
     * gets createdDate
     * @return returns CreateDate
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * sets create date
     * @param create_Date sets create date to LocalDateTime
     */
    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    /**
     * gets createdBy
     * @return returns createdBy
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * sets created By
     * @param created_By sets Created By to String
     */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /**
     * gets Last Updated
     * @return returns Last Update
     */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /**
     * sets last update
     * @param last_Update sets last Update as Timestamp
     */
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    /**
     * gets lastUpdatedBy
     * @return returns Last UpdatedBy
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * sets lastUpdateBY
     * @param last_Updated_By sets LastUpdatedBY to String
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     * gets customerID
     * @return returns customerID
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     * sets customer id
     * @param customer_ID sets customerID to int
     */
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    /**
     * gets userID
     * @return returns user ID
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     * sets user ID
     * @param user_ID sets UserID to int
     */
    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    /**
     * gets contact ID
     * @return returns contact ID
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     * sets contact ID
     * @param contact_ID sets contact ID to int
     */
    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }


}
