package service;

import controller.DBController;
import model.account.Admin;
import model.account.Customer;

import java.util.ArrayList;

/**
 * Class to provide account creation and login services
 */
public class AccountService {
    /**
     * static instance of DBController to access database
     */
    private static DBController dbController = DBController.getInstance();

    /**
     * Method to create a Customer account
     *
     * @param username
     * @param password
     * @param phoneNumber
     * @param email
     * @return
     */
    public static boolean createMovieGoerAccount(String username, String password, String phoneNumber, String email) {
        // check if phone number is numeric
        if (phoneNumber.length() != 8 || !phoneNumber.matches("[0-9]+")) {
            System.out.println("Phone number is invalid. Please try again");
            return false;
        }
        dbController.addCustomer(new Customer(username, password, phoneNumber, email));
        return true;
    }


    /**
     * Method to create an Admin account
     * @param username
     * @param password
     */
    public static void createAdminAccount(String username, String password) {
        dbController.addAdmin(username, password);
    }

    /**
     * Method to check if a Customer Account exists
     * @param username
     * @param password
     * @return
     */
    public static boolean checkIfCustomerAccountExists(String username, String password) {
        ArrayList<Customer> customers = dbController.getCustomer();

        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();
        for (Customer customer : customers) {
            usernames.add(customer.getUserName());
            passwords.add(customer.getPassword());
        }
        return usernames.contains(username) && passwords.contains(password);
    }

    /**
     * Method to get instance of Customer based on username
     * @param username
     * @return
     */
    public static Customer getCustomer(String username){
        ArrayList<Customer> customers = dbController.getCustomer();
        for(Customer customer : customers) {
            if(customer.getUserName().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Method to check if an admin account exists
     * @param username
     * @param password
     * @return
     */
    public static boolean checkIfAdminAccountExists(String username, String password) {
        ArrayList<Admin> admins = dbController.getAdmin();
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();

        for (Admin admin : admins) {
            usernames.add(admin.getUsername());
            passwords.add(admin.getPassword());
        }
        return usernames.contains(username) && passwords.contains(password);
    }
}
