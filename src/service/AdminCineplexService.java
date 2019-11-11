package service;

import controller.DBController;
import model.account.Admin;
import model.cinema.Cinema;
import model.cinema.Cineplex;
import model.cinema.ShowTime;
import model.movie.Movie;
import model.movie.MovieEnums;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class AdminCineplexService {
    private Admin admin;
    private DBController dbController = DBController.getInstance();


    public AdminCineplexService(Admin admin) {
        this.admin = admin;
    }


    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void addCineplex(Cineplex cineplex) {
//        dbController.getCineplexes().put(cineplex.getName(), cineplex);
        dbController.addCineplex(cineplex);
    }

    public void removeCineplex(String name) {
        dbController.removeCineplex(name);
    }

    public void addCinema(Cineplex cineplex, Cinema cinema) {
        dbController.addCinema(cineplex, cinema);
    }

    public void removeCinema(Cineplex cineplex, Cinema cinema) {
        dbController.removeCinema(cineplex, cinema);
    }

    public void addShowTime(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, Cinema cinema,
                            Cineplex cineplex) {
        ShowTime newShowTime = new ShowTime(movie, dateOfMovie, timeOfMovie, cinema.copyCinemaLayout(),
                cineplex, cinema);
        dbController.addShowTimes(cineplex, dateOfMovie, newShowTime);

    }

    public void addShowTime(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, Cinema cinema,
                            Cineplex cineplex, MovieEnums.Language language, MovieEnums.Subtitle subtitle, MovieEnums.MovieType movieType) {

        ShowTime newShowTime = new ShowTime(movie, dateOfMovie, timeOfMovie, cinema.copyCinemaLayout(),
                cineplex, cinema, language, subtitle, movieType);

        dbController.addShowTimes(cineplex, dateOfMovie, newShowTime);
    }

    public void removeShowTime(Movie movie, LocalDate dateOfMovie, LocalTime timeOfMovie, Cinema cinema,
                               Cineplex cineplex) {
        dbController.removeShowTimes(movie, dateOfMovie, timeOfMovie,cinema, cineplex);
    }

    public HashMap getCineplexes() {
        return dbController.getCineplexes();
    }


}
