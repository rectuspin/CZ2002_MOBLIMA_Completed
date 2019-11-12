package view;

import controller.DBController;
import model.account.Customer;
import model.cinema.Cineplex;
import model.cinema.ShowTime;
import model.movie.Movie;
import service.MovieGoerCineplexService;

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
            System.out.println("Enter number of movies displayed on each page: ");
            int page = scanner.nextInt();
            for (int i = 0; i < (movies.size() / page) + 1; i++) {
                for (int j = i * page; j < i * (page + 1); j++) {
                    if (j == movies.size() - 1) return;
                    System.out.println(movies.get(j));
                }
                System.out.println();
                System.out.println("Next page (Y) Back (B)");
                char choice = scanner.next().toLowerCase().charAt(0);
                while (choice != 'y' && choice != 'b') {
                    System.out.println("Next page (Y) Back (B)");
                    choice = scanner.next().toLowerCase().charAt(0);
                }
                if (choice == 'b') return;
            }

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
                while (choice < movies.size()) {
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
                        System.out.println("Enter choice of show time to show available seats (enter b to go back)");
                        selected = scanner.nextInt();
                        if (selected == (int) ('b')) return;
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


        public static void doBooking (Cineplex[]cineplexList, Movie[]moviesList, Customer user){
////            printMovieList(moviesList);
//            //Choose Movie first
//            System.out.println("Enter number of movie you wish to view details for: ");
//            int chosen;
//            chosen = scanner.nextInt();
//            int i;
//            for (i = 0; i < Array.getLength(moviesList); i++) {
//                if (chosen == i + 1) {
//                    for (int j = 0; j < (services.getShowTimes(cineplexList, moviesList[i])).size(); j++) {
//                        System.out.println((j + 1) + services.getShowTimes(cineplexList, moviesList[i]).get(j).toString());
//                    }
//                }
//            }
//            //Choose showtime next
//            ShowTime selected = null;
//            System.out.println("Enter the number of the showtime you would like: ");
//            chosen = scanner.nextInt();
//            for (int m = 0; m < services.getShowTimes(cineplexList, moviesList[i]).size(); m++) {
//                if (chosen == (m + 1)) {
//                    selected = services.getShowTimes(cineplexList, moviesList[i]).get(m);
//                }
//            }
//            if (selected == null) {
//                System.out.println("You have entered a wrong invalid showtime");
//            }
//            //Print the available seats;
//            services.showAvailableSeats(selected);
//
//            //Booking process
//            System.out.println("How many seats would you like to book? ");
//            int numOfSeats;
//            numOfSeats = scanner.nextInt();
//            String[] seatSelection = new String[numOfSeats];
//            for (int j = 0; j < numOfSeats; j++) {
//                System.out.println("Enter the seat you would like to book ; For Eg: (A1): ");
//                seatSelection[j] = scanner.nextLine();
//            }
//            System.out.println("Enter the name you would like to book under: ");
//            String name;
//            name = scanner.nextLine();
//            services.makeBooking(selected, seatSelection, name);
//            //Should we call the method to add to the booking history?

        }

        public static void printBookingHistory (Customer user){
//            ArrayList<Booking> booked = user.getBookingHistory();
//            for (int i = 0; i < booked.size(); i++) {
//                System.out.println(booked.get(i).toString());
//            }
        }
}


