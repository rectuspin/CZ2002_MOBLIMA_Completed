package model.cinema;

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

    public String getName() {
        /**This method would return the name for the specific movie type
         * @return The name for the specific movie type
         */
        return name;
    }

    public double getTicketPrice() {
        /**This method would return the extra charges for the specific cinema type
         * @return The extra charges for the specific cinema type
         */
        return prices;
    }

    public void setTicketPrice(double prices) {
        /**This method would set the extra charges for the specific cinema type
         * @param price          The extra charges for the specific cinema type
         */
        this.prices = prices;
    }
}
