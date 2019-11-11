package view;

import controller.DBController;
import model.account.Customer;
import model.cinema.Cineplex;
import model.movie.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MovieGoerView {
    private static final DBController dbController = DBController.getInstance();


    //creating dummy movie, cineplex and users - have to actually obtain from the admin dept!
    {

        //Customer[] customer;
    }


    public static void movieGoerView() {
        int choice;
        Scanner sc = new Scanner(System.in);
        MovieGoerMainMenuView movieListView = new MovieGoerMainMenuView();
        Customer[] users = new Customer[10];
        HashMap<String, Cineplex> cineplexes = dbController.getCineplexes();
        ArrayList<Cineplex> cineplex = new ArrayList<>(cineplexes.values());
//        ArrayList<Movie> moviesList = dbController.getMovies();
        Movie[] moviesList = new Movie[15];
        Cineplex[] cathay = new Cineplex[10];
        do {
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
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    movieListView.printMovieList(moviesList);

                }
                case 2: {
                    movieListView.searchMovieList(cathay, moviesList);
                }
                case 3: {
                    movieListView.viewDetails(moviesList);
                }
                case 4: {
                    //list all cineplexes;
                    movieListView.printCineplex(cathay, moviesList);
                }
                case 5: {
                    //checks seat availablit
                    movieListView.checkSeatAvailability(cathay, moviesList);
                }
                case 6: {
//                    System.out.println("Please Enter your email address: " );
//                    String custEmail = sc.nextLine();
//                    for (int i=0;i<Array.getLength(users);i++) {
//                        if (custEmail.equalsIgnoreCase(users[i].getEmail())) {
//                            movieListView.doBooking(cathay, moviesList, users[i]);
//                        }
//                    }

                }
                case 7: {
                    // View Top 5 movies ranked by ticket sales;
                }
                case 8: {
                    // View Top 5 movies ranked by overall reviewers’ ratings;
                }
                case 9: {
//                    System.out.println("Please Enter your email address: " );
//                    String custEmail = sc.nextLine();
//                    for (int i=0;i<Array.getLength(users);i++){
//                        if (custEmail.equalsIgnoreCase(users[i].getEmail())) {
//                            if (users[i].getBookingHistory().size() > 0) {
//                                movieListView.printBookingHistory(users[i]);
//                            } else {
//                                System.out.println("Booking history is empty");
//                            }
//                        }
//                    }


                }
            }
        }
        while (choice != 10);


    }
}
