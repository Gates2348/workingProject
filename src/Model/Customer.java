package Model;

import Utils.CountriesQuery;
import Utils.DivisionQuery;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * this is the customer class that sets and gets customer variables
 */
public class Customer {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code; // string
    private String Phone;
    private LocalDateTime Create_Date;  //timestamp or datetime use localdatetime
    private String Created_By;
    private LocalDateTime Last_Update; // LocalDateTime
    private String Last_Updated_By;
    private int Division_ID;
    private int Country_ID;

    /**
     * this is the customer constructor
     * @param Customer_ID sets customer ID to int
     * @param Customer_Name sets customer name to String
     * @param Address sets address to String
     * @param Postal_Code sets postal code to String
     * @param Phone sets phone to String
     * @param Division_ID sets division ID to int
     */
    public Customer(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, int Division_ID){
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Division_ID = Division_ID;
    }

    /**
     * gets customer ID
     * @return returns customer ID
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     * sets customer ID
     * @param customer_ID sets customer ID to int
     */
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    /**
     * gets customer name
     * @return returns customer name
     */
    public String getCustomer_Name() {
        return Customer_Name;
    }

    /**
     * sets customer name
     * @param customer_Name returns customer name
     */
    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    /**
     * gets address
     * @return returns address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * sets address
     * @param address sets address to string
     */
    public void setAddress(String address) {
        Address = address;
    }

    /**
     * gets postal code
     * @return returns postal code
     */
    public String getPostal_Code() {
        return Postal_Code;
    }

    /**
     * sets postal code
     * @param postal_Code sets postal code as string
     */
    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    /**
     * gets phone
     * @return returns phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * sets phone
     * @param phone sets phone as string
     */
    public void setPhone(String phone) {
        Phone = phone;
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
     * @param create_Date sets create date to LocalDate Time
     */
    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    /**
     * gets created BY
     * @return returns created by
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * sets created by
     * @param created_By sets create by to string
     */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /**
     * gets last updated
     * @return returns last update
     */
    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    /**
     * sets last update
     * @param last_Update sets last update to LocalDateTime
     */
    public void setLast_Update(LocalDateTime last_Update) {
        Last_Update = last_Update;
    }

    /**
     * gets ladt updated by
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
     * gets division id
     * @return returns division id
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     * sets division id
     * @param division_ID sets division id to int
     */
    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    /**
     * this method gets country name by division ID
     * @return returns country name
     * @throws SQLException provides information on an error accessing the DB
     */
    public String getCountryName() throws SQLException {
        return CountriesQuery.getCountryByDivision(Division_ID).getCountry();
    }

    /**
     * this methods gets division name by division ID
     * @return returns division name
     * @throws SQLException provides information on an error accessing the DB
     */
    public String getDivisionName() throws SQLException {
        return DivisionQuery.getDivision(Division_ID).getDivision();
    }

    /**
     * this method converts customer name to string
     * @return returns customer name as string
     */
    @Override
    public String toString(){
        return Customer_Name;
    }


}
