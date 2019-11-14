package view;

import controller.DBController;
import model.account.Customer;
import model.cinema.Cineplex;
import model.cinema.ShowTime;
import model.movie.Movie;
import model.transaction.Booking;
import service.MovieGoerCineplexService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieGoerMainMenuView {

    private static DBController dbController = DBController.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private static MovieGoerCineplexService services = new MovieGoerCineplexService();

    public static void printMovieList() {
        try {
            ArrayList<Movie> movies = dbController.getMovies();
            int number = 1;
            for (Movie movie : movies) {
                System.out.printf("[%d] %s\n", number++, movie.getTitle());
            }
            System.out.println();
        } catch (NullPointerException e) {
            System.out.println("No movies available right now! Try again later");
        }
    }

    public static void searchMovieList() {
        try {
            System.out.println("Enter the name of the movie to be searched: ");
            String movieSearched = scanner.nextLine();
            ArrayList<Movie> movies = dbController.getMovies();
            ArrayList<Cineplex> cineplexes = new ArrayList<>(dbController.getCineplexes().values());
            for (Movie movie : movies) {
                if (movieSearched.toLowerCase().equals(movie.getTitle().toLowerCase())) {
                    for (ShowTime showTime : services.getShowTimes(cineplexes, movie)) {
                        System.out.println(showTime);
                    }
                    return;
                }
            }
            System.out.println("Movie not found!");
        } catch (NullPointerException e) {
            System.out.println("No movies available right now! Try again later!");
        }
    }


    public static void viewDetails() {
        try {
            ArrayList<Movie> movies = dbController.getMovies();
            for (Movie movie : movies) {
                System.out.println(movie);
            }
            System.out.println();

        } catch (NullPointerException e) {
            System.out.println("There are no cineplexes available right now! Sorry!");
        }
    }

    public static void printCineplex() {
        try {
            ArrayList<Cineplex> cineplexes = new ArrayList<>(dbController.getCineplexes().values());
            for (int i = 0; i < cineplexes.size(); i++) {
                System.out.printf("[%d] %s\n", (i + 1), cineplexes.get(i).getName());
            }
            System.out.println("Would you like to select a cineplex to view the available movies? (Y-Yes and N-No): ");
            char choice = scanner.next().toLowerCase().charAt(0);
            while (choice != 'y' && choice != 'n') {
                System.out.println("Would you like to select a cineplex to view the available movies? (Y-Yes and N-No): ");
                choice = scanner.next().toLowerCase().charAt(0);
            }
            if (choice == 'y') {
                System.out.println("Enter the number of the cineplex you would like to view movie times for: ");
                int cineplexIndex = scanner.nextInt();
                while (cineplexIndex > cineplexes.size()) {
                    System.out.println("Enter the number of the cineplex you would like to view movie times for: ");
                    cineplexIndex = scanner.nextInt();
                }
                Cineplex cineplexOfChoice = cineplexes.get(cineplexIndex - 1);
                ArrayList<ShowTime> showTimes = services.getShowTimes(cineplexOfChoice);
                for (ShowTime showTime : showTimes) {
                    System.out.println(showTime);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("There are no cineplexes available right now! Sorry!");
        }
        }

    public static void checkSeatAvailability() {
        try {
            System.out.println("Would you like to search by (1) Movie or by (2) Cineplex ? - Enter choice 1 or 2");
            int searchBy = scanner.nextInt();
            while (searchBy != 1 && searchBy != 2) {
                System.out.println("Would you like to search by (1) Movie or by (2) Cineplex ? - Enter choice 1 or 2");
                searchBy = scanner.nextInt();
            }

            //search by movie
            if (searchBy == 1) {
                ArrayList<Cineplex> cineplexes = new ArrayList<>(dbController.getCineplexes().values());
                ArrayList<Movie> movies = dbController.getMovies();
                for (int i = 0; i < movies.size(); i++) {
                    System.out.printf("[%d] %s\n", (i + 1), movies.get(i).getTitle());
                }

                //exception handling ADD
                System.out.println("Enter the movie you wish to view seat availablity for (1, 2 and so on): ");
                int choice = scanner.nextInt();
                while (choice >= movies.size() + 1) {
                    System.out.println("Enter the movie you wish to view seat availablity for (1, 2 and so on): ");
                    choice = scanner.nextInt();
                }
                String movieCheckSeats = movies.get(choice - 1).getTitle();
                System.out.printf("You have chosen (%d) %s", choice, movieCheckSeats);


                if (cineplexes.isEmpty()) {
                    System.out.println("There are no cineplexes open");
                } else {
                    //print all showTimes for the cineplexes which have the searched movie
                    System.out.println("This movie is available at: ");

                    // all show times of searched movie in the next 3 days
                    ArrayList<ShowTime> thisMovieShows = services.getShowTimes(movies.get(choice - 1));

                    //iterate thorugh array of showTimes and print available seats
                    int showTimeIndex = 0;
                    for (ShowTime showTime : thisMovieShows) {
                        System.out.println((++showTimeIndex) + ". " + showTime);
                    }

                    while (true) {
                        int selected = showTimeIndex + 1;
                        System.out.println("Enter choice of show time to show available seats (enter -1 to go back)");
                        selected = scanner.nextInt();
                        if (selected == -1) return;
                        while (selected - 1 >= showTimeIndex) {
                            System.out.println("Enter choice of show time to show available seats");
                            selected = scanner.nextInt();
                        }
                        services.showAvailableSeats(thisMovieShows.get(selected - 1));
                    }
                }
            } else if (searchBy == 2) {            //search by cineplex

                ArrayList<Cineplex> cineplexes = new ArrayList<>(dbController.getCineplexes().values());
                for (int i = 0; i < cineplexes.size(); i++) {
                    System.out.printf("[%d] %s\n", (i + 1), cineplexes.get(i).getName());
                }

                System.out.println("Enter the cineplex you wish to view seat availablity for: ");
                int choice = scanner.nextInt();   //choice2 - 1 = index required
                while (choice > cineplexes.size() - 1) {
                    System.out.println("Enter the cineplex you wish to view seat availablity for: ");
                    choice = scanner.nextInt();
                }
                String cineplexCheckSeats = cineplexes.get(choice - 1).getName();
                System.out.printf("You have chosen (%d) %s", choice, cineplexCheckSeats);


                System.out.println("The movies available at this cineplex are: ");
                Cineplex cineplex = cineplexes.get(choice - 1);
                ArrayList<ShowTime> showTimes = services.getShowTimes(cineplex);
                if (showTimes.size() == 0) {
                    System.out.println("There are no movies showing!");
                } else {
                    System.out.print("Show Times for this cineplex for the next three days");
                    int i = 0;
                    for (ShowTime showTime : showTimes) {
                        System.out.println((++i) + ": " + showTime);
                    }

                    while (true) {
                        System.out.println("Select the show time to check seat availability (enter b to go back)");
                        int showTimeIndex = scanner.nextInt();
                        while (showTimeIndex != (int) ('b') && showTimeIndex - 1 >= i) {
                            System.out.println("Select the show time to check seat availability (enter b to go back)");
                            showTimeIndex = scanner.nextInt();
                        }
                        if (showTimeIndex == (int) ('b')) return;
                        services.showAvailableSeats(showTimes.get(showTimeIndex));
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("No movies or cineplexes are available! Sorry!");
        }
        }

    public static void doBooking() {
        try {
            System.out.println("Would you like to book by (1) Movie or by (2) Cineplex ? - Enter choice 1 or 2");
            int searchBy = scanner.nextInt();
            while (searchBy != 1 && searchBy != 2) {
                System.out.println("Would you like to search by (1) Movie or by (2) Cineplex ? - Enter choice 1 or 2");
                searchBy = scanner.nextInt();
            }

            //Book by movie
            if (searchBy == 1) {
                ArrayList<Cineplex> cineplexes = new ArrayList<>(dbController.getCineplexes().values());
                ArrayList<Movie> movies = dbController.getMovies();
                for (int i = 0; i < movies.size(); i++) {
                    System.out.printf("[%d] %s\n", (i + 1), movies.get(i).getTitle());
                }

                //exception handling ADD
                System.out.println("Enter the movie you wish to book (1, 2 and so on): ");
                int choice = scanner.nextInt();
                while (choice < movies.size()) {
                    System.out.println("Enter the movie you wish to book (1, 2 and so on): ");
                    choice = scanner.nextInt();
                }
                String movieCheckSeats = movies.get(choice - 1).getTitle();
                System.out.printf("You have chosen (%d) %s", choice, movieCheckSeats);

                if (cineplexes.isEmpty()) {
                    System.out.println("There are no cineplexes open");
                } else {
                    //print all showTimes for the cineplexes which have the searched movie
                    System.out.println("This movie is available at: ");

                    // all show times of searched movie in the next 3 days
                    ArrayList<ShowTime> thisMovieShows = services.getShowTimes(movies.get(choice - 1));

                    //iterate thorugh array of showTimes and print available seats
                    int showTimeIndex = 0;
                    for (ShowTime showTime : thisMovieShows) {
                        System.out.println((++showTimeIndex) + ". " + showTime);
                    }


                    int selected = showTimeIndex + 1;
                    System.out.println("Enter choice of show time you wish to book seats for (enter b to go back)");
                    selected = scanner.nextInt();
                    if (selected == (int) ('b')) return;
                    while (selected - 1 >= showTimeIndex) {
                        System.out.println("Enter choice of show time to book available seats");
                        selected = scanner.nextInt();
                    }
                    System.out.println("How many seats would you like to book? ");
                    int numOfSeats;
                    numOfSeats = scanner.nextInt();
                    String dummy = scanner.nextLine();
                    String[] seatSelection = new String[numOfSeats];
                    for (int j = 0; j < numOfSeats; j++) {
                        System.out.println("Enter the seat you would like to book ; For Eg: (A1): ");
                        seatSelection[j] = scanner.nextLine();
                    }
                    System.out.println("Enter the name you would like to book under: ");
                    String name;
                    name = scanner.nextLine();
                    services.makeBooking(thisMovieShows.get(showTimeIndex - 1), seatSelection, name);

                    //Should we call the method to add to the booking history?


                }
            } else if (searchBy == 2) {            //Book by cineplex

                ArrayList<Cineplex> cineplexes = new ArrayList<>(dbController.getCineplexes().values());
                for (int i = 0; i < cineplexes.size(); i++) {
                    System.out.printf("[%d] %s\n", (i + 1), cineplexes.get(i).getName());
                }

                System.out.println("Enter the cineplex you wish to book in: ");
                int choice = scanner.nextInt();   //choice2 - 1 = index required
                while (choice > cineplexes.size() - 1) {
                    System.out.println("Enter the cineplex you wish to book in: ");
                    choice = scanner.nextInt();
                }
                String cineplexCheckSeats = cineplexes.get(choice - 1).getName();
                System.out.printf("You have chosen (%d) %s", choice, cineplexCheckSeats);


                System.out.println("The movies available at this cineplex are: ");
                Cineplex cineplex = cineplexes.get(choice - 1);
                ArrayList<ShowTime> showTimes = services.getShowTimes(cineplex);
                if (showTimes.size() == 0) {
                    System.out.println("There are no movies showing!");
                } else {
                    System.out.print("Show Times for this cineplex for the next three days");
                    int i = 0;
                    for (ShowTime showTime : showTimes) {
                        System.out.println((++i) + ": " + showTime);
                    }

                    while (true) {
                        System.out.println("Select the show time to book tickets (enter b to go back)");
                        int showTimeIndex = scanner.nextInt();
                        while (showTimeIndex != (int) ('b') && showTimeIndex - 1 >= i) {
                            System.out.println("Select the show time to book tickets (enter b to go back)");
                            showTimeIndex = scanner.nextInt();
                        }
                        if (showTimeIndex == (int) ('b')) return;
                        System.out.println("How many seats would you like to book? ");
                        int numOfSeats;
                        numOfSeats = scanner.nextInt();
                        String[] seatSelection = new String[numOfSeats];
                        for (int j = 0; j < numOfSeats; j++) {
                            System.out.println("Enter the seat you would like to book ; For Eg: (A1): ");
                            seatSelection[j] = scanner.nextLine();
                        }
                        System.out.println("Enter the name you would like to book under: ");
                        String name;
                        name = scanner.nextLine();
                        services.makeBooking(showTimes.get(showTimeIndex), seatSelection, name);
                        //Should we call the method to add to the booking history?
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("No movies or cineplexes are available! Sorry!");
        }
    }

    public static void printBookingHistory() {
        ArrayList<Customer> users = dbController.getCustomer();
        String custEmail;
        Boolean userRight = Boolean.FALSE;
        System.out.println("Please Enter your email address: ");
        custEmail = scanner.nextLine();
        for (int i = 0; i < Array.getLength(users); i++) {
            if (custEmail.equalsIgnoreCase(users.get(i).getEmailAddress())) {
                userRight = Boolean.TRUE;
                if (users.get(i).getBookingHistory().size() > 0) {
                    ArrayList<Booking> booked = users.get(i).getBookingHistory();
                    if (booked.size() == 0)
                        System.out.println("No bookings have been done");
                    for (int j = 0; j < booked.size(); j++) {
                        System.out.println(booked.get(j).toString());
                    }
                    break;
                }
            }
        }
        if (!userRight)
            System.out.println("Invalid email ID entered!");
    }
}


