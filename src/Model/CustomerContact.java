package Model;

import java.time.LocalDateTime;

/**
 * This is the customer contact class that stores information about customer contact
 */
public class CustomerContact {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;

    /**
     * this is the constructor for the customer contact class
     * @param customer_ID sets customer id to int
     * @param customer_Name sets customer name to string
     * @param address sets address to string
     * @param postal_Code sets postal code to string
     * @param phone sets phone to string
     */
    public CustomerContact(int customer_ID, String customer_Name, String address, String postal_Code, String phone) {
        Customer_ID = customer_ID;
        Customer_Name = customer_Name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
    }

    /**
     * this gets the customer id
     * @return returns customer id
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     * sets customer id
     * @param customer_ID sets customer id to int
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
     * @param customer_Name sets customer name to string
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
     * @param postal_Code sets postal code to string
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
     * @param phone sets phone to string
     */
    public void setPhone(String phone) {
        Phone = phone;
    }
}
