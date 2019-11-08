package model.cinema;

import model.Model;
import model.movie.Movie;
import model.movie.MovieEnums;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class ShowTime implements Model {
//    private UUID uuid;

    private Movie movie;

    private LocalDate dateOfMovie;

    private LocalTime timeOfMovie;

    private HashMap<Character, Seat[]> seatLayout;

    private Cinema cinema;

    private Cineplex cineplex;

    private MovieEnums.Language language;

    private MovieEnums.Subtitle subtitle;

    private MovieEnums.MovieType movieType;

    public ShowTime(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, HashMap<Character, Seat[]> seatLayout,
                    Cineplex cineplex, Cinema cinema, MovieEnums.Language language, MovieEnums.Subtitle subtitle, MovieEnums.MovieType movieType) {
        /**This is a constructor for showtime
         * @param movie                 The movie of the showtime
         * @param dateOfMovie           The date of when the movie is showed for that showtime
         * @param timeOfMovie           The time of when the movie is showed for that showtime
         * @param seatLayout            The seat layout of the cinema for the showtime
         * @param cineplex              The cineplex of the showtime
         * @param cinema                The cinema of the showtime
         * @param language              The language of the movie for that showtime
         * @param subtitle              The subtitle of the movie for that showtime
         * @param movieType             The type of the movie for that showtime
         */
        this.movie = movie;
        this.dateOfMovie = dateOfMovie;
        this.timeOfMovie = timeOfMovie;
        this.seatLayout = seatLayout;
        this.cinema = cinema;
        this.cineplex = cineplex;
        this.language = language;
        this.subtitle = subtitle;
        this.movieType = movieType;
    }

    public ShowTime(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, HashMap<Character, Seat[]> seatLayout,
                    Cineplex cineplex, Cinema cinema) {
        /**This is a constructor for showtime
         * @param movie                 The movie of the showtime
         * @param dateOfMovie           The date of when the movie is showed for that showtime
         * @param timeOfMovie           The time of when the movie is showed for that showtime
         * @param seatLayout            The seat layout of the cinema for the showtime
         * @param cineplex              The cineplex of the showtime
         * @param cinema                The cinema of the showtime
         */
        this.movie = movie;
        this.dateOfMovie = dateOfMovie;
        this.timeOfMovie = timeOfMovie;
        this.seatLayout = seatLayout;
        this.cinema = cinema;
        this.cineplex = cineplex;
        this.language = MovieEnums.Language.ENGLISH;
        this.subtitle = MovieEnums.Subtitle.None;
        this.movieType = MovieEnums.MovieType.DIGITAL;
    }

    public Movie getMovie() {
        /**This method will get and return the movie of the showtime
         * @return          the movie of the showtime
         */
        return movie;
    }

    public void setMovie(Movie movie) {
        /**This method will set the movie to the showtime
         * @param movie          the movie of the showtime
         */
        this.movie = movie;
    }

    public LocalDate getDateOfMovie() {
        /**This method will get and return the date of the movie of the showtime
         * @return          the date of the movie for the showtime
         */
        return dateOfMovie;
    }

    public void setDateOfMovie(LocalDate dateOfMovie) {
        /**This method will set the date of the movie of the showtime
         * @param dateOfMovie          the date of the movie for the showtime
         */
        this.dateOfMovie = dateOfMovie;
    }

    public LocalTime getTimeOfMovie() {
        /**This method will get and return the time of the movie of the showtime
         * @return          the time of the movie for the showtime
         */
        return timeOfMovie;
    }

    public void setTimeOfMovie(LocalTime timeOfMovie) {
        /**This method will set the time of the movie of the showtime
         * @param timeOfMovie          the time of the movie for the showtime
         */
        this.timeOfMovie = timeOfMovie;
    }

    public HashMap<Character, Seat[]> getSeatLayout() {
        /**This method will get and return the seat layout of the cinema for the showtime
         * @return                      the seat layout for the showtime
         */
        return seatLayout;
    }

    public void setSeatLayout(HashMap<Character, Seat[]> seatLayout) {
        /**This method will set the seat layout of the cinema for the showtime
         * @param seatLayout                      the seat layout for the showtime
         */
        this.seatLayout = seatLayout;
    }

    public Cinema getCinema() {
        /**This method will get the cinema of the showtime
         * @return                  the cinema of the showtime
         */
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        /**This method will set the cinema of the showtime
         * @param cinema                  the cinema of the showtime
         */
        this.cinema = cinema;
    }

    public Cineplex getCineplex() {
        /**This method will get the cineplex of the showtime
         * @return                  the cineplex of the showtime
         */
        return cineplex;
    }

    public void setCineplex(Cineplex cineplex) {
        /**This method will set the cineplex of the showtime
         * @param cineplex                  the cineplex of the showtime
         */
        this.cineplex = cineplex;
    }

    public MovieEnums.Language getLanguage() {
        /**This method will get and return the language of the movie for the showtime
         * @return                          the language of the movie that is shown in the specific showtime
         */
        return language;
    }

    public void setLanguage(MovieEnums.Language language) {
        /**This method will set the language of the movie for the showtime
         * @param language                  the language of the movie that is shown in the specific showtime
         */
        this.language = language;
    }

    public MovieEnums.Subtitle getSubtitle() {
        /**This method will get and return the subtitle of the movie for the showtime
         * @return                          the subtitle of the movie that is shown in the specific showtime
         */
        return subtitle;
    }

    public void setSubtitle(MovieEnums.Subtitle subtitle) {
        /**This method will set the subtitle of the movie for the showtime
         * @param subtitle                  the subtitle of the movie that is shown in the specific showtime
         */
        this.subtitle = subtitle;
    }

    public MovieEnums.MovieType getMovieType() {
        /**This method will get the type of movie for the showtime
         * @return                          the type of movie for the showtime
         */
        return movieType;
    }

    public void setMovieType(MovieEnums.MovieType movieType) {
        /**This method will set the type of movie for the showtime
         * @param movieType                 the type of movie for the showtime
         */
        this.movieType = movieType;
    }

    @Override
    public String toString() {
        /**This method will return the format of how the showtime will be displayed
         * @return                          the format of how the details of the showtime will be displayed
         */
        return "ShowTime{" +
                "movie=" + movie +
                ", dateOfMovie=" + dateOfMovie +
                ", timeOfMovie=" + timeOfMovie +
                ", seatLayout=" + seatLayout +
                ", cinema=" + cinema +
                ", cineplex=" + cineplex +
                ", language=" + language +
                ", subtitle=" + subtitle +
                ", movieType=" + movieType +
                '}';
    }
}
