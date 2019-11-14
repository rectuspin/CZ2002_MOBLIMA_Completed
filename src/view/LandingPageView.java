package view;

import controller.SerializedDB;

import java.util.Scanner;

import static service.AccountService.*;
import static view.AdminView.adminView;
import static view.MovieGoerView.movieGoerView;

public class LandingPageView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void adminLogin() {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        while (!checkIfAdminAccountExists(username, password)) {
            System.out.println("Sorry, that username or password is wrong. Please try again. (Enter 'q' to go back)");
            System.out.print("Enter your username: ");
            username = scanner.next();
            if (username.equals("q")) return;
            System.out.print("Enter your password: ");
            password = scanner.next();
        }
        System.out.println("Welcome, " + username);
        adminView();
    }


    public static void createAdmin() {
        // create account - username, password, phone number, email address
        System.out.print("Enter a username: ");
        String username = scanner.next();

        System.out.print("Enter a password: ");
        String password = scanner.next();

        while (checkIfAdminAccountExists(username, password)) {
            System.out.println("Sorry, that username or password is already taken. Please try again. (Enter 'q' to go back)");
            System.out.print("Enter your username: ");
            username = scanner.next();
            if (username.equals("q")) return;
            System.out.print("Enter your password: ");
            password = scanner.next();
        }
        createAdminAccount(username, password);
        System.out.println("You have successfully created an user account");
    }

    public static void customerLogin() {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        while (!checkIfCustomerAccountExists(username, password)) {
            System.out.println("Sorry, that username or password is wrong. Please try again. (Enter 'q' to go back)");
            System.out.print("Enter your username: ");
            username = scanner.next();
            if (username.equals("q")) return;
            System.out.print("Enter your password: ");
            password = scanner.next();
        }
        System.out.println("Welcome, " + username);
        movieGoerView(getCustomer(username));
    }

    public static void createCustomerAccount() {
        // create account - username, password, phone number, email address
        System.out.print("Enter a username: ");
        String username = scanner.next();

        System.out.print("Enter a password: ");
        String password = scanner.next();

        while (checkIfCustomerAccountExists(username, password)) {
            System.out.println("Sorry, that username or password is already taken. Please try again. (Enter 'q' to go back)");
            System.out.print("Enter your username: ");
            username = scanner.next();
            if (username.equals("q")) return;
            System.out.print("Enter your password: ");
            password = scanner.next();
        }

        System.out.println("Enter your contact details");
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.next();
        System.out.print("Enter your email: ");
        String email = scanner.next();
        while (!createMovieGoerAccount(username, password, phoneNumber, email)) {
            System.out.println("Enter your contact details (Enter q to quit)");
            System.out.print("Enter your phone number: ");
            phoneNumber = scanner.next();
            if (phoneNumber.equals("q")) return;
            System.out.print("Enter your email: ");
            email = scanner.next();
        }
        System.out.println("You have successfully created an user account");
    }
}


