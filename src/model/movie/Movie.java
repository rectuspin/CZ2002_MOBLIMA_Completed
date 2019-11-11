package model.movie;

import model.Model;

import java.util.ArrayList;

public class Movie implements Model {
    private String title;
    private String director;
    private ArrayList<String> cast;
    private String synopsis;
    private MovieEnums.Language language = MovieEnums.Language.ENGLISH;
    private MovieEnums.Subtitle subtitle = MovieEnums.Subtitle.English;
    private MovieEnums.MovieStatus movieStatus = MovieEnums.MovieStatus.ComingSoon;
    private MovieEnums.MovieRating movieRating = MovieEnums.MovieRating.PG;
    private MovieEnums.MovieType movieType = MovieEnums.MovieType.DIGITAL;
    private Float overallRating = null;
    private ArrayList<Review> reviews;

    public Movie(String title, String director, ArrayList<String> cast, String synopsis) {
        //this.uuid = UUID.fromString(title); Error in this code
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.synopsis = synopsis;
        reviews = new ArrayList<>();
    }

    public Movie(String title, String director, ArrayList<String> cast, String synopsis, MovieEnums.Language language, MovieEnums.Subtitle subtitle, MovieEnums.MovieStatus movieStatus, MovieEnums.MovieRating movieRating, MovieEnums.MovieType movieType) {
        //this.uuid = UUID.fromString(title); Error in this code
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public MovieEnums.Language getLanguage() {
        return language;
    }

    public void setLanguage(MovieEnums.Language language) {
        this.language = language;
    }

    public MovieEnums.Subtitle getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(MovieEnums.Subtitle subtitle) {
        this.subtitle = subtitle;
    }

    public MovieEnums.MovieStatus getMovieStatus() {
        return movieStatus;
    }

    public void setMovieStatus(MovieEnums.MovieStatus movieStatus) {
        this.movieStatus = movieStatus;
    }

    public MovieEnums.MovieRating getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(MovieEnums.MovieRating movieRating) {
        this.movieRating = movieRating;
    }

    public MovieEnums.MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieEnums.MovieType movieType) {
        this.movieType = movieType;
    }

    public Float getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(Float overallRating) {
        this.overallRating = overallRating;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", cast=" + cast +
                ", synopsis='" + synopsis + '\'' +
                ", movieStatus=" + movieStatus +
                ", overallRating=" + overallRating +
                ", reviews=" + reviews +
                '}';
    }
}
