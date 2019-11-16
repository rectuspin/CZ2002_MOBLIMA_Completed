package service;

import controller.DBController;
import controller.SerializedDB;
import model.AgeGroup;
import model.PublicHoliday;
import model.cinema.CinemaType;
import model.movie.MovieEnums;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TicketPriceService {

    private static ArrayList<PublicHoliday> publicHolidayDates = new ArrayList<PublicHoliday>();
    private static SerializedDB serializedDB = SerializedDB.getInstance();
    private static double publicHolidayCharges;
    private static double weekendCharges;
    private static double basePrice; //Universal Base Price

    public static void setAllPrices(){
        /**This method will set all the prices during startup of the application
         * @param price                 the base price of the ticket
         * @param weekendCharge         the extra charges during the weekends
         * @param publicHolidayCharge   the extra charges during public holidays
         * @param publicHolidayDate     the list of dates of each public holiday
         */
        publicHolidayDates = serializedDB.getPublicHolidayDates();
        publicHolidayCharges = serializedDB.getPublicHolidayCharges();
        weekendCharges = serializedDB.getWeekendCharges();
        basePrice = serializedDB.getBasePrice();
    }

    public static void setMovieTypeCharges(int opt, double prices) {
        /**This method will set the extra charges for a specific movie type
         * @param opt                  The selected movie type which is given by the user
         * @param prices               The charges for the specific movie type
         */
        if (prices < 0) {
            System.out.println("[System: Negative Input Detected!]");
        } else {
            MovieEnums.MovieType.values()[opt - 1].setTicketPrice(prices);
            System.out.println("[System: Movie Type Charges Set Successfully]");
        }

    }

    public static void setCinemaTypeCharges(int opt, double prices) {
        /**This method will set the extra charges for a specific cinema type
         * @param opt                  The selected cinema type which is given by the user
         * @param prices               The charges for the specific cinema type
         */
        if (prices < 0) {
            System.out.println("[System: Negative Input Detected!]");
        } else {
            CinemaType.values()[opt - 1].setTicketPrice(prices);
            System.out.println("[System: Cinema Type Charges Set Successfully]");
        }
    }

    public static void setAgeGroupCharges(int opt, double prices) {
        /**This method will set the discounts for a specific age group
         * @param opt                  The selected age group which is given by the user
         * @param prices               The discount for the specific age group
         */
        if (prices < 0) {
            System.out.println("[System: Negative Input Detected!]");
        } else {
            AgeGroup.values()[opt].setTicketPrice(prices);
            System.out.println("[System: Special Citizen Discounts Set Successfully]");
        }
    }

    public static void setPublicHolidayCharges(double charges) {
        /**This method will set the extra charges during public holiday
         * @param charges              The extra charges during public holiday
         */
        if (charges < 0) {
            System.out.println("[System: Negative Input Detected!]");
        } else {
            publicHolidayCharges = charges;
            System.out.println("[System: Public Holiday Charges Set Successfully]");
        }
    }

    public static double getPublicHolidayCharges() {
        /**This method will return the extra charges during public holidays
         * @return The extra charges the occurs during public holidays
         */
        return publicHolidayCharges;
    }

    public static void setPublicHolidayDates(ArrayList<PublicHoliday> publicHolidays) {
        /**This method will update the public holiday dates
         * @param publicHolidays       An array list containing dates of each public holiday
         */
        publicHolidayDates = publicHolidays;
    }

    public static ArrayList<PublicHoliday> getPublicHolidayDates() {
        /**This method will return an array list of public holiday dates
         * @return An array list containing dates of each public holiday
         */
        return publicHolidayDates;
    }


    public static void setWeekendCharges(double charges) {
        /**This method will set the extra charges during weekends
         * @param charges              The extra charges that occurs during weekends
         */
        if (charges < 0) {
            System.out.println("[System: Negative Input Detected!]");
        } else {
            weekendCharges = charges;
            System.out.println("[System: Weekend Charges Set Successfully]");
        }
    }

    public static double getWeekendCharges() {
        /**This method will return the extra charges during weekends
         * @return The extra charges that occurs on the weekends
         */
        return weekendCharges;
    }

    public static void setBasePrice(double price) {
        /**This method will set the base price of the ticket
         * @param price                The base price of the ticket
         */
        if(price < 0){
            System.out.println("[System: Negative Input Detected!]");
        }else{
            basePrice = price;
            System.out.println("[System: Base Price Set Successfully]");
        }
    }

    public static double getBasePrice() {
        /**This method will return the base price of the ticket
         * @return The base price of the ticket
         */
        return basePrice;
    }

    public static boolean isWeekend(LocalDate date){
        /**This method will determine if the provided day is a weekend and returns true if it is
         * @param date                 The date in the ticket when the movie will show
         * @return true if it is a weekend
         * @return false if it is not a weekend
         */
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E");
        return date.format(dateFormat).equals("Sun") || date.format(dateFormat).equals("Sat");
    }

    public static void addPublicHolidayDates(String name, LocalDate date) {
        /**This method will add the public holiday dates given by the user to the publicHolidayDates list that consist
         * of all the public holiday dates.
         * @param name                 The name for the public holiday
         * @param date                 The date in the ticket when the movie will show
         * @return if there is a duplicated date found in the database
         * @return if the date provided is before the present date
         */
        if (date.isBefore(LocalDate.now())) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("[System: Date not added]");
            System.out.println("[System: " + date.format(dateFormat) + " is before today's date, " + LocalDate.now().format(dateFormat) + "]");
            return;
        }
        for (PublicHoliday publicHoliday : publicHolidayDates) {
            if (publicHoliday.getPublicHolidayDate().equals(date)) {
                System.out.println("[System: Duplicated Date detected!]");
                return;
            }
        }
        PublicHoliday publicHoliday = new PublicHoliday(name, date);
        publicHolidayDates.add(publicHoliday);
        System.out.println("[System: Public Holiday has been added!]");
    }


    public static boolean removePublicHolidayDates(LocalDate date){
        /**This method will remove the public holiday dates given by the user from the publicHolidayDates list that
         * consist of all the public holiday dates.
         * @param date                 The date in the ticket when the movie will show
         * @return true if succeed in removing the public holiday date
         * @return false if there is no such date in the publicHolidayDates list
         */
        for (PublicHoliday publicHoliday : publicHolidayDates) {
            if (publicHoliday.getPublicHolidayDate().equals(date)) {
                publicHolidayDates.remove(publicHoliday);
                return true;
            }
        }
        return false;
    }

    public static boolean isHoliday(LocalDate date){
        /**This method will check if the date provided is a holiday.
         * @param date                 The date in the ticket when the movie will show
         * @return true if it is a public holiday
         * @return false if it is not a public holiday
         */
        for (PublicHoliday publicHoliday : publicHolidayDates) {
            if (date.equals(publicHoliday.getPublicHolidayDate())) {
                return true;
            }
        }
        return false;
    }

    public static void commit(){
        /**This method is defined to confirm the changes of the ticket when exiting the configure system settings panel
         */
        DBController dbController = DBController.getInstance();
        dbController.commitTicketDetails();
    }
}
