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
    private String customerName;
    private String email;
    private int mobileNumber;
    private ArrayList<Booking> bookingHistory = new ArrayList<Booking>();

    public Customer(String customerName, String email, int mobileNumber) {
        /**This constructor for Customer
         * @param customerName          The name of the customer
         * @param email                 The email of the customer
         * @param mobileNumber          The mobile number of the customer
         */
        this.customerName = customerName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getCustomerName() {
        /**This method will get and return the customer's name
         * @return              The name of the customer
         */
        return customerName;
    }

    public String getEmail() {
        /**This method will get and return the customer's email
         * @return              The email of the customer
         */
        return email;
    }

    public int getMobileNumber() {
        /**This method will get and return the customer's mobile number
         * @return              The mobile number of the customer
         */
        return mobileNumber;
    }

    public void addBookingHistory(Booking booking) {
        /**This method will add the bookings into the customer's booking history for the customer to view later in the
         * future
         * @param booking           The booking to be added into the customer's booking history
         */
        bookingHistory.add(booking);
    }
}
