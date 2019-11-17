package model.cinema;

import model.Model;
import model.movie.Movie;
import model.movie.MovieEnums;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

/**
 * ShowTime class
 */
public class ShowTime implements Model {

    /**
     * Movie screened
     */
    private Movie movie;

    /**
     * Date of movie screened
     */
    private LocalDate dateOfMovie;

    /**
     * Time of movie screened
     */
    private LocalTime timeOfMovie;

    /**
     * A copy of the seat layout of the cinema screening the movie
     */
    private HashMap<Character, Seat[]> seatLayout;

    /**
     * Cinema the movie is screened in
     */
    private Cinema cinema;

    /**
     * The cineplex that the cinema belongs to
     */
    private Cineplex cineplex;

    /**
     * Language the movie is screened in
     */
    private MovieEnums.Language language;

    /**
     * Subtitle the movie is screened in
     */
    private MovieEnums.Subtitle subtitle;

    /**
     * DIGITAL, 3D, IMAX
     */
    private MovieEnums.MovieType movieType;

    /**
     * This is a constructor for showtime
     *
     * @param movie       The movie of the showtime
     * @param dateOfMovie The date of when the movie is showed for that showtime
     * @param timeOfMovie The time of when the movie is showed for that showtime
     * @param seatLayout  The seat layout of the cinema for the showtime
     * @param cineplex    The cineplex of the showtime
     * @param cinema      The cinema of the showtime
     * @param language    The language of the movie for that showtime
     * @param subtitle    The subtitle of the movie for that showtime
     * @param movieType   The type of the movie for that showtime
     */
    public ShowTime(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, HashMap<Character, Seat[]> seatLayout,
                    Cineplex cineplex, Cinema cinema, MovieEnums.Language language, MovieEnums.Subtitle subtitle, MovieEnums.MovieType movieType) {

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

    /**This is a constructor for showtime
     * @param movie                     The movie of the showtime
     * @param dateOfMovie               The date of when the movie is showed for that showtime
     * @param timeOfMovie               The time of when the movie is showed for that showtime
     * @param seatLayout                The seat layout of the cinema for the showtime
     * @param cineplex                  The cineplex of the showtime
     * @param cinema                    The cinema of the showtime
     */
    public ShowTime(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, HashMap<Character, Seat[]> seatLayout,
                    Cineplex cineplex, Cinema cinema) {

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

    /**This method will get and return the movie of the showtime
     * @return the movie of the showtime
     */
    public Movie getMovie() {

        return movie;
    }

    /**This method will set the movie to the showtime
     * @param movie                     the movie of the showtime
     */
    public void setMovie(Movie movie) {

        this.movie = movie;
    }

    /**This method will get and return the date of the movie of the showtime
     * @return the date of the movie for the showtime
     */
    public LocalDate getDateOfMovie() {

        return dateOfMovie;
    }

    /**This method will set the date of the movie of the showtime
     * @param dateOfMovie               the date of the movie for the showtime
     */
    public void setDateOfMovie(LocalDate dateOfMovie) {

        this.dateOfMovie = dateOfMovie;
    }

    /**This method will get and return the time of the movie of the showtime
     * @return the time of the movie for the showtime
     */
    public LocalTime getTimeOfMovie() {

        return timeOfMovie;
    }

    /**This method will set the time of the movie of the showtime
     * @param timeOfMovie               the time of the movie for the showtime
     */
    public void setTimeOfMovie(LocalTime timeOfMovie) {

        this.timeOfMovie = timeOfMovie;
    }

    /**This method will get and return the seat layout of the cinema for the showtime
     * @return the seat layout for the showtime
     */
    public HashMap<Character, Seat[]> getSeatLayout() {

        return seatLayout;
    }

    /**This method will set the seat layout of the cinema for the showtime
     * @param seatLayout                the seat layout for the showtime
     */
    public void setSeatLayout(HashMap<Character, Seat[]> seatLayout) {

        this.seatLayout = seatLayout;
    }

    /**This method will get the cinema of the showtime
     * @return the cinema of the showtime
     */
    public Cinema getCinema() {

        return cinema;
    }

    /**This method will set the cinema of the showtime
     * @param cinema                    the cinema of the showtime
     */
    public void setCinema(Cinema cinema) {

        this.cinema = cinema;
    }

    /**This method will get the cineplex of the showtime
     * @return the cineplex of the showtime
     */
    public Cineplex getCineplex() {

        return cineplex;
    }

    /**This method will set the cineplex of the showtime
     * @param cineplex                  the cineplex of the showtime
     */
    public void setCineplex(Cineplex cineplex) {

        this.cineplex = cineplex;
    }

    /**This method will get and return the language of the movie for the showtime
     * @return the language of the movie that is shown in the specific showtime
     */
    public MovieEnums.Language getLanguage() {

        return language;
    }

    /**This method will set the language of the movie for the showtime
     * @param language                  the language of the movie that is shown in the specific showtime
     */
    public void setLanguage(MovieEnums.Language language) {

        this.language = language;
    }

    /**This method will get and return the subtitle of the movie for the showtime
     * @return the subtitle of the movie that is shown in the specific showtime
     */
    public MovieEnums.Subtitle getSubtitle() {

        return subtitle;
    }

    /**This method will set the subtitle of the movie for the showtime
     * @param subtitle                  the subtitle of the movie that is shown in the specific showtime
     */
    public void setSubtitle(MovieEnums.Subtitle subtitle) {

        this.subtitle = subtitle;
    }

    /**This method will get the type of movie for the showtime
     * @return the type of movie for the showtime
     */
    public MovieEnums.MovieType getMovieType() {

        return movieType;
    }

    /**This method will set the type of movie for the showtime
     * @param movieType                 the type of movie for the showtime
     */
    public void setMovieType(MovieEnums.MovieType movieType) {

        this.movieType = movieType;
    }

    /**This method will return the format of how the showtime will be displayed
     * @return the format of how the details of the showtime will be displayed
     */
    @Override
    public String toString() {

        return "ShowTime: \n" +
                "Movie Title: " + movie.getTitle() +
                "\n Date Of Movie: " + dateOfMovie +
                "\n Time Of Movie: " + timeOfMovie +
                "\n Cinema: " + cinema.getName() +
                "\n Cineplex: " + cineplex.getName() +
                "\n Language: " + language +
                "\n Subtitle: " + subtitle +
                "\n Movie Type: " + movieType + "\n\n";
    }
}
