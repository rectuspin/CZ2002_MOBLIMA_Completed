package model.cinema;

import java.util.HashMap;

public class CinemaLayoutFactory {

    private static Integer maxCol = 16;
    private static Character maxRow = 'J';

    public static HashMap<Character, Seat[]> getCinemaLayout(Cinema cinema) {
        /**This method is used to get the cinema layout based on a given cinema
         * @param cinema        the cinema selected to obtain the seat layout
         * @return              the layout of the cinema
         */
        HashMap<Character, Seat[]> layout = new HashMap<>();
        for (char c = cinema.getMaxRow(); c >= 'A'; c--) {
            Seat[] row = new Seat[cinema.getMaxCol()];
            for (int i = 0; i < cinema.getMaxCol(); i++) {
                row[i] = new Seat(cinema, c, i + 1);
            }
            layout.put(c, row);
        }
        return layout;
    }

    public static HashMap<Character, Seat[]> getCinemaLayout(Cinema cinema, char maxRow, int maxCol) {
        /**This method is used to get the cinema layout based on a given cinema, maximum row and column
         * @param cinema        the cinema selected to obtain the seat layout
         * @param maxRow        the maximum number of seats for a row
         * @param maxCol        the maximum number of seats for a column
         * @return              the layout of the cinema
         */
        HashMap<Character, Seat[]> layout = new HashMap<>();
        for (char c = maxRow; c >= 'A'; c--) {
            Seat[] row = new Seat[maxCol];
            for (int i = 0; i < maxCol; i++) {
                row[i] = new Seat(cinema, c, i + 1);
            }
            layout.put(c, row);
        }
        return layout;
    }

}
