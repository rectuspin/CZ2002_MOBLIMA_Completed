package model.cinema;

import model.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cineplex class
 */
public class Cineplex implements Model {
    /**
     * name of Cineplex
     */
    private String name;
    /**
     * ArrayList of all Cinema objects in the Cineplex object
     */
    private ArrayList<Cinema> cinemas;
    /**
     * HashMap of ShowTimes identified by their dates
     */
    private HashMap<LocalDate, ArrayList<ShowTime>> showTimes;

    /**
     * This is constructor for Cineplex
     *
     * @param name This is the name of the cineplex
     */
    public Cineplex(String name) {

        this.name = name;
        this.cinemas = new ArrayList<>();
        this.showTimes = new HashMap<>();
    }

    /**
     * This method would return the name of the cineplex
     *
     * @return The name of the cineplex
     */
    public String getName() {

        return name;
    }

    /**
     * This method would set/ update the name of the cineplex
     *
     * @param name The name of the cineplex
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * This method would return the list of cinemas
     *
     * @return The array list consisting the list of cinemas
     */
    public ArrayList<Cinema> getCinemas() {

        return cinemas;
    }

    /**
     * This method is defined to set the list of cinemas
     *
     * @param cinemas The array list consisting the list of cinemas
     */
    public void setCinemas(ArrayList<Cinema> cinemas) {

        this.cinemas = cinemas;
    }

    /**This method is defined to get and return the showtime that are set and configured by the admin
     * @return The HashMap of showtime for different movies
     */
    public HashMap<LocalDate, ArrayList<ShowTime>> getShowTimes() {

        return showTimes;
    }

    /**This method is defined to set the showtime
     * @param showTimes     A HashMap of showtime that is configured and set by the admin
     */
    public void setShowTimes(HashMap<LocalDate, ArrayList<ShowTime>> showTimes) {

        this.showTimes = showTimes;
    }
}

