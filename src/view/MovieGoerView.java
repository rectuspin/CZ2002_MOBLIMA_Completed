package view;

import controller.DBController;
import model.account.Customer;
import model.cinema.Cineplex;
import model.movie.Movie;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static view.MovieGoerMainMenuView.*;

public class MovieGoerView {
    private static final DBController dbController = DBController.getInstance();
    private static final Scanner scanner = new Scanner(System.in);



    public static void movieGoerView() {
        int choice = 0;
        boolean continueRunning = true;
        MovieGoerMainMenuView movieListView = new MovieGoerMainMenuView();
        Customer[] users = new Customer[10];
        HashMap<String, Cineplex> cineplexes = dbController.getCineplexes();
        ArrayList<Cineplex> cineplex = new ArrayList<>(cineplexes.values());
//        ArrayList<Movie> moviesList = dbController.getMovies();
        Movie[] moviesList = new Movie[15];
        Cineplex[] cathay = new Cineplex[10];
        while (continueRunning) {
            System.out.println("(1) List movies available: ");    //done
            System.out.println("(2) Search movies available: ");  //done
            System.out.println("(3) View Movie Details: ");       //done
            System.out.println("(4) List cineplexes available: ");  //done
            System.out.println("(5) Check seat availability: ");    //done
            System.out.println("(6) Book and purchase ticket: ");   //yet to do
            System.out.println("(7) View Top 5 movies ranked by ticket sales: ");   //haven't started
            System.out.println("(8) View Top 5 movies ranked by overall reviewers’ ratings: ");  //haven't started
            System.out.println("(9) Show booking history"); //to do - create an user class!
            System.out.println("(10) Quit");
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    printMovieList();
                    break;
                case 2:
                    searchMovieList();
                    break;
                case 3:
                    viewDetails();
                    break;
                case 4:
                    //list all cineplexes;
                    printCineplex();
                    break;
                case 5:
                    //checks seat availablity
                    checkSeatAvailability();
                    break;
                case 6:
                    System.out.println("Please Enter your email address: ");
                    String custEmail = scanner.nextLine();
                    for (int i = 0; i < Array.getLength(users); i++) {
                        if (custEmail.equalsIgnoreCase(users[i].getEmailAddress())) {
                            doBooking(cathay, moviesList, users[i]);
                        }
                    }
                    break;
                case 7:
                    // View Top 5 movies ranked by ticket sales;
                    break;
                case 8:
                    // View Top 5 movies ranked by overall reviewers’ ratings;
                    break;
                case 9:
                    System.out.println("Please Enter your email address: ");
                    custEmail = scanner.nextLine();
                    for (int i = 0; i < Array.getLength(users); i++) {
                        if (custEmail.equalsIgnoreCase(users[i].getEmailAddress())) {
                            if (users[i].getBookingHistory().size() > 0) {
                                printBookingHistory(users[i]);
                            } else {
                                System.out.println("Booking history is empty");
                            }
                        }
                    }
                    break;

                case 10:
                    continueRunning = false;
                    break;

            }
        }
    }


}

