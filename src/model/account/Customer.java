package model.account;

import model.Model;
import model.transaction.Booking;

import java.util.ArrayList;

/*This is the customer class where it would consist the information of the customers such as
    - customer name
    - mobile number
    - email address
 */
public class Customer implements Model {
    private String userName;
    private String password;
    private String mobileNumber;
    private String emailAddress;
    //    private int mobileNumber;
    private ArrayList<Booking> bookingHistory = new ArrayList<Booking>();

    public Customer(String userName, String password, String mobileNumber, String emailAddress) {
        /**This constructor for Customer
         * @param username    The username of the customer
         * @param password         The password of the customer
         * @param mobileNumber  The mobileNumber of the customer
         * @param emailAddress  The emailAddress of the customer
         */
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void addBookingHistory(Booking booking) {
        /**This method will add the bookings into the customer's booking history for the customer to view later in the
         * future
         * @param booking           The booking to be added into the customer's booking history
         */
        bookingHistory.add(booking);
    }

    public ArrayList<Booking> getBookingHistory() {
        return bookingHistory;
    }

}
