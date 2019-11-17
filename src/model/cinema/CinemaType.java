package model.cinema;

/**
 * enum that contains the different types of cinema
 */
public enum CinemaType {
    STANDARD("Standard", 0),
    PLATINUM_MOVIE_SUITES("Suites", 0),
    IMAX("IMAX", 0);

    private String name;
    private double prices;

    CinemaType(String name, double prices) {
        this.name = name;
        this.prices = prices;
    }

    /**
     * This method would return the name for the specific movie type
     *
     * @return The name for the specific movie type
     */
    public String getName() {

        return name;
    }

    /**
     * This method would return the extra charges for the specific cinema type
     *
     * @return The extra charges for the specific cinema type
     */
    public double getTicketPrice() {

        return prices;
    }

    /**This method would set the extra charges for the specific cinema type
     * @param prices          The extra charges for the specific cinema type
     */
    public void setTicketPrice(double prices) {

        this.prices = prices;
    }

}
