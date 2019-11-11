package view;

import controller.DBController;
import model.account.Customer;
import model.cinema.Cineplex;
import model.cinema.ShowTime;
import model.movie.Movie;
import model.transaction.Booking;
import service.MovieGoerCineplexService;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class MovieGoerMainMenuView {

    private static DBController dbController = DBController.getInstance();
    private static final Scanner sc = new Scanner(System.in);
    //creating a dummy cineplex object to test class- real one from admin should be replaced
    //Cineplex [] cathay = new Cineplex[4];
    private static MovieGoerCineplexService services = new MovieGoerCineplexService();

    public static void printMovieList() {
        ArrayList<Movie> movies = dbController.getMovies();
        int number = 1;
        try {
            for (Movie movie : movies) {
                System.out.printf("[%d] %s\n", number, movie.getTitle());
            }
        } catch (NullPointerException e) {
//        if (Array.getLength(moviesList) < 0) {
//            System.out.println("Print no available movies at the moment");
//
//        } else {
//            for (int i = 0; i < Array.getLength(moviesList); i++) {
//
//            }
//        }
    }

        public static void searchMovieList () {
        System.out.println("Enter the name of the movie to be searched: ");
        String movieSearched = sc.nextLine();
        int i;
        for (i = 0; i < Array.getLength(moviesList); i++) {
            if (movieSearched == moviesList[i].getTitle()) {

                for (int j = 0; j < (services.getShowTimes(listCineplex, moviesList[i])).size(); j++) {
                    System.out.println(services.getShowTimes(listCineplex, moviesList[i]).get(j).toString());
                }
                break;
            }
        }
        if (i == Array.getLength(moviesList)) {
            System.out.print("No such movies!");
        }
    }

        public static void viewDetails (Movie[]moviesList){
        printMovieList(moviesList);
        System.out.println("Enter number of movie you wish to view details for: ");
        int chosen = sc.nextInt();
        for (int i = 0; i < Array.getLength(moviesList); i++) {
            if (chosen == i + 1) {
                moviesList[i].toString();
            }
        }

    }

        public static void printCineplex (Cineplex[]listCineplex, Movie[]moviesList){
        int i;
        for (i = 0; i < Array.getLength(listCineplex); i++) {
            System.out.printf("[%d] %s\n", (i + 1), listCineplex[i].getName());
        }
        System.out.println("Would you like to select a cineplex to view the available movies? (Y-Yes and N-No): ");
        if (sc.next().charAt(0) == 'Y') {
            System.out.println("Enter number of the cineplex you would like to view movie times for: ");
            for (i = 0; i < Array.getLength(listCineplex); i++) {
                int chosen = sc.nextInt();
                if (chosen == i + 1) {

                    System.out.println("The movies available at this cineplex are: ");
                    if (Array.getLength(moviesList) < 0) {
                        System.out.println("There are no movies showing!");
                    } else {
                        HashMap<LocalDate, ArrayList<ShowTime>> thisCineplexShows = listCineplex[chosen - 1].getShowTimes();
                        System.out.print(thisCineplexShows);
                    }
                }
            }
        }
    }

        public static void checkSeatAvailability (Cineplex[]cineplexList, Movie[]moviesList){
        System.out.println("Would you like to search by (1)Movie or by (2)Cineplex?- Enter choice 1 or 2");
        int searchBy = sc.nextInt();
        System.out.print("You have chosen choice " + searchBy);

        //search by movie
        if (searchBy == 1) {

            for (int i = 0; i < Array.getLength(moviesList); i++) {
                System.out.printf("[%d] %s\n", (i + 1), moviesList[i].getTitle());
            }

            //exception handling ADD
            System.out.println("Enter the movie you wish to view seat availablity for (1, 2 and so on): ");
            int choice1 = sc.nextInt();   //choice1 - 1 = index required
            String movieCheckSeats = moviesList[choice1 - 1].getTitle();
            System.out.printf("You have chosen (%d) %s", choice1, movieCheckSeats);

            System.out.println("This movie is available at: ");

            if (Array.getLength(cineplexList) < 0) {
                System.out.println("There are no cineplexes open");
            } else {
                int j = 0;

                //print all showTimes for the cineplexes which have the searched movie
                ArrayList<ShowTime> thisMovieShows = services.getShowTimes(cineplexList, moviesList[choice1 - 1]);

                //iterate thorugh array ofshowTimes and print available seats
                for (int i = 0; i < Array.getLength(Array.getLength(thisMovieShows)); i++) {
                    services.showAvailableSeats(thisMovieShows.get(i));
                }
            }
        } else if (searchBy == 2) {            //search by cineplex

            for (int i = 0; i < Array.getLength(cineplexList); i++) {
                System.out.printf("[%d] %s\n", (i + 1), cineplexList[i].getName());
            }

            System.out.println("Enter the cineplex you wish to view seat availablity for (1, 2, 3 and so on): ");
            int choice2 = sc.nextInt();   //choice2 - 1 = index required
            String cineplexCheckSeats = cineplexList[choice2 - 1].getName();
            System.out.printf("You have chosen (%d) %s", choice2, cineplexCheckSeats);


            System.out.println("The movies available at this cineplex are: ");
            if (Array.getLength(moviesList) < 0) {
                System.out.println("There are no movies showing!");
            } else {
                HashMap<LocalDate, ArrayList<ShowTime>> thisCineplexShows = cineplexList[choice2 - 1].getShowTimes();
                System.out.print(thisCineplexShows);


                //+print seats for each showTime
                //for that cineplex show all shows and seat availability
                for (HashMap.Entry<LocalDate, ArrayList<ShowTime>> entry : thisCineplexShows.entrySet()) {
                    ArrayList<ShowTime> showTimes = entry.getValue();
                    Iterator iter = showTimes.iterator();
                    while (iter.hasNext()) {
                        services.showAvailableSeats((ShowTime) iter.next()); //check casting at runtime
                    }
                    //thisCineplexShows.values().forEach(showTimes(forEach (showTime->showAvailableSeats(showTime))));
                    // do something with key and/or tab
                }
            }
        }
    }


        public static void doBooking (Cineplex[]cineplexList, Movie[]moviesList, Customer user){
        printMovieList(moviesList);
        //Choose Movie first
        System.out.println("Enter number of movie you wish to view details for: ");
        int chosen;
        chosen = sc.nextInt();
        int i;
        for (i = 0; i < Array.getLength(moviesList); i++) {
            if (chosen == i + 1) {
                for (int j = 0; j < (services.getShowTimes(cineplexList, moviesList[i])).size(); j++) {
                    System.out.println((j + 1) + services.getShowTimes(cineplexList, moviesList[i]).get(j).toString());
                }
            }
        }
        //Choose showtime next
        ShowTime selected = null;
        System.out.println("Enter the number of the showtime you would like: ");
        chosen = sc.nextInt();
        for (int m = 0; m < services.getShowTimes(cineplexList, moviesList[i]).size(); m++) {
            if (chosen == (m + 1)) {
                selected = services.getShowTimes(cineplexList, moviesList[i]).get(m);
            }
        }
        if (selected == null) {
            System.out.println("You have entered a wrong invalid showtime");
        }
        //Print the available seats;
        services.showAvailableSeats(selected);

        //Booking process
        System.out.println("How many seats would you like to book? ");
        int numOfSeats;
        numOfSeats = sc.nextInt();
        String[] seatSelection = new String[numOfSeats];
        for (int j = 0; j < numOfSeats; j++) {
            System.out.println("Enter the seat you would like to book ; For Eg: (A1): ");
            seatSelection[j] = sc.nextLine();
        }
        System.out.println("Enter the name you would like to book under: ");
        String name;
        name = sc.nextLine();
        services.makeBooking(selected, seatSelection, name);
        //Should we call the method to add to the booking history?

    }

        public static void printBookingHistory (Customer user){
        ArrayList<Booking> booked = user.getBookingHistory();
        for (int i = 0; i < booked.size(); i++) {
            System.out.println(booked.get(i).toString());
        }
    }
}

