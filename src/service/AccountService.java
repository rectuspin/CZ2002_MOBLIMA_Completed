package service;

import controller.DBController;
import controller.SerializedDB;
import model.account.Admin;
import model.account.Customer;

import java.util.ArrayList;

public class AccountService {
    private static DBController dbController = DBController.getInstance();


    public static boolean movieGoerLoginService(Customer customer) {
        return dbController.getCustomer().contains(customer);
    }

    //to do
    public static boolean createMovieGoerAccount(String username, String password, String phoneNumber, String email) {
        // check if phone number is numeric
        if (phoneNumber.length() != 8 || !phoneNumber.matches("[0-9]+")) {
            System.out.println("Phone number is invalid. Please try again");
            return false;
        }
        dbController.addCustomer(new Customer(username, password, phoneNumber, email));
        return true;
    }

    public static boolean adminLoginService(Admin admin) {
        return dbController.getCustomer().contains(admin);
    }

    //to do
    public static void createAdminAccount(String username, String password) {
        dbController.addAdmin(username, password);
    }


    public static boolean checkIfCustomerAccountExists(String username, String password) {
        ArrayList<Customer> customers = dbController.getCustomer();

        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();
        for (Customer customer : customers) {
            System.out.println(customer.getUserName());
            System.out.println(customer.getPassword());
            usernames.add(customer.getUserName());
            passwords.add(customer.getPassword());
        }
        return usernames.contains(username) && passwords.contains(password);
    }

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
