package model.cinema;

import model.Model;

public class Seat implements Model {

    private boolean isOccupied = false;
    private Cinema cinema;
    private char row;
    private int col;

    public Seat(Cinema cinema, char row, int col) {
        /**This is constructor for Seat
         * @param cinema        The cinema that consist of the seat
         * @param row           The alphabetically numbered row of the seat
         * @param col           The column number of the seat
         */
        this.cinema = cinema;
        this.row = row;
        this.col = col;
    }

    public String toString() {
        /**This method will return a string that is formatted to show if the seat is occupied or not occupied
         * @return              A string formatted that shows if a seat is occupied or unoccupied
         */
        return isOccupied ? "[X]" : "[ ]";
    }

    public boolean makeBooking() {
        /**This method will change the status of the seat to occupied where it means that the seat has been booked
         * @return              true if the selected seat is unoccupied
         * @return              false id the selected seat is occupied already
         */
        if (!isOccupied) {
            isOccupied = true;
            return true;
        }
        return false;
    }

    public boolean cancelBooking() {
        /**This method will change the status of the seat to unoccupied where it means that the booked seat has been
         * cancelled
         * @return              true if the selected seat is occupied
         * @return              false id the selected seat is unoccupied
         */
        if (isOccupied) {
            isOccupied = false;
            return true;
        }
        return false;
    }
}
