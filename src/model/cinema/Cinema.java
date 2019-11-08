package model.cinema;

import model.Model;

import java.util.HashMap;

public class Cinema implements Model {
//    private static final Scanner scanner = new Scanner(System.in);

//    private UUID uuid;

    private String name;
    private HashMap<Character, Seat[]> cinemaLayout;
    private CinemaType cinemaType;
    private char maxRow = 'J';
    private int maxCol = 16;

    public Cinema(String name) {
        /**This is a constructor for cinema
         * @param name          The name of the cinema
         */
        this.name = name;
//        this.uuid = UUID.fromString(name);
        createCinemaLayout();
        this.cinemaType = CinemaType.STANDARD;
    }

    public Cinema(String name, char maxRow, int maxCol, CinemaType cinemaType) {
        /**This is another constructor for the cinema
         * @param name          The name of the cinema
         * @param maxRow        The maximum row for the seats in the cinema
         * @param maxCol        The maximum column for the seats in the cinema
         * @param cinemaType    The type of cinema
         */
//        this.uuid = UUID.fromString(name);
        this.name = name;
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        createCinemaLayout(maxRow, maxCol);
        this.cinemaType = cinemaType;
    }

    public String getName() {
        /**This method will return the name of the cinema
         * @return              The name of the cinema
         */
        return name;
    }

    public void createCinemaLayout() {
        /**This method will create the layout for a cinema
         */
        this.cinemaLayout = CinemaLayoutFactory.getCinemaLayout(this);
    }

    public void createCinemaLayout(char maxRow, int maxCol) {
        /**This method will create the layout for a cinema based on the given maximum row and column
         * @param maxRow        The maximum row for the seats in the cinema
         * @param maxCol        The maximum column for the seats in the cinema
         */
        this.cinemaLayout = CinemaLayoutFactory.getCinemaLayout(this, maxRow, maxCol);
    }

    public HashMap<Character, Seat[]> copyCinemaLayout() {
        /**This method will clone the layout of the cinema
         * @return              The clone version of the cinema layout
         */
        return (HashMap<Character, Seat[]>) (cinemaLayout.clone());
    }

    public HashMap<Character, Seat[]> getCinemaLayout() {
        /**This method will return the layout of the cinema
         * @return              The cinema layout
         */
        return cinemaLayout;
    }

    public char getMaxRow() {
        /**This method will return the maximum row of a cinema
         * @return              The maximum row number in the cinema
         */
        return maxRow;
    }

    public int getMaxCol() {
        /**This method will return the maximum column of a cinema
         * @return              The maximum column number in the cinema
         */
        return maxCol;
    }

    public CinemaType getCinemaType() {
        /**This method will return the type of cinema
         * @return              The type of cinema
         */
        return cinemaType;
    }

    public void setCinemaType(CinemaType cinemaType) {
        /**This method will set the type of cinema
         * @param cinemaType              The type of cinema
         */
        this.cinemaType = cinemaType;
    }

    @Override
    public String toString() {
        /**This method will return the format of how the cinema should be printed out
         * @return              The format of printing the cinema information
         */
        return "Cinema{" +
                "name='" + name + '\'' +
                ", cinemaLayout=" + cinemaLayout +
                ", cinemaType=" + cinemaType +
                '}';
    }
}
