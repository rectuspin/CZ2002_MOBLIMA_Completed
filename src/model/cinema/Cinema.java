package model.cinema;

import model.Model;

import java.util.HashMap;

/**
 * Cinema class
 */
public class Cinema implements Model {
//    private static final Scanner scanner = new Scanner(System.in);

//    private UUID uuid;
    /**
     * name of cinema
     */
    private String name;
    /**
     * a template layout of the cinema
     */
    private HashMap<Character, Seat[]> cinemaLayout;
    /**
     * IMAX, DIGITAL or 3D
     */
    private CinemaType cinemaType;
    /**
     * max row
     */
    private char maxRow = 'J';
    /**
     * max column
     */
    private int maxCol = 16;

    /**
     * This is a constructor for cinema
     *
     * @param name The name of the cinema
     */
    public Cinema(String name) {

        this.name = name;
        createCinemaLayout();
        this.cinemaType = CinemaType.STANDARD;
    }

    /**
     * This is another constructor for the cinema
     *
     * @param name       The name of the cinema
     * @param maxRow     The maximum row for the seats in the cinema
     * @param maxCol     The maximum column for the seats in the cinema
     * @param cinemaType The type of cinema
     */
    public Cinema(String name, char maxRow, int maxCol, CinemaType cinemaType) {
        this.name = name;
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        createCinemaLayout(maxRow, maxCol);
        this.cinemaType = cinemaType;
    }

    /**
     * This method will return the name of the cinema
     *
     * @return The name of the cinema
     */
    public String getName() {

        return name;
    }

    /**This method will create the layout for a cinema
     */
    public void createCinemaLayout() {

        this.cinemaLayout = CinemaLayoutFactory.getCinemaLayout(this);
    }

    /**
     * This method will create the layout for a cinema based on the given maximum row and column
     *
     * @param maxRow The maximum row for the seats in the cinema
     * @param maxCol The maximum column for the seats in the cinema
     */
    public void createCinemaLayout(char maxRow, int maxCol) {

        this.cinemaLayout = CinemaLayoutFactory.getCinemaLayout(this, maxRow, maxCol);
    }

    /**
     * This method will clone the layout of the cinema
     *
     * @return The clone version of the cinema layout
     */
    public HashMap<Character, Seat[]> copyCinemaLayout() {

        return (HashMap<Character, Seat[]>) (cinemaLayout.clone());
    }

    /**
     * This method will return the layout of the cinema
     *
     * @return The cinema layout
     */
    public HashMap<Character, Seat[]> getCinemaLayout() {

        return cinemaLayout;
    }

    /**This method will return the maximum row of a cinema
     * @return The maximum row number in the cinema
     */
    public char getMaxRow() {

        return maxRow;
    }

    /**This method will return the maximum column of a cinema
     * @return The maximum column number in the cinema
     */
    public int getMaxCol() {

        return maxCol;
    }

    /**This method will return the type of cinema
     * @return The type of cinema
     */
    public CinemaType getCinemaType() {

        return cinemaType;
    }

    /**
     * This method will set the type of cinema
     *
     * @param cinemaType The type of cinema
     */
    public void setCinemaType(CinemaType cinemaType) {

        this.cinemaType = cinemaType;
    }

    /**This method will return the format of how the cinema should be printed out
     * @return The format of printing the cinema information
     */
    @Override
    public String toString() {

        return "Cinema{" +
                "name='" + name + '\'' +
                ", cinemaLayout=" + cinemaLayout +
                ", cinemaType=" + cinemaType +
                '}';
    }
}
