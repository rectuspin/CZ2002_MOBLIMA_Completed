package view;

import controller.DBController;
import model.movie.Movie;
import model.movie.Review;

import java.util.*;
import java.util.Map.Entry;

public class Top5MoviesView {
    private static DBController dbController = DBController.getInstance();

    public static void printTop5MoviesByRatings() {
        ArrayList<Movie> movies = dbController.getMovies();
        HashMap<String, Float> ratings = new HashMap<>();

        for(Movie movie : movies) {
            ratings.put(movie.getTitle(), getRating(movie));
        }

        Object[] a = ratings.entrySet().toArray();
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2) {
                int i = ((Map.Entry<String, Float>) o2).getValue()
                        .compareTo(((Map.Entry<String, Float>) o1).getValue());
                return i;
            }
        });

        List<Entry<String, Float>> top5Movies = findGreatest(ratings, 5);
        Collections.reverse(top5Movies);
        System.out.println("Top 5 Movies:");

        for (Entry<String, Float> entry : top5Movies) {
            System.out.println(entry.getKey());
        }
    }

    public static void printTop5Movies() {

        /* for the testing purpose. You are free to remove it
        ArrayList<String> cast=null;
        String synopsis=null;
        Movie movie = new Movie("Iron Man", "Jon Favreau", cast, synopsis);
        Movie movie1 = new Movie("Iron Man 2", "Jon Favreau", cast, synopsis);
        Movie movie2 = new Movie("Iron Man 3", "Jon Favreau", cast, synopsis);
        Movie movie3 = new Movie("Iron Man 4", "Jon Favreau", cast, synopsis);
        Movie movie4 = new Movie("Iron Man 5", "Jon Favreau", cast, synopsis);
        Movie movie5 = new Movie("Iron Man 6", "Jon Favreau", cast, synopsis);
        Movie movie6 = new Movie("Iron Man 7", "Jon Favreau", cast, synopsis);
        Movie movie7 = new Movie("Iron Man 8", "Jon Favreau", cast, synopsis);

        Cineplex cineplex  = new Cineplex("JURONG POINT");
        Cinema cinema = new Cinema("HALL 1");
        cinema.setCinemaType(CinemaType.PLATINUM_MOVIE_SUITES);
        LocalDate date=null;
        LocalTime time=null;
        HashMap<Character, Seat[]> seatLayout=null;
        Cineplex cineplex1=null;
        Cinema cinema1=null;
        Seat seat1;
        ShowTime showTime = new ShowTime(movie,date,time, seatLayout, cineplex1,cinema1);
        ShowTime showTime1 = new ShowTime(movie,date,time, seatLayout, cineplex1,cinema1);
        ShowTime showTime2 = new ShowTime(movie,date,time, seatLayout, cineplex1,cinema1);
        ShowTime showTime3 = new ShowTime(movie,date,time, seatLayout, cineplex1,cinema1);
        ShowTime showTime4 = new ShowTime(movie,date,time, seatLayout, cineplex1,cinema1);
        ShowTime showTime5 = new ShowTime(movie,date,time, seatLayout, cineplex1,cinema1);
        ShowTime showTime6 = new ShowTime(movie,date,time, seatLayout, cineplex1,cinema1);
        ShowTime showTime7 = new ShowTime(movie,date,time, seatLayout, cineplex1,cinema1);
        ShowTime showTime8 = new ShowTime(movie,date,time, seatLayout, cineplex1,cinema1);

        Seat[] seat= new Seat[0];
        Customer customer=null;
        Booking booking = new Booking(LocalDate.now(), LocalTime.now(), showTime, seat, customer);
        Booking booking1 = new Booking(LocalDate.now(), LocalTime.now(), showTime1, seat, customer);
        Booking booking2 = new Booking(LocalDate.now(), LocalTime.now(), showTime2, seat, customer);
        Booking booking3 = new Booking(LocalDate.now(), LocalTime.now(), showTime3, seat, customer);
        Booking booking4 = new Booking(LocalDate.now(), LocalTime.now(), showTime4, seat, customer);
        Booking booking5 = new Booking(LocalDate.now(), LocalTime.now(), showTime5, seat, customer);
        Booking booking6 = new Booking(LocalDate.now(), LocalTime.now(), showTime6, seat, customer);
        Booking booking7 = new Booking(LocalDate.now(), LocalTime.now(), showTime6, seat, customer);
        Booking booking8 = new Booking(LocalDate.now(), LocalTime.now(), showTime6, seat, customer);

        booking.setCinema(cinema);
        booking.makeBooking(AgeGroup.STUDENT_PRICE);
        int i;
        for(i=0;i<8;i++){booking.makeBooking();}
        for(i=0;i<7;i++){booking1.makeBooking();}
        for(i=0;i<4;i++){booking2.makeBooking();}
        for(i=0;i<5;i++){booking3.makeBooking();}
        for(i=0;i<6;i++){booking4.makeBooking();}
        for(i=0;i<3;i++){booking5.makeBooking();}
        for(i=0;i<1;i++){booking6.makeBooking();}
        for(i=0;i<1;i++){booking7.makeBooking();}
        for(i=0;i<1;i++){booking8.makeBooking();}

        dbController.addSales(booking);
        dbController.addSales(booking1);
        dbController.addSales(booking2);
        dbController.addSales(booking3);
        dbController.addSales(booking4);
        dbController.addSales(booking5);
        dbController.addSales(booking6);
        dbController.addSales(booking7);
        dbController.addSales(booking8);
        for the testing purpose. You are free to remove it */

        HashMap<String, Integer> tempTopMovies;
        tempTopMovies = dbController.getSales();
        Object[] a = tempTopMovies.entrySet().toArray();
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2) {
                int i = ((Map.Entry<String, Integer>) o2).getValue()
                        .compareTo(((Map.Entry<String, Integer>) o1).getValue());
                return i;
            }
        });

        List<Entry<String, Integer>> top5Movies = findGreatest(tempTopMovies, 5);
        Collections.reverse(top5Movies);
        System.out.println("Top 5 Movies:");

        //Printing the Top 5 Movies
        for (Entry<String, Integer> entry : top5Movies) {
            System.out.println(entry.getKey());
        }
    }

    private static <K, V extends Comparable<? super V>> List<Entry<K, V>> findGreatest(Map<K, V> map, int n) {
        Comparator<? super Entry<K, V>> comparator =
                new Comparator<Entry<K, V>>() {
                    @Override
                    public int compare(Entry<K, V> e0, Entry<K, V> e1) {
                        V v0 = e0.getValue();
                        V v1 = e1.getValue();
                        return v0.compareTo(v1);
                    }
                };
        PriorityQueue<Entry<K, V>> highest = new PriorityQueue<Entry<K, V>>(n, comparator);
        for (Entry<K, V> entry : map.entrySet()) {
            highest.offer(entry);
            while (highest.size() > n) {
                highest.poll();
            }
        }

        List<Entry<K, V>> result = new ArrayList<Map.Entry<K, V>>();
        while (highest.size() > 0) {
            result.add(highest.poll());
        }
        return result;
    }
    public static float getRating(Movie movie) {
        ArrayList<Review> reviews = movie.getReviews();
        float total = 0;
        for(Review review : reviews) {
            total += review.getRating();
        }
        return (reviews.size()==0) ? 0 : total / reviews.size();
    }
}