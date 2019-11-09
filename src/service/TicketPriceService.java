package service;

import model.AgeGroup;
import model.PublicHoliday;
import model.cinema.CinemaType;
import model.movie.MovieEnums;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TicketPriceService {

    private static ArrayList<PublicHoliday> publicHolidayDates = new ArrayList<PublicHoliday>();
    private static double publicHolidayCharges;
    private static double weekendCharges;
    private static double basePrice; //Universal Base Price

  
    public static void setMovieTypeCharges(int opt, double prices) {
        /**This method will set the extra charges for a specific movie type
         * @param opt                  The selected movie type which is given by the user
         * @param prices               The charges for the specific movie type
         */
        MovieEnums.MovieType.values()[opt - 1].setTicketPrice(prices);
    }

    public static void setCinemaTypeCharges(int opt, double prices) {
        /**This method will set the extra charges for a specific cinema type
         * @param opt                  The selected cinema type which is given by the user
         * @param prices               The charges for the specific cinema type
         */
        CinemaType.values()[opt - 1].setTicketPrice(prices);
    }

    public static void setAgeGroupCharges(int opt, double prices) {
        /**This method will set the discounts for a specific age group
         * @param opt                  The selected age group which is given by the user
         * @param prices               The discount for the specific age group
         */
        AgeGroup.values()[opt].setTicketPrice(prices);
    }

    public static void setPublicHolidayCharges(double charges) {
        /**This method will set the extra charges during public holiday
         * @param charges              The extra charges during public holiday
         */
        publicHolidayCharges = charges;
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
        weekendCharges = charges;
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
        basePrice = price;
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


    public static boolean addPublicHolidayDates(String name, LocalDate date) {
         /**This method will add the public holiday dates given by the user to the publicHolidayDates list that consist
         * of all the public holiday dates.
         * @param name                 The name for the public holiday
         * @param date                 The date in the ticket when the movie will show

         * @return true if succeed in adding the public holiday date
         * @return false if there is a duplicate date which results in failure of adding the public holiday
         *                             date
         */
        for (PublicHoliday publicHoliday : publicHolidayDates) {
            if (publicHoliday.getPublicHolidayDate().equals(date)) {
                return false;
            }
        }
        PublicHoliday publicHoliday = new PublicHoliday(name, date);
        publicHolidayDates.add(publicHoliday);
        return true;
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
}
