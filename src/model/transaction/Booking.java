package model.transaction;

import model.AgeGroup;
import model.Model;
import model.account.Customer;
import model.cinema.Cinema;
import model.cinema.Cineplex;
import model.cinema.Seat;
import model.cinema.ShowTime;
import model.movie.Movie;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static service.TicketPriceService.isHoliday;
import static service.TicketPriceService.isWeekend;

/**
 * Class containing information on Bookings
 */
public class Booking implements Model {
    /**
     * price of the booking
     */
    private double price;
    /**
     * transaction id
     */
    private String transactionID;
    /**
     * Customer who made the booking
     */
    private Customer customer;
    /**
     * Date the booking is made
     */
    private LocalDate dateOfBooking;
    /**
     * Time the booking is made
     */
    private LocalTime timeOfBooking;
    /**
     * The show time the booking was made under
     */
    private ShowTime showTime;
    /**
     * Cineplex containing the cinema showing the movie
     */
    private Cineplex cineplex;
    /**
     * Cinema showing the movie
     */
    private Cinema cinema;
    /**
     * Movie being shown
     */
    private Movie movie;
    /**
     * Seats being booked
     */
    private Seat[] seats;
    /**
     * Tickets purchased
     */
    private ArrayList<Ticket> tickets = new ArrayList<>();

    /**
     * This constructor is for the booking
     *
     * @param dateOfBooking The date when the booking is made
     * @param timeOfBooking The time when the booking is made
     * @param showTime      The selected showtime by the user
     * @param seats         An array of seats
     * @param customer      The customer that made the booking
     */
    public Booking(LocalDate dateOfBooking, LocalTime timeOfBooking, ShowTime showTime, Seat[] seats, Customer customer) {

        this.dateOfBooking = dateOfBooking;
        this.timeOfBooking = timeOfBooking;
        this.showTime = showTime;
        this.seats = seats;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
        this.transactionID = "CUS" + this.dateOfBooking.format(dateFormat) + this.timeOfBooking.format(timeFormat);
        this.customer = customer;
        this.movie = this.showTime.getMovie();
        this.cinema = this.showTime.getCinema();
        this.cineplex = this.showTime.getCineplex();
    }

    /**This method is used to add the tickets into the booking
     */
    public void makeBooking() {

        tickets.add(new Ticket(movie, showTime.getMovieType(), cinema.getCinemaType()));
    }

    /**This method is used to add the tickets based on the age of the citizen into the booking
     */
    public void makeBooking(AgeGroup ageGroup) {

        Ticket ticket = new Ticket(movie, showTime.getMovieType(), cinema.getCinemaType());
        ticket.setAgeGroup(ageGroup);
        tickets.add(ticket);
    }

    /**This method will return a list of tickets that was selected in the booking
     * @return An array list of tickets
     */
    public ArrayList<Ticket> getTickets() {

        return tickets;
    }

    /**This method will get the total price of the booking based on the number of tickets and the price of each
     * ticket
     * @param date                        The date when the booking is made
     * @return The total price of the booking
     */
    public double getPrice(LocalDate date) {

        price = 0;
        for (Ticket ticket : tickets) {
            price += ticket.getTicketCharges(isHoliday(date), isWeekend(date));
        }
        return price;
    }

    /**This method will return the date of the booking
     * @return The date when the booking is made
     */
    public LocalDate getDateOfBooking() {
        return dateOfBooking;
    }


    /**This method will set/ update the date of booking
     * @param dateOfBooking               The date when the booking is made
     */
    public void setDateOfBooking(LocalDate dateOfBooking) {

        this.dateOfBooking = dateOfBooking;
    }

    /**This method will return the date of booking
     * @return The time when the booking is made
     */
    public LocalTime getTimeOfBooking() {

        return timeOfBooking;
    }

    /**This method will set the time of booking
     * @param timeOfBooking               The time when the booking is made
     */
    public void setTimeOfBooking(LocalTime timeOfBooking) {

        this.timeOfBooking = timeOfBooking;
    }

    /**This method will return the cineplex of the specific booking
     * @return The cineplex where the movie is shown for the booking
     */
    public Cineplex getCineplex() {

        return cineplex;
    }

    /**This method will set the cineplex for the specific booking
     * @param cineplex                    The cineplex where the movie is showed
     */
    public void setCineplex(Cineplex cineplex) {

        this.cineplex = cineplex;
    }

    /**This method will get the cinema of the movie for that specific booking
     * @return The cinema where the movie is showed
     */
    public Cinema getCinema() {

        return cinema;
    }

    /**This method will set the cinema of the movie for that specific booking
     * @param cinema                      The cinema where the movie is showed
     */
    public void setCinema(Cinema cinema) {

        this.cinema = cinema;
    }

    /**This method will return the movie of the booking
     * @return movie                      The movie of the specific booking
     */
    public Movie getMovie() {

        return movie;
    }

    /**This method will set the movie for the booking
     * @param movie                       The movie selected by the user
     */
    public void setMovie(Movie movie) {

        this.movie = movie;
    }

    /**This method will return the seats for the booking
     * @return The seats of the cinema
     */
    public Seat[] getSeats() {

        return seats;
    }

    /**This method will set the seats for the booking
     * @param seats                       The seats of the cinema
     */
    public void setSeats(Seat[] seats) {

        this.seats = seats;
    }

    /**This method will return the transaction ID of the booking
     * @return transactionID               The transaction ID of the booking
     */
    public String getTransactionID() {

        return transactionID;
    }

    /**
     * Method to return the Show Time object of the booking
     * @return showTime
     */
    public ShowTime getShowTime() {
        return showTime;
    }
    }
