package controller;

import model.AgeGroup;
import model.account.Admin;
import model.account.Customer;
import model.cinema.Cinema;
import model.cinema.CinemaType;
import model.cinema.Cineplex;
import model.cinema.ShowTime;
import model.movie.Movie;
import model.movie.MovieEnums;
import model.transaction.Booking;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static service.TicketPriceService.*;

/*This is a DBController (Database Controller) class where it will handle all database related task such as
  - create, read, update and delete
  - add/remove cinema
  - add/remove cineplex
  - add/remove showtimes
  - add/get sales
 */

public class DBController {

    private SerializedDB serializedDB = SerializedDB.getInstance();
    private static DBController dbController = null;
    private DBController(){ }

    public void createDB(String DBName) throws IOException {
        /**This method is defined to create the .dat file
         * @param  DBName       The name of the database that wants to be created
         * @throws IOException  If the file is not found
         */
        String dir = "src/database/";
        File myFile = new File(dir +DBName + ".dat");
        myFile.createNewFile();
    }

    public void deleteDB(String DBName) throws IOException {
        /**This method is defined to delete the .dat file
         * @param  DBName       The name of the database that wants to be delete
         * @throws IOException  If the file is not found
         */
        String dir = "src/database/";
        File myFile = new File(dir + DBName + ".dat");
        myFile.delete();
    }

    //Updates the data with a given list
    public void updateDB(SerializedDB serializedDB, String DBName) throws IOException {
        /**This method is defined to write data to .dat file
         * @param DBName    The name of the database that wants to be update
         * @param list      A list of stored items to be stored into the .dat file
         * @throws IOException  If the file is not found
         */
        String dir = "src/database/";
        File myFile = new File(dir + DBName + ".dat");
        if(!myFile.exists()) {
            myFile.createNewFile();
        }else {
            SerializeDB.writeSerializedObject(dir + DBName + ".dat", serializedDB);
        }
    }

    //Gets the data from the DB
    public SerializedDB readDB(String DBName) throws IOException {
        /**This method is defined to read data from .dat file
         * @param  DBName       The name of the database that wants to be read
         * @return              A list of stored items in the .dat file
         * @throws IOException  If the file is not found
         */
        String dir = "src/database/";
        SerializedDB serializedDB = new SerializedDB();
        File myFile = new File(dir + DBName + ".dat");
        if(!myFile.exists()) {
            myFile.createNewFile();
        }else {
            serializedDB = SerializeDB.readSerializedObject(dir + DBName + ".dat");
        }
        return serializedDB;
    }

    public void addCustomer(Customer customer) {
        serializedDB.addCustomer(customer);
    }

    public ArrayList<Customer> getCustomer() {
        return serializedDB.getCustomers();
    }

    public void addAdmin(String username, String password) {
        serializedDB.getAdmins().add(new Admin(username, password));
    }

    public ArrayList<Admin> getAdmin() {
        return serializedDB.getAdmins();
    }

    public ArrayList<Movie> getMovies() {
        return serializedDB.getMovies();
    }

    public HashMap<String, Cineplex> getCineplexes() {
        /**This method is defined to get the array of stored cineplex
         * @return An array of cineplex from the database
         */
        return serializedDB.getCineplexes();
    }

    public void addCineplex(Cineplex cineplex){
        /**This method is defined to add the cineplex into the temporary database
         * @param cineplex      The selected cineplex object to be added into the database
         */
        serializedDB.addCineplexes(cineplex);
    }

    public void removeCineplex(String name){
        /**This method is defined to remove the cineplex from the temporary database
         * @param name      The name of the cineplex to be removed from the database
         */
        HashMap<String, Cineplex> cineplexes = serializedDB.getCineplexes();
        cineplexes.remove(name);
        serializedDB.setCineplexes(cineplexes);
    }

    public void addCinema(Cineplex cineplex, Cinema cinema){
        /**This method is defined to add the cinema into the temporary database
         * @param cineplex      The selected cineplex of the cinema to be added
         * @param cinema        The selected cinema to be added
         */
        HashMap<String, Cineplex> cineplexes = serializedDB.getCineplexes();
        Cineplex selected_cineplex = cineplexes.get(cineplex.getName());
        ArrayList<Cinema> cinema_list= selected_cineplex.getCinemas();
        cinema_list.add(cinema);
        cineplex.setCinemas(cinema_list);
        cineplexes.replace(cineplex.getName(), cineplex);
        serializedDB.setCineplexes(cineplexes);
    }

    public void removeCinema(Cineplex cineplex, Cinema cinema){
        /**This method is defined to remove the cinema from the temporary database
         * @param cineplex      The selected cineplex of the cinema to be removed
         * @param cinema        The selected cinema to be removed
         */
        HashMap<String, Cineplex> cineplexes = serializedDB.getCineplexes();
        Cineplex selected_cineplex = cineplexes.get(cineplex.getName());
        ArrayList<Cinema> cinema_list= selected_cineplex.getCinemas();
        cinema_list.remove(cinema);
        cineplex.setCinemas(cinema_list);
        cineplexes.replace(cineplex.getName(), cineplex);
        serializedDB.setCineplexes(cineplexes);
    }

    public void addShowTimes(Cineplex cineplex, LocalDate dateOfMovie, ShowTime newShowTime){
        /**This method is defined to add the showtime into the temporary database
         * @param movie         The type of movie
         * @param dateOfMovie   The date of the showtime
         * @param timeOfMovie   The time of the showtime
         */
        serializedDB.getCineplexes().get(cineplex.getName()).getShowTimes().get(dateOfMovie).add(newShowTime);
    }

    public void removeShowTimes(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, Cinema cinema,
                                Cineplex cineplex){
        /**This method is defined to remove the showtime from the temporary database in the system
         * @param movie         The type of movie
         * @param dataOfMovie   The date of the showtime
         * @param timeOfMovie   The time of the showtime
         * @param cinema        The cinema that premieres the movie
         * @param cineplex      The cineplex that premieres the movie
         */
        ArrayList<ShowTime> showTimes = serializedDB.getCineplexes().get(cineplex.getName()).getShowTimes().get(dateOfMovie);
        for (ShowTime showTime : showTimes) {
            if (showTime.getMovie() == movie && showTime.getTimeOfMovie() == timeOfMovie &&
                    showTime.getCinema() == cinema) {
                serializedDB.getCineplexes().get(cineplex.getName()).getShowTimes().get(dateOfMovie).remove(showTime);
            }
        }
    }

    public static DBController getInstance()
    {
        /**This method is to return an instance of its own
         * @return: An instance of DBController
         */
        if (dbController == null)
            dbController = new DBController();

        return dbController;
    }


    public void load()  {
        /**This method is defined to load all the data from the database into the system
         * @throws IOException  If the file is not found
         */
        DBController dbController = DBController.getInstance();
        try {
            //Retrieve all the data from database to list
            SerializedDB serializedDBObj = dbController.readDB("SerializedDB");

            //Loads all the prices from the data into the system
            serializedDB.setTicketPricing(serializedDBObj.getPublicHolidayDates(), serializedDBObj.getPublicHolidayCharges(), serializedDBObj.getWeekendCharges(), serializedDBObj.getBasePrice());
            serializedDB.setCineplexes(serializedDBObj.getCineplexes());
            serializedDB.setAdmins(serializedDBObj.getAdmins());
            serializedDB.setBookings(serializedDBObj.getBookings());
            serializedDB.setCustomers(serializedDBObj.getCustomers());
            serializedDB.setMovies(serializedDBObj.getMovies());

            try {
                loadEnums(serializedDBObj);
            }catch(Exception e){
                System.out.println("[System: Error in loading enums]");
            }
            setAllPrices();
        } catch(IOException e){
            serializedDB = new SerializedDB();
        }
    }

    public void loadEnums(SerializedDB serializedDBObj){
        /**This method is defined to load the enums to the temporary database where it stores that data when the system
         * is running
         * @param serializedDBObj  The data extracted from the database
         */
        //Declaration of variable
        int i;

        //Loads all the movie type prices into the system
        i = 0;
        for (MovieEnums.MovieType m : MovieEnums.MovieType.values()) {
            m.setTicketPrice((double) serializedDBObj.getMovieType().get(i));
            i++;
        }

        //Loads all the cinema type prices into the system
        i = 0;
        for (CinemaType c : CinemaType.values()) {
            c.setTicketPrice((double) serializedDBObj.getCinemaType().get(i));
            i++;
        }

        //Loads all the discount prices for different citizen of different age group into the system
        i = 0;
        for (AgeGroup group : AgeGroup.values()) {
            group.setTicketPrice((double) serializedDBObj.getAgeGroup().get(i));
            i++;
        }
    }

    public void setEnums(){
        serializedDB.setEnum();
    }

    public void save() {
        /**This method is defined to save all the data from the database into the system
         * @throws IOException  If the file is not found
         */
        DBController dbController = DBController.getInstance();
        try {
            //Saves all the base/holiday/weekend pricing and holiday dates into the database
            dbController.updateDB(serializedDB, "SerializedDB");
        }catch(IOException e){
            try {
                createDB("SerializedDB");
                dbController.updateDB(serializedDB, "SerializedDB");
            }catch (IOException i){
                System.out.println("[System: Error in creating database]");
            }
        }
    }

    //
    public void addSales(Booking booking){
        /**This method is defined to increment the sales each time based on the number of tickets for each booking
         * @param booking  The booking selected to add to the sales
         */
        serializedDB.addSales(booking.getMovie().getTitle(), booking.getTickets().size());
    }

    public HashMap<String, Integer> getSales(){
        /**This method is defined to get the array of the sales
         * @return An array list of sales for each movie
         */
        return serializedDB.getSales();
    }

    public Integer getSalesFigure(String movieName){
        /**This method is defined to get the sales for a given movie
         * @param movieName  the movie name used to get the number of sales
         * @return: the number of sales for the specified movie
         */
        return serializedDB.getSalesFigure(movieName);
    }

    public void commitTicketDetails() {
        /**This method is defined to confirm the changes of the ticket when exiting the configure system settings panel
         */
        setEnums();
        serializedDB.setTicketPricing(getPublicHolidayDates(), getPublicHolidayCharges(), getWeekendCharges(), getBasePrice());
    }

    public void addMovies(Movie movie){
        /**This method is defined to add movies into the temporary database when the application is running
         * @param movie The movie created to be added into the database
         */
        serializedDB.addMovies(movie);
    }
}



