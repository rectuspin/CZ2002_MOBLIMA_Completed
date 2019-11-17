package view;

import controller.DBController;
import model.account.Customer;
import model.cinema.Cineplex;
import model.movie.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static view.MovieGoerMainMenuView.*;
import static view.Top5MoviesView.printTop5Movies;
import static view.Top5MoviesView.printTop5MoviesByRatings;

/**
 * Class to display main menu for customer
 */
public class MovieGoerView {
    /**
     * static instance of DBController
     */
    private static final DBController dbController = DBController.getInstance();
    private static final Scanner scanner = new Scanner(System.in);


    /**
     * Method to display main menu view for customer
     *
     * @param customer
     */
    public static void movieGoerView(Customer customer) {
        int choice = 0;
        boolean continueRunning = true;
        MovieGoerMainMenuView movieListView = new MovieGoerMainMenuView();
        Customer[] users = new Customer[10];
        HashMap<String, Cineplex> cineplexes = dbController.getCineplexes();
        ArrayList<Cineplex> cineplex = new ArrayList<>(cineplexes.values());
        ArrayList<Movie> moviesList = dbController.getMovies();
        //Movie[] moviesList = new Movie[15];
        Cineplex[] cathay = new Cineplex[10];
        while (continueRunning) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("                           Booking Menu                             ");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("(1) List movies available: ");    //done
            System.out.println("(2) Search movies available: ");  //done
            System.out.println("(3) View Movie Details: ");       //done
            System.out.println("(4) List cineplexes available: ");  //done
            System.out.println("(5) Check seat availability: ");    //done
            System.out.println("(6) Book and purchase ticket: ");   //done
            System.out.println("(7) View Top 5 movies ranked by ticket sales: ");   //done
            System.out.println("(8) View Top 5 movies ranked by overall reviewers’ ratings: ");  //yet to do
            System.out.println("(9) Show booking history"); //done
            System.out.println("(10) Leave Rating"); //done
            System.out.println("(11) Quit");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Enter your choice:");

            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    //Prints Movie List
                    printMovieList();
                    break;
                case 2:
                    //Searches for a Movie
                    searchMovieList();
                    break;
                case 3:
                    //Prints details of a movie
                    viewDetails();
                    break;
                case 4:
                    //List all Cineplexes;
                    printCineplex();
                    break;
                case 5:
                    //Checks Seat Availablity
                    checkSeatAvailability();
                    break;
                case 6:
                    //Does Booking
                    doBooking(customer);
                    break;
                case 7:
                    //View Top 5 movies ranked by ticket sales;
                    printTop5Movies();
                    break;
                case 8:
                    // View Top 5 movies ranked by overall reviewers’ ratings;
                    printTop5MoviesByRatings();
                    break;
                case 9:
                    printBookingHistory();
                    break;
                case 10:
                    leaveReview(customer);
                    break;
                case 11:
                    continueRunning = false;
                    break;

            }
        }
    }


}

