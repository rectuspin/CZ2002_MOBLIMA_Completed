package model.cinema;

import model.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Cineplex implements Model {
    private String name;
    private ArrayList<Cinema> cinemas;
    private HashMap<LocalDate, ArrayList<ShowTime>> showTimes;

    public Cineplex(String name) {
        /**This is constructor for Cineplex
         * @param name          This is the name of the cineplex
         */
        this.name = name;
        this.cinemas = new ArrayList<>();
        this.showTimes = new HashMap<>();
    }

    public String getName() {
        /**This method would return the name of the cineplex
         * @return              The name of the cineplex
         */
        return name;
    }

    public void setName(String name) {
        /**This method would set/ update the name of the cineplex
         * @param name          The name of the cineplex
         */
        this.name = name;
    }

    public ArrayList<Cinema> getCinemas() {
        /**This method would return the list of cinemas
         * @return              The array list consisting the list of cinemas
         */
        return cinemas;
    }

    public void setCinemas(ArrayList<Cinema> cinemas) {
        /**This method is defined to set the list of cinemas
         * @param cinemas       The array list consisting the list of cinemas
         */
        this.cinemas = cinemas;
    }

    public HashMap<LocalDate, ArrayList<ShowTime>> getShowTimes() {
        /**This method is defined to get and return the showtime that are set and configured by the admin
         * @return              The list of showtime for different movies
         */
        return showTimes;
    }

    public void setShowTimes(HashMap<LocalDate, ArrayList<ShowTime>> showTimes) {
        /**This method is defined to set the showtime
         * @param showTimes     A list of showtime that is configured and set by the admin
         */
        this.showTimes = showTimes;
    }
}

