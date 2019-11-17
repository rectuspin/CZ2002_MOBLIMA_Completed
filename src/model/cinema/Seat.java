package model.cinema;

import model.Model;

/**
 * Seat class
 */
public class Seat implements Model {

    /**
     * indicate if a seat is occupied or not
     */
    private boolean isOccupied = false;
    /**
     * Cinema the seat belongs to
     */
    private Cinema cinema;
    /**
     * Row index
     */
    private char row;
    /**
     * column index
     */
    private int col;

    /**
     * This is constructor for Seat
     *
     * @param cinema The cinema that consist of the seat
     * @param row    The alphabetically numbered row of the seat
     * @param col    The column number of the seat
     */
    public Seat(Cinema cinema, char row, int col) {

        this.cinema = cinema;
        this.row = row;
        this.col = col;
    }
    /**This method will return a string that is formatted to show if the seat is occupied or not occupied
     * @return A string formatted that shows if a seat is occupied or unoccupied
     */
    public String toString() {

        return isOccupied ? "[X]" : "[ ]";
    }

    /**This method will change the status of the seat to occupied where it means that the seat has been booked
     * @return true if the selected seat is unoccupied
     * @return false id the selected seat is occupied already
     */
    public boolean makeBooking() {

        if (!isOccupied) {
            isOccupied = true;
            return true;
        }
        return false;
    }

    /**This method will change the status of the seat to unoccupied where it means that the booked seat has been
     * cancelled
     * @return true if the selected seat is occupied
     * @return false id the selected seat is unoccupied
     */
    public boolean cancelBooking() {

        if (isOccupied) {
            isOccupied = false;
            return true;
        }
        return false;
    }
}
