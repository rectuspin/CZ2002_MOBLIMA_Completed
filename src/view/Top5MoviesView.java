package view;

import controller.DBController;
import model.movie.Movie;
import model.movie.Review;

import java.util.*;
import java.util.Map.Entry;


/**
 * Class containing methods to display top 5 movies by sales or ratings
 */
public class Top5MoviesView {
    private static DBController dbController = DBController.getInstance();

    /**
     * Method prints the top 5 movies based user ratings
     */
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

    /**
     * Method prints the top 5 movies based on overall movie sales
     */
    public static void printTop5Movies() {


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

    /**
     * Sort Movies by ratings / sales
     *
     * @param map
     * @param n
     * @param <K>
     * @param <V>
     * @return
     */
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

    /**
     * Method obtains the ratings for a particular movie
     * @param movie
     * @return
     */
    public static float getRating(Movie movie) {
        ArrayList<Review> reviews = movie.getReviews();
        float total = 0;
        for(Review review : reviews) {
            total += review.getRating();
        }
        return (reviews.size()==0) ? 0 : total / reviews.size();
    }
}