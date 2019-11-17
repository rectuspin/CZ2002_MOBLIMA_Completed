package model.movie;

import model.Model;

import java.util.ArrayList;

/**
 * Class containing information of a Movie
 */
public class Movie implements Model {
    /**
     * title of movie
     */
    private String title;
    /**
     * name of director
     */
    private String director;
    /**
     * ArrayList of cast names
     */
    private ArrayList<String> cast;
    /**
     * Synopsis of movie
     */
    private String synopsis;
    /**
     * language of movie
     */
    private MovieEnums.Language language = MovieEnums.Language.ENGLISH;
    /**
     * subtitle of movie
     */
    private MovieEnums.Subtitle subtitle = MovieEnums.Subtitle.English;
    /**
     * status of movie
     */
    private MovieEnums.MovieStatus movieStatus = MovieEnums.MovieStatus.ComingSoon;
    /**
     * rating of movie
     */
    private MovieEnums.MovieRating movieRating = MovieEnums.MovieRating.PG;
    /**
     * type of movie
     */
    private MovieEnums.MovieType movieType = MovieEnums.MovieType.DIGITAL;
    /**
     * overall rating of the movie
     */
    private Float overallRating = null;
    /**
     * arraylist of reviews of the movie
     */
    private ArrayList<Review> reviews;

    /**
     * constructor for Movie
     *
     * @param title
     * @param director
     * @param cast
     * @param synopsis
     */
    public Movie(String title, String director, ArrayList<String> cast, String synopsis) {
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.synopsis = synopsis;
        reviews = new ArrayList<>();
    }

    /**
     * overloaded constructor for Movie
     * @param title
     * @param director
     * @param cast
     * @param synopsis
     * @param language
     * @param subtitle
     * @param movieStatus
     * @param movieRating
     * @param movieType
     */
    public Movie(String title, String director, ArrayList<String> cast, String synopsis, MovieEnums.Language language, MovieEnums.Subtitle subtitle, MovieEnums.MovieStatus movieStatus, MovieEnums.MovieRating movieRating, MovieEnums.MovieType movieType) {
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.synopsis = synopsis;
        this.language = language;
        this.subtitle = subtitle;
        this.movieStatus = movieStatus;
        this.movieRating = movieRating;
        this.movieType = movieType;
        reviews = new ArrayList<>();
    }


    /**
     * get name of movie
     * @return title of movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * set title of movie
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get name of movie director
     * @return
     */
    public String getDirector() {
        return director;
    }

    /**
     * set name of movie director
     * @param director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * get cast names
     * @return arraylist of cast names
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * set cast names
     * @param cast
     */
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * get synopsis of movie
     * @return synopsis of movie
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * set synopsis of movie
     * @param synopsis
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * get Language of Movie
     * @return MovieEnums.Language enum
     */
    public MovieEnums.Language getLanguage() {
        return language;
    }

    /**
     * set Language of Movie
     * @param language
     */
    public void setLanguage(MovieEnums.Language language) {
        this.language = language;
    }

    /**
     * get subtitle of Movie
     * @return
     */
    public MovieEnums.Subtitle getSubtitle() {
        return subtitle;
    }

    /**
     * set subtitle of Movie
     * @param subtitle
     */
    public void setSubtitle(MovieEnums.Subtitle subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * get movie status of Movie
     * @return
     */
    public MovieEnums.MovieStatus getMovieStatus() {
        return movieStatus;
    }

    /**
     * set movie status of Movie
     * @param movieStatus
     */
    public void setMovieStatus(MovieEnums.MovieStatus movieStatus) {
        this.movieStatus = movieStatus;
    }

    /**
     * get rating for movie
     * @return movie rating
     */
    public MovieEnums.MovieRating getMovieRating() {
        return movieRating;
    }

    /**
     * set movie rating for movie
     * @param movieRating
     */
    public void setMovieRating(MovieEnums.MovieRating movieRating) {
        this.movieRating = movieRating;
    }

    /**
     * get type of movie
     * @return movietype
     */
    public MovieEnums.MovieType getMovieType() {
        return movieType;
    }

    /**
     * set type of movie
     * @param movieType
     */
    public void setMovieType(MovieEnums.MovieType movieType) {
        this.movieType = movieType;
    }

    /**
     * get overall rating of movie
     * @return overall rating
     */
    public Float getOverallRating() {
        return overallRating;
    }

    /**
     * set overall rating of movie
     * @param overallRating
     */
    public void setOverallRating(Float overallRating) {
        this.overallRating = overallRating;
    }

    /**
     * get all reviews of the movie
     * @return arraylist of review objects
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * set reviews of the movie
     * @param reviews
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * toString method of Movie
     */
    @Override
    public String toString() {
        return "Movie Title: " + title +
                "\nDirector: " + director +
                "\nCast: " + printCast(cast) +
                "\nSynopsis: " + synopsis +
                "\nMovie Status: " + movieStatus +
                "\nOverall Rating: " + overallRating + "\n\n";
//                ", reviews=" + reviews +

    }

    /**
     * returns a formatted string containing all cast names
     * @param casts
     * @return
     */
    public String printCast(ArrayList<String> casts) {
        if (casts.isEmpty()) {
            return "None";
        }
        String castString = "";
        for (String cast : casts) {
            if (!casts.get(cast.length() - 1).equals(cast))
                castString += cast + ", ";
            else castString += cast;
        }
        return castString;
    }
}
