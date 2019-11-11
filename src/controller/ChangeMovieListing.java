package controller;

import model.movie.Movie;
import model.movie.MovieEnums;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static view.TicketPriceMenuView.backOption;

public class ChangeMovieListing {
    private static DBController dbController = DBController.getInstance();

    public static void Create() {
        /**This method is used to create a movie listing
         */
        System.out.println("How many movies do you want to add?");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String movieTitle, movieDirector, movieSynopsis;

        for (int i = 0; i < n; i++) {
            System.out.print("Movie's Title: ");
            sc.nextLine();
            movieTitle = sc.nextLine();
            System.out.print("Movie's Director: ");
            movieDirector = sc.nextLine();
            ArrayList<String> movieCast = addCast();
            System.out.print("Movie's Synopsis: ");
            movieSynopsis = sc.nextLine();
            Movie movie = new Movie(movieTitle, movieDirector, movieCast, movieSynopsis);
            dbController.addMovies(movie);
        }
        System.out.println("You created a new movie list.");
    }

    public static void Update() {
        /**This method is used to update the movie data
         * @return goes back to the previous interface
         */
        System.out.println("Which movie do you want to update?");
        ArrayList<Movie> movies = dbController.getMovies();
        int i = 1;
            System.out.println("\n====================================\n" +
                                "|               Movies             |\n" +
                                "====================================");
        if (movies.size() == 0){
            System.out.println( "| There is no movies currently...  |" );
            System.out.println( "| (1) Back                         |");
            System.out.println( "====================================");
            if (backOption()) {
                return;
            }
        }else {
            for (Movie movie : movies) {
                System.out.println("[" + i + "] " + movie.getTitle());
                i++;
            }
        }
        System.out.println("====================================");
        Scanner sc = new Scanner(System.in);
        System.out.print("Select a movie: ");
        int movieOption = sc.nextInt();
        while (true) {
            UpdateMenu();
            System.out.print("Select an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                //This is used to edit the movie title
                System.out.println("Movie Title: " + movies.get(movieOption-1).getTitle());
                System.out.print("Title: ");
                String title = sc.nextLine();
                movies.get(movieOption - 1).setTitle(title);

            } else if (choice == 2) {
                //This is used to edit the movie director
                System.out.println("Movie Director: " + movies.get(movieOption-1).getDirector());
                System.out.print("Director: ");
                String director = sc.nextLine();
                movies.get(movieOption - 1).setDirector(director);

            } else if (choice == 3) {
                //This is used to edit the movie cast members
                System.out.println("Movie Cast: ");
                ArrayList<String> cast = addCast();
                movies.get(movieOption - 1).setCast(cast);

            } else if (choice == 4) {
                //This is used to edit the movie synopsis
                System.out.println("Movie Synopsis: \n" + movies.get(movieOption-1).getSynopsis());
                System.out.print("Synopsis: ");
                String synopsis = sc.nextLine();
                movies.get(movieOption - 1).setSynopsis(synopsis);

            } else if (choice == 5) {
                //This is used to edit the language of the movie
                movieLanguageSettings(movies, movieOption);

            } else if (choice == 6) {
                //This is used to edit the subtitle of the movie
                movieSubtitleSettings(movies, movieOption);

            } else if (choice == 7) {
                //This is used to edit the movie status
                movieStatusSettings(movies, movieOption);

            } else if (choice == 8) {
                //This is used to update the rating of the movie
                movieRatingSettings(movies, movieOption);

            } else if (choice == 9) {
                //This is used to update the movie type of the movie
                movieTypeSettings(movies, movieOption);

            } else if (choice == 10){
                //This is used to go back to the previous interface
                return;
            }
        }
    }

    public static void Remove() {
        /**This method is used to remove a movie listing from the application
         */
        ArrayList<Movie> movies = dbController.getMovies();
        int i = 1;
        Scanner sc = new Scanner(System.in);
            System.out.println( "\n====================================\n" +
                                "|               Movies             |\n" +
                                "====================================");
        if (movies.size() == 0){
            System.out.println( "| There is no movies currently...  |" );
            System.out.println( "| (1) Back                         |");
            System.out.println( "====================================");
            if (backOption()) {
                return;
            }
        }else {
            for (Movie movie : movies) {
                System.out.println("[" + i + "] " + movie.getTitle());
                i++;
            }
            System.out.println("====================================");
            System.out.print("Which movie do you want to remove?: ");

            int option = sc.nextInt();
            movies.remove(option - 1);
        }
    }

    public static ArrayList<String> addCast(){
        /**This method is used to edit the cast of the movie
         * @return a list of cast that acts in the movie
         */
        ArrayList<String> movieCast = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Number Of Cast: ");
            int n = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < n; i++) {
                System.out.print("Movie's Cast " + (i+1) + " : ");
                String cast = sc.nextLine();
                movieCast.add(cast);
            }
        } catch (Exception e) {
            System.out.println("[System: Invalid Input]");
        }
        return movieCast;
    }

    public static void movieLanguageSettings(ArrayList<Movie> movies, int movieOption){
        /**This method is used to display the available languages and select the language for the movie
         * @param movies The list that consist of all the movies
         * @param movieOption The selection of which language is for the movie
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.print(   "===================================\n" +
                            "|       Available Languages       |\n" +
                            "===================================\n");
        int i = 1;
        for (MovieEnums.Language l : MovieEnums.Language.values()){
            System.out.format("| (%d) %-27s |\n",i, l);
            i++;
        }
        System.out.print( "===================================\n");
        System.out.println("Movie Language: " + movies.get(movieOption-1).getLanguage());
        System.out.print("Enter a selection: ");
        int opt = sc.nextInt();
        movies.get(movieOption - 1).setLanguage(MovieEnums.Language.values()[opt-1]);
    }

    public static void movieSubtitleSettings(ArrayList<Movie> movies, int movieOption){
        /**This method is used to display the available subtitle and select the language for the movie
         * @param movies The list that consist of all the movies
         * @param movieOption The selection of which subtitle is for the movie
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.print(   "===================================\n" +
                            "|       Available Subtitles       |\n" +
                            "===================================\n");
        int i = 1;
        for (MovieEnums.Subtitle s : MovieEnums.Subtitle.values()){
            System.out.format("| (%d) %-27s |\n",i, s);
            i++;
        }
        System.out.print( "===================================\n");
        System.out.println("Movie Subtitle: " + movies.get(movieOption-1).getSubtitle());
        System.out.print("Enter a selection: ");
        int opt = sc.nextInt();
        movies.get(movieOption - 1).setSubtitle(MovieEnums.Subtitle.values()[opt-1]);
    }

    public static void movieStatusSettings(ArrayList<Movie> movies, int movieOption){
        /**This method is used to display the available movie status and select the language for the movie
         * @param movies The list that consist of all the movies
         * @param movieOption The selection of which movie status is for the movie
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.print(   "====================================\n" +
                            "|            Movie Status          |\n" +
                            "====================================\n");
        int i = 1;
        for (MovieEnums.MovieStatus s : MovieEnums.MovieStatus.values()){
            System.out.format("| (%d) %-28s |\n",i, s);
            i++;
        }
        System.out.print( "====================================\n");
        System.out.println("Movie Status: " + movies.get(movieOption-1).getMovieStatus());
        System.out.print("Enter a selection: ");
        int opt = sc.nextInt();
        movies.get(movieOption - 1).setMovieStatus(MovieEnums.MovieStatus.values()[opt-1]);
    }

    public static void movieRatingSettings(ArrayList<Movie> movies, int movieOption){
        /**This method is used to display the available movie rating and select the language for the movie
         * @param movies The list that consist of all the movies
         * @param movieOption The selection of which movie rating is for the movie
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.print(   "================================================\n" +
                            "|                  Movie Rating                |\n" +
                            "================================================\n");
        int i = 1;
        for (MovieEnums.MovieRating s : MovieEnums.MovieRating.values()){
            System.out.format("| (%d) %-40s |\n",i, s);
            i++;
        }
        System.out.print( "================================================\n");
        System.out.println("Movie Rating: " + movies.get(movieOption-1).getMovieRating());
        System.out.print("Enter a selection: ");
        int opt = sc.nextInt();
        movies.get(movieOption - 1).setMovieRating(MovieEnums.MovieRating.values()[opt-1]);
    }

    public static void movieTypeSettings(ArrayList<Movie> movies, int movieOption){
        /**This method is used to display the available movie type and select the language for the movie
         * @param movies The list that consist of all the movies
         * @param movieOption The selection of which movie type is for the movie
         */

        Scanner sc = new Scanner(System.in);
        System.out.print(   "====================================\n" +
                            "|             Movie Type           |\n" +
                            "====================================\n");
        int i = 1;
        for (MovieEnums.MovieType s : MovieEnums.MovieType.values()){
            System.out.format("| (%d) %-28s |\n",i, s);
            i++;
        }

        System.out.print( "====================================\n");
        System.out.println("Movie Type: " + movies.get(movieOption-1).getMovieType());
        System.out.print("Enter a selection: ");
        int opt = sc.nextInt();
        movies.get(movieOption - 1).setMovieType(MovieEnums.MovieType.values()[opt-1]);
    }

    public static void UpdateMenu(){
        /**This method is used to display the menu for editing the movie
         */
        System.out.println("");
        System.out.format(  "====================================\n" +
                            "|           Select Option          |\n" +
                            "====================================\n" +
                            "| (1) Edit Title                   |\n" +
                            "| (2) Edit Director                |\n" +
                            "| (3) Edit Cast                    |\n" +
                            "| (4) Edit Synopsis                |\n" +
                            "| (5) Edit Language                |\n" +
                            "| (6) Edit Subtitle                |\n" +
                            "| (7) Edit Movie Status            |\n" +
                            "| (8) Edit Movie Rating            |\n" +
                            "| (9) Edit Movie Type              |\n" +
                            "|(10) Back                         |\n" +
                            "====================================\n");
    }

}
