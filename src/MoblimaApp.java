import controller.DBController;

import java.util.Scanner;

import static view.LandingPageView.*;

public class MoblimaApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        int choice = 0;
        boolean stillRunning = true;
        DBController dbController = DBController.getInstance();
        while (stillRunning) {
            System.out.println("Welcome to MOvie Booking and LIsting Management Application (MOBLIMA)\n");
            System.out.println("Enter your options: ");
            System.out.println("1. Login as admin");
            System.out.println("2. Login as customer");
            System.out.println("3. Create admin account");
            System.out.println("4. Create customer account");
            System.out.println("5. Quit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    createAdmin();
                    break;
                case 4:
                    createCustomerAccount();
                    break;
                case 5:
                    stillRunning = false;
                    System.out.println("bye");

                    break;

            }
        }
    }
}