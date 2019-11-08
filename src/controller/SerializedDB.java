package controller;

import model.account.Admin;
import model.account.Customer;
import model.cinema.Cineplex;
import model.movie.Movie;
import model.transaction.Booking;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/*This is the database (SerializedDatabase) where it will store all the data into a temporary database when the
  application is running. This class is mainly used to:
  - store all the data into arrays which would be used to stored into the .dat database file
  - add/set/get cineplexes
  - set/get ticket pricing
  - add/get sales
 */

public class SerializedDB implements Serializable {
    private static SerializedDB serializedDB = null;
    private HashMap<String, Cineplex> cineplexes;
    private ArrayList<Movie> movies;
    private ArrayList<Admin> admins;
    private ArrayList<Customer> customers;
    private ArrayList<Booking> bookings;
    private ArrayList<LocalDate> publicHolidayDates;
    private HashMap<String, Integer> sales;
    private double publicHolidayCharges;
    private double weekendCharges;
    private double basePrice;


    public SerializedDB(){
        /**This constructor is for the SerializedDB
         */
        admins = new ArrayList<>();
        cineplexes = new HashMap<>();
        movies = new ArrayList<>();
        customers = new ArrayList<>();
        bookings = new ArrayList<>();
        publicHolidayDates = new ArrayList<>();
        sales = new HashMap<>();
    }

    public HashMap<String, Cineplex> getCineplexes() {
        /**This method is used to return the cineplexes list
         * @return       An array list of cineplexes
         */
        return cineplexes;
    }

    public ArrayList<Movie> getMovies(){
        /**This method is used to return the movies list
         * @return       An array list of movies
         */
        return movies;
    }

    protected void setCineplexes(HashMap<String, Cineplex> cineplexes) {
        /**This method is used to update the cineplexes list
         * @param cineplexes      The selected cineplexes list to be updated
         */
        this.cineplexes = cineplexes;
    }

    protected void addCineplexes(Cineplex cineplex){
        /**This method is used to add a cineplex
         * @param cineplex       The selected cineplex to be added to the cineplexes list
         */
        this.cineplexes.put(cineplex.getName(), cineplex);
    }

    protected ArrayList<Admin> getAdmins() {
        /**This method is used to return the admin list
         * @return      An array list containing a list of admin would be returned
         */
        return admins;
    }

    protected void setAdmins(ArrayList<Admin> admins) {
        /**This method is used to add an admin
         * @param admins      The selected admin to be added to the admin list
         */
        this.admins = admins;
    }

    protected ArrayList<Customer> getCustomers() {
        /**This method is used to return the list of customer
         * @return     An array list containing a list of customers would be returned
         */
        return customers;
    }

    protected void setCustomers(ArrayList<Customer> customers) {
        /**This method is used to update the list of customer
         * @param customers       An array list containing a list of customers would be used to update the list
         */
        this.customers = customers;
    }

    protected ArrayList<Booking> getBookings() {
        /**This method is used to get the list of bookings
         * @return      An array list containing a list of bookings would be returned
         */
        return bookings;
    }

    protected void setBookings(ArrayList<Booking> bookings) {
        /**This method is used to update the list of bookings
         * @param bookings       An array list containing a list of bookings would be used to update the list
         */
        this.bookings = bookings;
    }

    public double getPublicHolidayCharges(){
        /**This method is used to return the extra public holiday charges for a ticket
         * @return      The public holiday charges value
         */
        return publicHolidayCharges; }

    public double getWeekendCharges(){
        /**This method is used to return the extra weekend charges for a ticket
         * @return      The weekend charges value
         */
        return weekendCharges;}

    public double getBasePrice(){
        /**This method is used to return the base price of the ticket
         * @return      The base price value
         */
        return basePrice; }

    public ArrayList<LocalDate> getPublicHolidayDates(){
        /**This method is used to return the list of public holiday dates
         * @return      An array list of public holiday dates
         */
        return publicHolidayDates; }

    public void setTicketPricing(ArrayList<LocalDate> publicHolidayDates, double publicHolidayCharges, double weekendCharges, double basePrice){
        /**This methods will set the public holiday and weekend charges. It would also set the base price and the public
         * holiday dates with an array list.
         * @param publicHolidayDates        The array list containing the public holiday dates
         * @param publicHolidayCharges      The extra charges during a public holiday
         * @param weekendCharges            The extra charges during the weekends
         * @param basePrice                 The base price of a ticket
         */
        this.publicHolidayCharges = publicHolidayCharges;
        this.publicHolidayDates = publicHolidayDates;
        this.weekendCharges = weekendCharges;
        this.basePrice = basePrice;
    }

    public static SerializedDB getInstance()
    {
        /**This method is to return an instance of its own
         * @return:    An instance of SerializedDB
         */
        if (serializedDB == null)
            serializedDB = new SerializedDB();

        return serializedDB;
    }

    public void addSales(String movieName, int numOfTickets){
        /**This method is defined to increment the sales each time based on the number of tickets for each booking
         * @param movie         The name of the movie
         * @param numOfTickets  The number of tickets sold for the movie
         */
        sales.put(movieName, numOfTickets);
    }

    public HashMap<String, Integer> getSales(){
        /**This method is defined to get the array of the sales
         * @return      An array list of sales for each movie
         */
        return sales;
    }

    public Integer getSalesFigure(String movieName){
        /**This method is defined to get the sales for a given movie
         * @param movieName   the movie name used to get the number of sales
         * @return:           the number of sales for the specified movie
         */
        return sales.get(movieName);
    }
}
