package controller;

import model.AgeGroup;
import model.PublicHoliday;
import model.account.Admin;
import model.account.Customer;
import model.cinema.CinemaType;
import model.cinema.Cineplex;
import model.movie.Movie;
import model.movie.MovieEnums;
import model.movie.Review;
import model.transaction.Booking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is the database (SerializedDatabase) where it will store all the data into a temporary database when the
 * application is running. This class is mainly used to:
 * store all the data into arrays which would be used to stored into the .dat database file
 * add/set/get cineplexes
 * set/get ticket pricing
 * add/get sales
 **/

public class SerializedDB implements Serializable {
    /**
     * an instance of the serializedDB object
     */
    private static SerializedDB serializedDB = null;
    /**
     * a hashmap of Cineplex objects
     */
    private HashMap<String, Cineplex> cineplexes;
    /**
     * an arraylist of Movie objects
     */
    private ArrayList<Movie> movies;
    /**
     * an arraylist of Admin objects
     */
    private ArrayList<Admin> admins;
    /**
     * an arraylist of Customer objects
     */
    private ArrayList<Customer> customers;
    /**
     * an arraylist of Booking objects
     */
    private ArrayList<Booking> bookings;
    /**
     * an arraylist of public holiday dates
     */
    private ArrayList<PublicHoliday> publicHolidayDates;
    /**
     * a HashMap containing sales figures for each movie title
     */
    private HashMap<String, Integer> sales;
    /**
     * public holiday ticket price
     */
    private double publicHolidayCharges;
    /**
     * weekend ticket price
     */
    private double weekendCharges;
    /**
     * base ticket price
     */
    private double basePrice;
    /**
     * List of movieType enums - 3D, DIGITAL, IMAX
     */
    private List movieType;
    /**
     * List of cinema type enums - STANDARD, PLATINUM
     */
    private List cinemaType;
    /**
     * List of enums for ticket prices for different age groups
     */
    private List ageGroup;

    /**
     * Constructor is for the SerializedDB
     */
    public SerializedDB(){

        admins = new ArrayList<>();
        cineplexes = new HashMap<>();
        movies = new ArrayList<>();
        customers = new ArrayList<>();
        bookings = new ArrayList<>();
        publicHolidayDates = new ArrayList<>();
        sales = new HashMap<>();
        movieType = new ArrayList<>();
        cinemaType = new ArrayList<>();
        ageGroup = new ArrayList<>();
    }

    /**
     * This method is used to return the movieType enum list
     *
     * @return A movieType enum list is return
     */
    public List getMovieType(){

        return movieType;
    }

    /**This method is used to return the cinemaType enum list
     * @return A cinemaType enum list is return
     */
    public List getCinemaType(){

        return cinemaType;
    }

    /**This method is used to return the ageGroup enum list
     * @return A ageGroup enum list is return
     */
    public List getAgeGroup(){

        return ageGroup;
    }

    /**This method is used to set all the enums in the serializedDB
     */
    public void setEnum(){

        for (MovieEnums.MovieType m : MovieEnums.MovieType.values()){
            movieType.add(m.getTicketPrice());
        }

        //Saves all the different cinema type pricing into the database
        for (CinemaType c : CinemaType.values()){
            cinemaType.add(c.getTicketPrice());
        }

        //Saves all the different cinema type pricing into the database
        for (AgeGroup group : AgeGroup.values()){
            ageGroup.add(group.getTicketPrice());
        }
    }

    /**This method is used to return the cineplexes list
     * @return An array list of cineplexes
     */
    public HashMap<String, Cineplex> getCineplexes() {

        return cineplexes;
    }

    /**This method is used to return the movies list
     * @return An array list of movies
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /**This method is used to update the cineplexes list
     * @param cineplexes    The selected cineplexes list to be updated
     */
    protected void setCineplexes(HashMap<String, Cineplex> cineplexes) {

        this.cineplexes = cineplexes;
    }

    /**This method is used to add a cineplex
     * @param cineplex      The selected cineplex to be added to the cineplexes list
     */
    protected void addCineplexes(Cineplex cineplex){

        this.cineplexes.put(cineplex.getName(), cineplex);
    }

    /**This method is used to return the admin list
     * @return An array list containing a list of admin would be returned
     */
    protected ArrayList<Admin> getAdmins() {

        return admins;
    }

    /**This method is used to add an admin
     * @param admins        The selected admin to be added to the admin list
     */
    protected void setAdmins(ArrayList<Admin> admins) {

        this.admins = admins;
    }

    /**
     * method to add a Customer object to the arraylist of customer objects
     * @param customer
     */
    protected void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**This method is used to return the list of customer
     * @return An array list containing a list of customers would be returned
     */
    protected ArrayList<Customer> getCustomers() {

        return customers;
    }

    /**This method is used to update the list of customer
     * @param customers     An array list containing a list of customers would be used to update the list
     */
    protected void setCustomers(ArrayList<Customer> customers) {

        this.customers = customers;
    }

    /**This method is used to get the list of bookings
     * @return An array list containing a list of bookings would be returned
     */
    protected ArrayList<Booking> getBookings() {

        return bookings;
    }

    /**This method is used to update the list of bookings
     * @param bookings       An array list containing a list of bookings would be used to update the list
     */
    protected void setBookings(ArrayList<Booking> bookings) {

        this.bookings = bookings;
    }

    /**This method is used to return the extra public holiday charges for a ticket
     * @return The public holiday charges value
     */
    public double getPublicHolidayCharges() {

        return publicHolidayCharges;
    }

    /**This method is used to return the extra weekend charges for a ticket
     * @return The weekend charges value
     */
    public double getWeekendCharges() {

        return weekendCharges;
    }

    /**This method is used to return the base price of the ticket
     * @return The base price value
     */
    public double getBasePrice() {

        return basePrice;
    }

    /**This method is used to return the list of public holiday dates
     * @return An array list of public holiday dates
     */
    public ArrayList<PublicHoliday> getPublicHolidayDates() {

        return publicHolidayDates;
    }

    /**This methods will set the public holiday and weekend charges. It would also set the base price and the public
     * holiday dates with an array list.
     * @param publicHolidayDates        The array list containing the public holiday dates
     * @param publicHolidayCharges      The extra charges during a public holiday
     * @param weekendCharges            The extra charges during the weekends
     * @param basePrice                 The base price of a ticket
     */
    public void setTicketPricing(ArrayList<PublicHoliday> publicHolidayDates, double publicHolidayCharges, double weekendCharges, double basePrice) {

        this.publicHolidayCharges = publicHolidayCharges;
        this.publicHolidayDates = publicHolidayDates;
        this.weekendCharges = weekendCharges;
        this.basePrice = basePrice;
    }

    /**This method is to return an instance of its own
     * @return: An instance of SerializedDB
     */
    public static SerializedDB getInstance() {

        if (serializedDB == null)
            serializedDB = new SerializedDB();
        return serializedDB;
    }

    /**This method is to return an instance of its own
     * @return: An instance of SerializedDB
     */
    public static void setInstance(SerializedDB S) {
        serializedDB = S;
    }


    /**This method is defined to increment the sales each time based on the number of tickets for each booking
     * @param movieName       The name of the movie
     * @param numOfTickets  The number of tickets sold for the movie
     */
    public void addSales(String movieName, int numOfTickets){

        sales.put(movieName, numOfTickets);
    }

    /**
     * method to add Review object to a Movie object
     * @param movieOfChoice
     * @param review
     */
    public void addReview(Movie movieOfChoice, Review review) {
        for(Movie movie : movies) {
            if(movie.equals(movieOfChoice)) {
                movie.getReviews().add(review);
            }
        }
    }

    /**This method is defined to get the array of the sales
     * @return An array list of sales for each movie
     */
    public HashMap<String, Integer> getSales(){

        return sales;
    }

    /**This method is defined to get the sales for a given movie
     * @param   movieName   the movie name used to get the number of sales
     * @return: the number of sales for the specified movie
     */
    public Integer getSalesFigure(String movieName){

        return sales.get(movieName);
    }

    /**This method is defined to add movies into the temporary database
     * @param movie The movie created to be added into the database
     */
    public void addMovies(Movie movie){

        this.movies.add(movie);
    }

    /**This method is used to set the current list of movie database with another list of movies
     * @param movies a list of movies created by the admin
     */
    public void setMovies(ArrayList<Movie> movies){

        this.movies = movies;
    }

}
