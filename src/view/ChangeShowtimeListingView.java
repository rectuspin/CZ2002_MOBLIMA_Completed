package view;

import controller.DBController;
import model.cinema.Cinema;
import model.cinema.Cineplex;
import model.cinema.ShowTime;
import model.movie.Movie;
import model.movie.MovieEnums;
import service.AdminCineplexService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ChangeShowtimeListingView {

    public static List list = new ArrayList();
    private static DBController dbController = DBController.getInstance();
    private static Scanner scanner = new Scanner(System.in);
    private static AdminCineplexService adminCineplexService = new AdminCineplexService();

    public static void create() {
        System.out.println("How many showtimes do you want to add?");
        int n = scanner.nextInt();
        ArrayList<Movie> movies = dbController.getMovies();
        ArrayList<String> cineplexes = new ArrayList<>(dbController.getCineplexes().keySet());
        int choiceOfMovie;
        int choiceOfCineplex;
        int choiceOfCinema;
        String date;
        String time;
        for (int i = 0; i < n; i++) {
            printMovies();
            System.out.print("\nEnter your choice of movie: ");
            choiceOfMovie = scanner.nextInt() - 1;
            printCineplex();
            System.out.print("\nEnter your choice of cineplex: ");
            choiceOfCineplex = scanner.nextInt() - 1;
            printCinema(dbController.getCineplexes().get(cineplexes.get(choiceOfCineplex)));
            System.out.print("\nEnter your choice of cinema: ");
            choiceOfCinema = scanner.nextInt() - 1;
            System.out.print("\nEnter the date: (YYYY-MM-DD)");
            date = scanner.nextLine();
            System.out.print("\nEnter the time: (HH:mm:ss)");
            time = scanner.nextLine();
            Cineplex cineplexOfChoice = dbController.getCineplexes().get(cineplexes.get(choiceOfCineplex));
            Cinema cinemaOfChoice = cineplexOfChoice.getCinemas().get(choiceOfCinema);
            adminCineplexService.addShowTime(movies.get(choiceOfMovie), stringToDate(date), stringToTime(time), cinemaOfChoice, cineplexOfChoice);                             //Movie LocalDate LocalTime Cineplex Cinema
        }
    }

    public static void update() {
        ArrayList<String> cineplexNames = new ArrayList<>(dbController.getCineplexes().keySet());
        HashMap<String, Cineplex> cineplexes = dbController.getCineplexes();
        printCineplex();
        System.out.println("Which cineplex do you want to update show times?");
        int choice = scanner.nextInt();
        Cineplex cineplexOfChoice = cineplexes.get(cineplexNames.get(choice - 1));
        printShowTimes(cineplexOfChoice);
        System.out.println("What is the date of the show time you wish to update?");
        LocalDate dateOfChoice = stringToDate(scanner.nextLine());
        ArrayList<ShowTime> showTimes = cineplexOfChoice.getShowTimes().get(dateOfChoice);
        System.out.println("Which show time do you wish to update?");
        int count = 0;
        for (ShowTime showTime : showTimes) {
            System.out.println((++count) + ": " + showTime);
        }
        ShowTime oldShowTime = showTimes.get(scanner.nextInt() - 1);
        boolean continueRunning = true;
        int number;


        ArrayList<Movie> movies = dbController.getMovies();

        List<MovieEnums.Language> languages = Arrays.asList(MovieEnums.Language.values());
        List<MovieEnums.Subtitle> subtitles = Arrays.asList(MovieEnums.Subtitle.values());
        List<MovieEnums.MovieType> movieTypes = Arrays.asList(MovieEnums.MovieType.values());

        Movie movie = oldShowTime.getMovie();
        LocalDate localDate = oldShowTime.getDateOfMovie();
        LocalTime localTime = oldShowTime.getTimeOfMovie();
        Cineplex cineplex = oldShowTime.getCineplex();
        Cinema cinema = oldShowTime.getCinema();
        MovieEnums.Language lang = oldShowTime.getLanguage();
        MovieEnums.Subtitle subs = oldShowTime.getSubtitle();
        MovieEnums.MovieType movieType = oldShowTime.getMovieType();
        while (continueRunning) {
            System.out.println("Which attribute do you wish to update?");
            System.out.println("1. Movie");
            System.out.println("2. Date Of Movie");
            System.out.println("3. Time Of Movie");
            System.out.println("4. Cineplex");
            System.out.println("5. Cinema");
            System.out.println("6. Language");
            System.out.println("7. Subtitles");
            System.out.println("8. Movie Type");
            System.out.println("9. Return");
            number = scanner.nextInt();
            switch (number) {
                case 1:
                    printMovies();
                    System.out.println("Enter choice of movie: ");
                    int movieIndex = scanner.nextInt();
                    movie = movies.get(movieIndex - 1);
                    System.out.println();

                    break;

                case 2:
                    System.out.print("Enter new date of movie: ");
                    localDate = stringToDate(scanner.nextLine());
                    System.out.println();

                    break;

                case 3:
                    System.out.println("Enter new time of movie: ");
                    localTime = stringToTime(scanner.nextLine());
                    System.out.println();

                    break;

                case 4:
                    printCineplex();
                    System.out.println("Enter choice of cineplex: ");
                    cineplex = cineplexes.get(cineplexNames.get(scanner.nextInt() - 1));
                    System.out.println();
                    break;

                case 5:
                    printCinema(cineplex);
                    ArrayList<Cinema> cinemas = cineplex.getCinemas();
                    System.out.println("Enter choice of cinema: ");
                    cinema = cinemas.get(scanner.nextInt() - 1);
                    break;

                case 6:
                    printLanguages();
                    System.out.println("Enter choice of language: ");
                    lang = languages.get(scanner.nextInt() - 1);
                    System.out.println();
                    break;

                case 7:
                    printSubtitles();
                    System.out.println("Enter choice of subtitles: ");
                    subs = subtitles.get(scanner.nextInt() - 1);
                    System.out.println();
                    break;

                case 8:
                    printMovieType();
                    System.out.println("Enter choice of movie type: ");
                    movieType = movieTypes.get(scanner.nextInt());
                    System.out.println();
                    break;

                case 9:
                    continueRunning = false;
                    break;
            }
        }

        ShowTime newShowTime = new ShowTime(movie, localDate, localTime, cinema.copyCinemaLayout(), cineplex, cinema, lang, subs, movieType);
        adminCineplexService.updateShowTime(oldShowTime, newShowTime);
    }

    public static List Remove(List list) {
        list.clear();
        System.out.println("You cleared the showtime list.");
        return list;
    }

    public static void delete() {
        System.out.println("Which cineplex do you want to delete show time from?");
        printCineplex();
    }

    public static void printMovies() {
        ArrayList<Movie> movies = dbController.getMovies();
        int count = 1;
        for (Movie movie : movies) {
            System.out.println((count++) + ": " + movie.getTitle());
        }
    }

    public static void printCineplex() {
        ArrayList<String> cineplexes = new ArrayList<>(dbController.getCineplexes().keySet());
        int count = 1;
        for (String cineplex : cineplexes) {
            System.out.println((count++) + ": " + cineplex);
        }

    }

    public static void printCinema(Cineplex cineplex) {
        ArrayList<Cinema> cinemas = cineplex.getCinemas();
        int count = 1;
        for (Cinema cinema : cinemas) {
            System.out.println((count++) + ": " + cinema.getName());
        }
    }

    public static void printShowTimes(Cineplex cineplex) {
        HashMap<LocalDate, ArrayList<ShowTime>> showTimes = cineplex.getShowTimes();
        ArrayList<LocalDate> dates = new ArrayList<>(showTimes.keySet());

        for (LocalDate date : dates) {
            System.out.println("Show Times on " + date + ": ");
            ArrayList<ShowTime> showTimesOnThisDay = showTimes.get(date);
            for (ShowTime showTime : showTimesOnThisDay) {
                System.out.println(showTime);
            }
        }
    }

    public static void printLanguages() {
        MovieEnums.Language[] languages = MovieEnums.Language.values();
        int count = 0;
        for (MovieEnums.Language lang : languages) {
            System.out.println((++count) + ": " + lang);
        }
    }

    public static void printSubtitles() {
        MovieEnums.Subtitle[] subtitles = MovieEnums.Subtitle.values();
        int count = 0;
        for (MovieEnums.Subtitle subtitle : subtitles) {
            System.out.println((++count) + ": " + subtitle);
        }
    }

    public static void printMovieType() {
        MovieEnums.MovieType[] movieTypes = MovieEnums.MovieType.values();
        int count = 0;
        for (MovieEnums.MovieType movieType : movieTypes) {
            System.out.println((count++) + ": " + movieType);
        }
    }

    public static LocalDate stringToDate(String string) {
        return LocalDate.parse(string, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static LocalTime stringToTime(String string) {
        return LocalTime.parse(string, DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
