package service;

import controller.DBController;
import model.cinema.Cinema;
import model.cinema.Cineplex;
import model.cinema.ShowTime;
import model.movie.Movie;
import model.movie.MovieEnums;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

/**
 * Service class to provide services for admin
 */
public class AdminCineplexService {

    /**
     * static variable for DBController to access DB
     */
    private DBController dbController = DBController.getInstance();

    /**
     * Method to add a cineplex into the database
     *
     * @param cineplex
     */
    public void addCineplex(Cineplex cineplex) {

        dbController.addCineplex(cineplex);
    }

    /**
     * Method to add cineplex by name
     * @param name
     */
    public void removeCineplex(String name) {
        dbController.removeCineplex(name);
    }

    /**
     * Method to add cinema to a cineplex
     * @param cineplex
     * @param cinema
     */
    public void addCinema(Cineplex cineplex, Cinema cinema) {
        dbController.addCinema(cineplex, cinema);
    }

    /**
     * Method to remove cinema from a cineplex
     * @param cineplex
     * @param cinema
     */
    public void removeCinema(Cineplex cineplex, Cinema cinema) {
        dbController.removeCinema(cineplex, cinema);
    }

    /**
     * Method to add show time
     * @param showTime
     */
    public void addShowTime(ShowTime showTime) {
        dbController.addShowTimes(showTime);
    }

    /**
     * overloaded method to add show time
     * @param movie
     * @param dateOfMovie
     * @param timeOfMovie
     * @param cinema
     * @param cineplex
     */
    public void addShowTime(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, Cinema cinema,
                            Cineplex cineplex) {
        ShowTime newShowTime = new ShowTime(movie, dateOfMovie, timeOfMovie, cinema.copyCinemaLayout(),
                cineplex, cinema);
        dbController.addShowTimes(cineplex, dateOfMovie, newShowTime);

    }

    /**
     * overloaded method to add show time
     * @param movie
     * @param dateOfMovie
     * @param timeOfMovie
     * @param cinema
     * @param cineplex
     * @param language
     * @param subtitle
     * @param movieType
     */
    public void addShowTime(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, Cinema cinema,
                            Cineplex cineplex, MovieEnums.Language language, MovieEnums.Subtitle subtitle, MovieEnums.MovieType movieType) {

        ShowTime newShowTime = new ShowTime(movie, dateOfMovie, timeOfMovie, cinema.copyCinemaLayout(),
                cineplex, cinema, language, subtitle, movieType);

        dbController.addShowTimes(cineplex, dateOfMovie, newShowTime);
    }

    /**
     * Method to remove Show Time
     *
     * @param showTime
     */
    public void removeShowTime(ShowTime showTime) {
        removeShowTime(showTime.getMovie(), showTime.getDateOfMovie(), showTime.getTimeOfMovie(), showTime.getCinema(),
                showTime.getCineplex());
    }

    /**
     * Overloaded method to remove Show Time
     * @param movie
     * @param dateOfMovie
     * @param timeOfMovie
     * @param cinema
     * @param cineplex
     */
    public void removeShowTime(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, Cinema cinema,
                               Cineplex cineplex) {
        dbController.removeShowTimes(movie, dateOfMovie, timeOfMovie,cinema, cineplex);
    }

    /**
     * Method to update show time
     * @param oldShowTime
     * @param newShowTime
     */
    public void updateShowTime(ShowTime oldShowTime, ShowTime newShowTime) {
        removeShowTime(oldShowTime);
        addShowTime(newShowTime);
    }

    /**
     * method to get the hashmap of cineplexes from the database
     * @return HashMap
     */
    public HashMap getCineplexes() {
        return dbController.getCineplexes();
    }


}
