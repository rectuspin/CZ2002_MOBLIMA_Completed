import controller.DBController;

import java.util.Scanner;

import static view.LandingPageView.*;

public class MoblimaApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        int choice = 0;
        boolean stillRunning = true;
        DBController dbController = DBController.getInstance();
        dbController.load();
        while (stillRunning) {
            System.out.println("=====================================================================");
            System.out.println("Welcome to MOvie Booking and LIsting Management Application (MOBLIMA)\n");
            System.out.println("Enter your options: ");
            System.out.println("1. Login as admin");
            System.out.println("2. Login as customer");
            System.out.println("3. Create admin account");
            System.out.println("4. Create customer account");
            System.out.println("5. Quit");
            System.out.print("Option: ");
            choice = scanner.nextInt();

//            "\n=======================================\n" +
//                    "|       Showtime Listing Setting      |\n" +
//                    "=======================================\n" +
//                    "| (1) Create Showtime listing         |\n" +
//                    "| (2) Update Showtime listing         |\n" +
//                    "| (3) Remove Showtime listing         |\n" +
//                    "| (4) Go back                         |\n" +
//                    "======================================="
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
                    dbController.save();
                    break;

            }
        }
    }
}