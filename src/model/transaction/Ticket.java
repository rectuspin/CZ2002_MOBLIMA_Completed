package model.transaction;

import model.AgeGroup;
import model.Model;
import model.cinema.CinemaType;
import model.movie.Movie;
import model.movie.MovieEnums;
import service.TicketPriceService;

import static service.TicketPriceService.getBasePrice;

/**This is the ticket class where it would consist the information of the tickets such as
 * - movie
 * - movie type
 * - cinema type
 * - age group
 * - base price
 */

public class Ticket implements Model {
    private Movie movie;
    private MovieEnums.MovieType movieType;
    private CinemaType cinemaType;
    private AgeGroup ageGroup;
    private double basePrice;

    /**
     * This is the constructor for ticket
     *
     * @param movie      The movie selected based on the showtime for the ticket
     * @param movieType  The type of movie selected based on the showtime for the ticket
     * @param cinemaType The cinema type selected based on the showtime for the ticket
     */
    public Ticket(Movie movie, MovieEnums.MovieType movieType, CinemaType cinemaType) {

        this.movie = movie;
        this.movieType = movieType;
        this.cinemaType = cinemaType;
        this.basePrice = getBasePrice();
        this.ageGroup = AgeGroup.STANDARD;
    }

    /**This method will get the ticket charges based on certain criteria such as:
     * 1. The cinema type
     * 2. The movie type
     * 3. The age group (STUDENT, SENIOR CITIZEN, CHILD)
     * 4. Public Holiday
     * 5. Weekends
     * @param isHoliday             if that selected date is a holiday?
     * @param isWeekend             if that selected date is a weekend?
     * @return the final price of the ticket after the calculation depending on the given criteria
     */
    public double getTicketCharges(boolean isHoliday, boolean isWeekend) {

        double ticketPrice = basePrice;
        ticketPrice += cinemaType.getTicketPrice();
        ticketPrice += movieType.getTicketPrice();
        ticketPrice -= ageGroup.getTicketPrice();
        if (isHoliday) {
            ticketPrice += TicketPriceService.getPublicHolidayCharges();
        } else if (isWeekend) {
            ticketPrice += TicketPriceService.getWeekendCharges();
        }
        return ticketPrice;
    }

    /**This method will set which category is the customer based on their age which may provide certain discounts to
     * them if they fall into certain categories such as a student, senior citizen or a child.
     * @param ageGroup              The category of the customer (STUDENT_PRICE, SENIOR_CITIZEN, CHILD)
     */
    public void setAgeGroup(AgeGroup ageGroup){

        this.ageGroup = ageGroup;
    }

    /**This method will return the specific movie of the ticket
     * @return the specific movie of the ticket
     */
    public Movie getMovie() {

        return movie;
    }

    /**This method will return the type of movie for the ticket
     * @return the type of movie for the ticket
     */
    public MovieEnums.MovieType getMovieType() {

        return movieType;
    }

    /**This method will return the type of cinema for the ticket
     * @return the type of cinema for the ticket
     */
    public CinemaType getCinemaType() {

        return cinemaType;
    }
}
