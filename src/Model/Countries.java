package Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * this is the countries class that sets and gets country variables
 */
public class Countries {
    private int Country_ID;
    private String Country;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;

    /**
     * this is the countries constructor
     * @param country_ID sets country ID to int
     * @param country sets country to string
     */
    public Countries(int country_ID, String country) {
        this.Country_ID = country_ID;
        this.Country = country;
    }

    /**
     * gets country
     * @return returns country
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * sets country ID
     * @param country_ID sets country ID to int
     */
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    /**
     * gets country
     * @return returns country
     */
    public String getCountry() {
        return Country;
    }

    /**
     * sets country
     * @param country sets country to string
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     * gets create date
     * @return return create date
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * sets create date
     * @param create_Date sets create date as LocalDateTime
     */
    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    /**
     * gets created By
     * @return returns created by
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * sets created By
     * @param created_By sets created by to String
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
     * @param last_Update sets last update to TimeStamp
     */
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    /**
     * gets last Updated BY
     * @return returns last updated by
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * sets last updated by
     * @param last_Updated_By sets last updated by to String
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     * converts country
     * @return converts country to string
     */
    @Override
    public String toString(){
        return Country;
    }
}
