package controller;

import model.AgeGroup;
import model.cinema.Cinema;
import model.cinema.CinemaType;
import model.cinema.Cineplex;
import model.cinema.ShowTime;
import model.movie.Movie;
import model.movie.MovieEnums;
import model.transaction.Booking;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static service.TicketPriceService.*;
import static service.TicketPriceService.setWeekendCharges;

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
    public void updateDB(List list, String DBName) throws IOException {
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
            SerializeDB.writeSerializedObject(dir + DBName + ".dat", list);
        }
    }

    //Gets the data from the DB
    public List readDB(String DBName) throws IOException {
        /**This method is defined to read data from .dat file
         * @param  DBName       The name of the database that wants to be read
         * @return              A list of stored items in the .dat file
         * @throws IOException  If the file is not found
         */
        String dir = "src/database/";
        List list = new ArrayList();
        File myFile = new File(dir + DBName + ".dat");
        if(!myFile.exists()) {
            myFile.createNewFile();
        }else {
            list = SerializeDB.readSerializedObject(dir + DBName + ".dat");
        }
        return list;
    }


    public HashMap<String, Cineplex> getCineplexes() {
        /**This method is defined to get the array of stored cineplex
         * @return     An array of cineplex from the database
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
         * @param dataOfMovie   The date of the showtime
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
         * @return:    An instance of DBController
         */
        if (dbController == null)
            dbController = new DBController();

        return dbController;
    }

    public void loadTicketPriceInfoDatabase() throws ParseException, IOException {
        /**This method is defined to load all the data from the database into the system
         * @throws IOException  If the file is not found
         */

        DBController dbController = DBController.getInstance();

        //Retrieve all the data from database to list
        List generalTypePriceDB = dbController.readDB("GeneralTypePriceDB");
        List movieTypePriceDB = dbController.readDB("MovieTypePriceDB");
        List cinemaTypePriceDB = dbController.readDB("CinemaTypePriceDB");
        List citizenTypePriceDB = dbController.readDB("CitizenTypePriceDB");

        //Convert to SerializedDB data type
        SerializedDB defaultPrices = (SerializedDB) generalTypePriceDB.get(0);

        //Loads all the prices from the data into the system
        setBasePrice(defaultPrices.getBasePrice());
        setPublicHolidayCharges(defaultPrices.getPublicHolidayCharges());
        setPublicHolidayDates(defaultPrices.getPublicHolidayDates());
        setWeekendCharges(defaultPrices.getWeekendCharges());

        //Declaration of variable
        int i;

        //Loads all the movie type prices into the system
        i = 0;
        for (MovieEnums.MovieType m : MovieEnums.MovieType.values()) {
            m.setTicketPrice((double) movieTypePriceDB.get(i));
            i++;
        }

        //Loads all the cinema type prices into the system
        i = 0;
        for (CinemaType c : CinemaType.values()) {
            c.setTicketPrice((double) cinemaTypePriceDB.get(i));
            i++;
        }

        //Loads all the discount prices for different citizen of different age group into the system
        i = 0;
        for (AgeGroup group : AgeGroup.values()) {
            group.setTicketPrice((double) citizenTypePriceDB.get(i));
            i++;
        }
    }

    public void saveTicketPriceInfoDatabase() throws IOException {
        /**This method is defined to save all the data from the database into the system
         * @throws IOException  If the file is not found
         */
        DBController dbController = DBController.getInstance();

        //Saves all the base/holiday/weekend pricing and holiday dates into the database
        SerializedDB ticketPrices = new SerializedDB();
        ticketPrices.setTicketPricing(getPublicHolidayDates(), getPublicHolidayCharges(), getWeekendCharges(), getBasePrice());
        List generalTicketPriceDB = new ArrayList();
        generalTicketPriceDB.add(ticketPrices);

        //Saves all the different movie type pricing into the database
        List movieTypeDB = new ArrayList();
        for (MovieEnums.MovieType m : MovieEnums.MovieType.values()){
            movieTypeDB.add(m.getTicketPrice());
        }

        //Saves all the different cinema type pricing into the database
        List cinemaTypeDB = new ArrayList();
        for (CinemaType c : CinemaType.values()){
            cinemaTypeDB.add(c.getTicketPrice());
        }

        //Saves all the different cinema type pricing into the database
        List ageGroupDB = new ArrayList();
        for (AgeGroup group : AgeGroup.values()){
            ageGroupDB.add(group.getTicketPrice());
        }

        //Save all the current changes into each database
        dbController.updateDB(generalTicketPriceDB, "GeneralTypePriceDB");
        dbController.updateDB(movieTypeDB, "MovieTypePriceDB");
        dbController.updateDB(cinemaTypeDB, "CinemaTypePriceDB");
        dbController.updateDB(ageGroupDB, "CitizenTypePriceDB");
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
         * @return      An array list of sales for each movie
         */
        return serializedDB.getSales();
    }

    public Integer getSalesFigure(String movieName){
        /**This method is defined to get the sales for a given movie
         * @param movieName  the movie name used to get the number of sales
         * @return:           the number of sales for the specified movie
         */
        return serializedDB.getSalesFigure(movieName);
    }
}



