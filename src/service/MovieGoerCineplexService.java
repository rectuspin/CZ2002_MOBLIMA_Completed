package service;

import controller.DBController;
import model.cinema.Cinema;
import model.cinema.Cineplex;
import model.cinema.Seat;
import model.cinema.ShowTime;
import model.movie.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class MovieGoerCineplexService {

    // returns showtimes at a cineplex on a certain day
    public ArrayList<ShowTime> getShowTimes(Cineplex cineplex, LocalDate localDate) {
        return cineplex.getShowTimes().get(localDate);
    }

    // returns showtimes for a certain movie for the next 3 days
    public ArrayList<ShowTime> getShowTimes(ArrayList<Cineplex> cineplexes, Movie movie) {
        LocalDate today = LocalDate.now();
        LocalDate threeDaysLater = today.plusDays(3);

        ArrayList<ShowTime> nextThreeDays = new ArrayList<>();

        for (; !today.equals(threeDaysLater); today = today.plusDays(1)) {
            for (Cineplex cineplex : cineplexes) {
                // check for null to prevent NullPointerException
                if (!(cineplex.getShowTimes().get(today) == null)) {
                    ArrayList<ShowTime> showTimes = cineplex.getShowTimes().get(today);
                    for (ShowTime showTime : showTimes) {
                        if (showTime.getMovie() == movie) {
                            nextThreeDays.add(showTime);
                        }
                    }
                    }
                }
            }

        return nextThreeDays;
    }

    // returns showtimes for a cineplex for the next 3 days
    public ArrayList<ShowTime> getShowTimes(Cineplex cineplex) {
        LocalDate today = LocalDate.now();
        LocalDate threeDaysLater = today.plusDays(3);
        ArrayList<ShowTime> nextThreeDays = new ArrayList<>();
        for (; !today.equals(threeDaysLater); today = today.plusDays(1)) {
            if (!(cineplex.getShowTimes().get(today) == null)) {
                ArrayList<ShowTime> showTimes = cineplex.getShowTimes().get(today);
                for (ShowTime showTime : showTimes) {
                    nextThreeDays.add(showTime);
                }
            }
        }
        return nextThreeDays;
    }

    public ArrayList<ShowTime> getShowTimes(Movie movie) {
        DBController dbController = DBController.getInstance();
        LocalDate today = LocalDate.now();
        LocalDate threeDaysLater = today.plusDays(3);
        ArrayList<Cineplex> cineplexes = new ArrayList<>(dbController.getCineplexes().values());
        ArrayList<ShowTime> nextThreeDays = new ArrayList<>();
        for (; !today.equals(threeDaysLater); today = today.plusDays(1)) {
            for (Cineplex cineplex : cineplexes) {
                if (!(cineplex.getShowTimes().get(today) == null)) {
                    ArrayList<ShowTime> showTimes = cineplex.getShowTimes().get(today);
                    for (ShowTime showTime : showTimes) {
                        if (showTime.getMovie().getTitle().toLowerCase().equals(movie.getTitle().toLowerCase())) {
                            nextThreeDays.add(showTime);
                        }
                    }
                }
            }
        }
        return nextThreeDays;
    }

    public void makeBooking(ShowTime showTime, String[] seatPos, String name) {
        HashMap<Character, Seat[]> layout = showTime.getSeatLayout();
        for (String seat : seatPos) {
            char row = Character.toUpperCase(seat.charAt(0));
            int col = seat.charAt(1);
            layout.get(row)[col - 1].makeBooking();
        }
    }

    public void cancelBooking(ShowTime showTime, String[] seatPos, String name) {
        HashMap<Character, Seat[]> layout = showTime.getSeatLayout();
        for (String seat : seatPos) {
            char row = Character.toUpperCase(seat.charAt(0));
            int col = seat.charAt(1);
            layout.get(row)[col - 1].cancelBooking();
        }
    }

    public void showAvailableSeats(ShowTime showTime) {
        HashMap<Character, Seat[]> layout = showTime.getSeatLayout();
        TreeSet<Character> rows = new TreeSet<>(layout.keySet());
        Iterator<Character> it = rows.descendingIterator();
        int rowLength = layout.get('A').length;
//        int length = rows.size() * 3
        for (int i = 0; i < rowLength / 2; i++) {
            System.out.print("===");
        }
        System.out.print("  S C R E E N  ");
        for (int i = rowLength / 2; i < rowLength - 1; i++) {
            System.out.print("===");
        }
        System.out.print("\n\n");


        while (it.hasNext()) {
            Character row = it.next();
            Seat[] seats = layout.get(row);
            System.out.print(row + "  ");
            for (int i = 0; i < seats.length / 2; i++) {
                System.out.print(seats[i]);
            }
            for (int i = 0; i < 3; i++) {
                System.out.print("   ");
            }
            for (int i = seats.length / 2; i < seats.length; i++) {
                System.out.print(seats[i]);
            }
            System.out.println();
        }
        System.out.print("\n\n");

        for (int i = 0; i < rowLength / 2 - 1; i++) {
            System.out.print("   ");
        }
        System.out.print(" | E N T R A N C E |");

        System.out.print("\n\n\n\n");
    }


    public void showAvailableSeats(Cinema cinema) {
        HashMap<Character, Seat[]> layout = cinema.getCinemaLayout();
        TreeSet<Character> rows = new TreeSet<>(layout.keySet());
        Iterator<Character> it = rows.descendingIterator();
        int rowLength = layout.get('A').length;
//        int length = rows.size() * 3
        for (int i = 0; i < rowLength / 2; i++) {
            System.out.print("===");
        }
        System.out.print("  S C R E E N  ");
        for (int i = rowLength / 2; i < rowLength - 1; i++) {
            System.out.print("===");
        }
        System.out.print("\n\n");


        while (it.hasNext()) {
            Character row = it.next();
            Seat[] seats = layout.get(row);
            System.out.print(row + "  ");
            for (int i = 0; i < seats.length / 2; i++) {
                System.out.print(seats[i]);
            }
            for (int i = 0; i < 3; i++) {
                System.out.print("   ");
            }
            for (int i = seats.length / 2; i < seats.length; i++) {
                System.out.print(seats[i]);
            }
            System.out.println();
        }
        System.out.print("\n\n");

        for (int i = 0; i < rowLength / 2 - 1; i++) {
            System.out.print("   ");
        }
        System.out.print(" | E N T R A N C E |");

        System.out.print("\n\n\n\n");

    }




}
