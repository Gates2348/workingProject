package Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * this is the division class that sets and gets division variables
 */
public class Division {
    private int Division_ID;
    private String Division;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Country_ID;

    /**
     * this is the division constructor
     * @param division_ID sets division id to int
     * @param division sets division to string
     * @param countryID sets country id to int
     */
    public Division(int division_ID, String division, int countryID) {
        this.Division_ID = division_ID;
        this.Division = division;
        this.Country_ID = countryID;
    }

    /**
     * gets division ID
     * @return returns division ID
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     * set division ID
     * @param division_ID sets division id to int
     */
    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    /**
     * gets division
     * @return returns division
     */
    public String getDivision() {
        return Division;
    }

    /**
     * sets division
     * @param division sets division to string
     */
    public void setDivision(String division) {
        Division = division;
    }

    /**
     * gets create date
     * @return returns create date
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
     * gets created by
     * @return returns created by
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * sets created by
     * @param created_By sets created by to string
     */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /**
     * gets last update
     * @return returns last update
     */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /**
     * sets last update
     * @param last_Update sets last updated to timestamp
     */
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    /**
     * gets last updated by
     * @return returns last updated by
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * sets last updated by
     * @param last_Updated_By sets last updated by to string
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     * gets country id
     * @return returns country id
     */
    public int getCountryID() {
        return Country_ID;
    }

    /**
     * sets country id
     * @param countryID sets country id to int
     */
    public void setCountryID(int countryID) {
        this.Country_ID = countryID;
    }

    /**
     * converts division to string
     * @return returns division as string
     */
    @Override
    public String toString(){
        return Division;
    }

}
