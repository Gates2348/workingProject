package Model;

/**
 * This is the contacts class that stores information about contacts
 */
public class Contacts {
    private int Contact_ID;
    private String Contact_Name;
    private String Email;

    /**
     * This is the contact constructor
     * @param contact_ID converts contactID from DB to int
     * @param contact_Name converts contact name from DB to string
     * @param email converts email from db to string
     */
    public Contacts(int contact_ID, String contact_Name, String email) {
        this.Contact_ID = contact_ID;
        this.Contact_Name = contact_Name;
        this.Email = email;
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

    /**
     * gets contact Name
     * @return returns contact name
     */
    public String getContact_Name() {
        return Contact_Name;
    }

    /**
     * sets contact name
     * @param contact_Name sets contact name to String
     */
    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    /**
     * gets email
     * @return returns email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * sets email
     * @param email sets email to string
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * converts contactname from Contacts to a string
     * @return returns contact name as a string
     */
    @Override
    public String toString(){
        return Contact_Name;
    }
}
