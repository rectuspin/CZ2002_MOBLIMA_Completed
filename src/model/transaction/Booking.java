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


public class Booking implements Model {
    private double price;
    private String transactionID;
    private Customer customer;
    private LocalDate dateOfBooking;
    private LocalTime timeOfBooking;
    private ShowTime showTime;
    private Cineplex cineplex;
    private Cinema cinema;
    private Movie movie;
    private Seat[] seats;
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public Booking(LocalDate dateOfBooking, LocalTime timeOfBooking, ShowTime showTime, Seat[] seats, Customer customer) {
        /** This constructor is for the booking
         * @param dateOfBooking               The date when the booking is made
         * @param timeOfBooking               The time when the booking is made
         * @param showTime                    The selected showtime by the user
         * @param seats                       An array of seats
         * @param customer                    The customer that made the booking
         */
        this.dateOfBooking = dateOfBooking;
        this.timeOfBooking = timeOfBooking;
        this.showTime = showTime;
        this.seats = seats;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hhmm");
        this.transactionID = "CUS" + this.dateOfBooking.format(dateFormat) + this.timeOfBooking.format(timeFormat);
        this.customer = customer;
        this.movie = this.showTime.getMovie();
        this.cinema = this.showTime.getCinema();
        this.cineplex = this.showTime.getCineplex();
    }

    public void makeBooking() {
        /**This method is used to add the tickets into the booking
         */
        tickets.add(new Ticket(movie, showTime.getMovieType(), cinema.getCinemaType()));
    }

    public void makeBooking(AgeGroup ageGroup) {
        /**This method is used to add the tickets based on the age of the citizen into the booking
         */
        Ticket ticket = new Ticket(movie, showTime.getMovieType(), cinema.getCinemaType());
        ticket.setAgeGroup(ageGroup);
        tickets.add(ticket);
    }

    public ArrayList<Ticket> getTickets() {
        /**This method will return a list of tickets that was selected in the booking
         * @return                            An array list of tickets
         */
        return tickets;
    }

    public double getPrice(LocalDate date) {
        /**This method will get the total price of the booking based on the number of tickets and the price of each
         * ticket
         * @param date                        The date when the booking is made
         * @return                            The total price of the booking
         */
        price = 0;
        for (Ticket ticket : tickets) {
            price += ticket.getTicketCharges(isHoliday(date), isWeekend(date));
        }
        return price;
    }
    public LocalDate getDateOfBooking() {
        /**This method will return the date of the booking
         * @return                            The date when the booking is made
         */
        return dateOfBooking;
    }

    public void setDateOfBooking(LocalDate dateOfBooking) {
        /**This method will set/ update the date of booking
         * @param dateOfBooking               The date when the booking is made
         */
        this.dateOfBooking = dateOfBooking;
    }

    public LocalTime getTimeOfBooking() {
        /**This method will return the date of booking
         * @return                            The time when the booking is made
         */
        return timeOfBooking;
    }

    public void setTimeOfBooking(LocalTime timeOfBooking) {
        /**This method will set the time of booking
         * @param timeOfBooking               The time when the booking is made
         */
        this.timeOfBooking = timeOfBooking;
    }

    public Cineplex getCineplex() {
        /**This method will return the cineplex of the specific booking
         * @return                            The cineplex where the movie is shown for the booking
         */
        return cineplex;
    }

    public void setCineplex(Cineplex cineplex) {
        /**This method will set the cineplex for the specific booking
         * @param cineplex                    The cineplex where the movie is showed
         */
        this.cineplex = cineplex;
    }

    public Cinema getCinema() {
        /**This method will get the cinema of the movie for that specific booking
         * @return                            The cinema where the movie is showed
         */
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        /**This method will set the cinema of the movie for that specific booking
         * @param cinema                      The cinema where the movie is showed
         */
        this.cinema = cinema;
    }

    public Movie getMovie() {
        /**This method will return the movie of the booking
         * @return movie                      The movie of the specific booking
         */
        return movie;
    }

    public void setMovie(Movie movie) {
        /**This method will set the movie for the booking
         * @param movie                       The movie selected by the user
         */
        this.movie = movie;
    }

    public Seat[] getSeats() {
        /**This method will return the seats for the booking
         * @return                            The seats of the cinema
         */
        return seats;
    }

    public void setSeats(Seat[] seats) {
        /**This method will set the seats for the booking
         * @param seats                       The seats of the cinema
         */
        this.seats = seats;
    }

    public String getTransactionID(){
        /**This method will return the transaction ID of the booking
         * @param transactionID               The transaction ID of the booking
         */
        return transactionID;}

}
