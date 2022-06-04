package Model;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * this is the users class that sets and gets user variables
 */
public class Users {
    private int User_ID;
    private String User_Name;
    private Text Password;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;

    /**
     * user class constructor
     * @param user_ID sets user id to int
     * @param user_Name sets user name to string
     */
    public Users(int user_ID, String user_Name) {
        this.User_ID = user_ID;
        this.User_Name = user_Name;
    }

    /**
     * gets user id
     * @return returns user id
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     * sets user id
     * @param user_ID sets user id to int
     */
    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    /**
     * gets user name
     * @return returns user name
     */
    public String getUser_Name() {
        return User_Name;
    }

    /**
     * sets user name
     * @param user_Name sets user name to string
     */
    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    /**
     * gets password
     * @return returns password
     */
    public Text getPassword() {
        return Password;
    }

    /**
     * sets password
     * @param password sets password to text
     */
    public void setPassword(Text password) {
        Password = password;
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
     * @return returns last updated
     */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /**
     * sets last update
     * @param last_Update sets last updated to Timestamp
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
     * converts user name to string
     * @return returns user name as string
     */
    @Override
    public String toString(){
        return User_Name;
    }
}
