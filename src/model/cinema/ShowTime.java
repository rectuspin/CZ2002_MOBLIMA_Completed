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


    //
        public ShowTime(){}
    //Remove on final (Just for testing purposes!)
    public ShowTime(Movie movie, Cinema cinema, Cineplex cineplex){
        this.movie = movie;
        this.movieType = MovieEnums.MovieType.DIGITAL;
        this.cinema = cinema;
        this.cineplex = cineplex;
    }// Just for testing will be removed!

    public ShowTime(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, HashMap<Character, Seat[]> seatLayout,
                    Cineplex cineplex, Cinema cinema, MovieEnums.Language language, MovieEnums.Subtitle subtitle, MovieEnums.MovieType movieType) {
        this.movie = movie;
        this.dateOfMovie = dateOfMovie;
        this.timeOfMovie = timeOfMovie;
        this.seatLayout = seatLayout;
        this.cinema = cinema;
        this.language = language;
        this.subtitle = subtitle;
        this.movieType = movieType;
    }

    public ShowTime(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, HashMap<Character, Seat[]> seatLayout,
                    Cineplex cineplex, Cinema cinema) {
        this.movie = movie;
        this.dateOfMovie = dateOfMovie;
        this.timeOfMovie = timeOfMovie;
        this.seatLayout = seatLayout;
        this.cinema = cinema;
        this.language = MovieEnums.Language.ENGLISH;
        this.subtitle = MovieEnums.Subtitle.None;
        this.movieType = MovieEnums.MovieType.DIGITAL;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDate getDateOfMovie() {
        return dateOfMovie;
    }

    public void setDateOfMovie(LocalDate dateOfMovie) {
        this.dateOfMovie = dateOfMovie;
    }

    public LocalTime getTimeOfMovie() {
        return timeOfMovie;
    }

    public void setTimeOfMovie(LocalTime timeOfMovie) {
        this.timeOfMovie = timeOfMovie;
    }

    public HashMap<Character, Seat[]> getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(HashMap<Character, Seat[]> seatLayout) {
        this.seatLayout = seatLayout;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Cineplex getCineplex() {
        return cineplex;
    }

    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }

    public MovieEnums.Language getLanguage() {
        return language;
    }

    public void setLanguage(MovieEnums.Language language) {
        this.language = language;
    }

    public MovieEnums.Subtitle getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(MovieEnums.Subtitle subtitle) {
        this.subtitle = subtitle;
    }

    public MovieEnums.MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieEnums.MovieType movieType) {
        this.movieType = movieType;
    }

    @Override
    public String toString() {
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
