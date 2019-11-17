package model.account;

import model.Model;
import model.transaction.Booking;

import java.util.ArrayList;

/**This is the customer class where it would consist the information of the customers such as
    - customer name
    - mobile number
    - email address
 **/
public class Customer implements Model {
    /**
     * username of Customer
     */
    private String userName;
    /**
     * password of Customer
     */
    private String password;
    /**
     * mobile number of Customer
     */
    private String mobileNumber;
    /**
     * email address of Customer
     */
    private String emailAddress;
    //    private int mobileNumber;
    /**
     * an arraylist of bookings of the customer
     */
    private ArrayList<Booking> bookingHistory = new ArrayList<Booking>();

    /**
     * This constructor for Customer
     *
     * @param userName     The username of the customer
     * @param password     The password of the customer
     * @param mobileNumber The mobileNumber of the customer
     * @param emailAddress The emailAddress of the customer
     */
    public Customer(String userName, String password, String mobileNumber, String emailAddress) {

        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
    }

    /**
     * This method is used to obtain the username
     *
     * @return the username of the customer
     */
    public String getUserName() {

        return userName;
    }

    /**This method is used to set the username
     * @param  userName the username of the customer
     */
    public void setUserName(String userName) {

        this.userName = userName;
    }

    /**
     * This method is used to obtain the password
     *
     * @return the password of the customer
     */
    public String getPassword() {

        return password;
    }

    /**This method is used to set the password
     * @param password the password of the customer
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * This method is used to obtain the mobile number
     *
     * @return the mobile number of the customer
     */
    public String getMobileNumber() {

        return mobileNumber;
    }

    /**This method is used to set the mobile number
     * @param mobileNumber the mobile number of the customer
     */
    public void setMobileNumber(String mobileNumber) {

        this.mobileNumber = mobileNumber;
    }

    /**
     * This method is used to obtain the email
     *
     * @return the email of the customer
     */
    public String getEmailAddress() {

        return emailAddress;
    }

    /**This method is used to set the email
     * @param emailAddress the email of the customer
     */
    public void setEmailAddress(String emailAddress) {

        this.emailAddress = emailAddress;
    }

    /**
     * This method will add the bookings into the customer's booking history for the customer to view later in the
     * future
     *
     * @param booking The booking to be added into the customer's booking history
     */
    public void addBookingHistory(Booking booking) {

        bookingHistory.add(booking);
    }

    /**
     * This method will get the bookings into the customer's booking history
     *
     * @return The customer's booking history
     */
    public ArrayList<Booking> getBookingHistory() {

        return bookingHistory;
    }

}
