package view;

import controller.DBController;
import model.cinema.Cinema;
import model.cinema.CinemaType;
import model.cinema.Cineplex;
import service.AdminCineplexService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static view.ChangeShowtimeListingView.printCinema;
import static view.ChangeShowtimeListingView.printCineplex;

/**
 * class to display options for adding/removing/updating cineplex and cinema
 */
public class UpdateCineplexOrCinemaView {
    public static AdminCineplexService adminCineplexService = new AdminCineplexService();

    /**
     * Prints the menu to update,create and remove cineplex and cinemas
     */
    public static void updateCineplexOrCinemaView() {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        String cineplexName;
        String cinemaName;
        int choice;
        do {
            System.out.println("\n======================================\n" +
                    "|            Admin Setting           |\n" +
                    "======================================\n" +
                    "| (1) Create Cineplex                |\n" +
                    "| (2) Remove Cineplex                |\n" +
                    "| (3) Create Cinema                  |\n" +
                    "| (4) Remove Cinema                  |\n" +
                    "| (5) Go back                        |\n" +
                    "======================================");
            System.out.print("Option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("What is the name of a new cineplex?");
                    cineplexName = sc1.nextLine();
                    Cineplex cineplex = new Cineplex(cineplexName);
                    adminCineplexService.addCineplex(cineplex);
                    break;

                case 2:
                    System.out.println("What cineplex do you want to delete?");
                    cineplex = displayCineplexes();
                    if (cineplex != null) {
                        cineplexName = cineplex.getName();
                        adminCineplexService.removeCineplex(cineplexName);
                    }
                    break;

                case 3:
                    System.out.println("To what cineplex do you want to add a cinema?");
                    cineplex = displayCineplexes();
                    if (cineplex != null) {
                        System.out.println("What is the name of a new cinema?");
                        cinemaName = sc1.nextLine();
                        System.out.println("What is the maximum amount of rows in a new cinema?");
                        char maxRow = sc.next().charAt(0);
                        System.out.println("What is the maximum amount of columns in a new cinema?");
                        int maxCol = sc.nextInt();
                        System.out.println("What is the type of a new cinema? Choose 1, 2 or 3.\n" +
                                "1. Standard\n" +
                                "2. Suites\n" +
                                "3. IMAX");

                        int type = 0;
                        CinemaType cinemaType = null;
                        do {
                            type = sc.nextInt();
                            if (type == 1) {
                                cinemaType = CinemaType.STANDARD;
                            } else if (type == 2) {
                                cinemaType = CinemaType.PLATINUM_MOVIE_SUITES;
                            } else if (type == 3) {
                                cinemaType = CinemaType.IMAX;
                            } else {
                                System.out.println("Please, choose 1, 2 or 3");
                            }
                        } while (type != 1 && type != 2 && type != 3);
                        Cinema cinema = new Cinema(cinemaName, maxRow, maxCol, cinemaType);
                        adminCineplexService.addCinema(cineplex, cinema);
                    }
                    break;

                case 4:
                    System.out.println("From what cineplex do you want to delete a cinema?");
                    cineplex = displayCineplexes();
                    if (cineplex != null) {
                        System.out.println("What cinema do you want to delete?");
                        Cinema cinema = displayCinemas(cineplex);
                        if (cinema != null) {
                            adminCineplexService.removeCinema(cineplex, cinema);
                        }
                    }
                    break;
            }
        } while (choice < 5);

    }

    /**
     * Method to list all exisiting cineplexes
     *
     * @return Cineplex
     */
    public static Cineplex displayCineplexes() {
        Scanner scanner = new Scanner(System.in);
        DBController dbController = DBController.getInstance();
        ArrayList<String> cineplexNames = new ArrayList<>(dbController.getCineplexes().keySet());
        HashMap<String, Cineplex> cineplexes = dbController.getCineplexes();
        if (cineplexNames.size() != 0) {
            printCineplex();
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                Cineplex cineplexOfChoice = cineplexes.get(cineplexNames.get(choice - 1));
                return cineplexOfChoice;
            } else {
                System.out.println("Please input a number.");
                return null;
            }
        }
        System.out.println("There are no cineplexes. Returning...");
        return null;
    }

    /**
     * Method to print all the exsiting cinemas in a particular cineplex
     * @param cineplex
     * @return Cineplex
     */
    public static Cinema displayCinemas(Cineplex cineplex) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cinema> cinemas = cineplex.getCinemas();
        if (cinemas.size() != 0) {
            printCinema(cineplex);
            if (scanner.hasNextInt()) {
                Cinema cinema = cinemas.get(scanner.nextInt() - 1);
                return cinema;
            } else {
                System.out.println("Please input a number.");
                return null;
            }
        }
        System.out.println("There are no cinemas in this cineplex. Returning...");
        return null;
    }
}
