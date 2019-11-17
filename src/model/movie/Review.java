package model.movie;

import model.Model;

import java.time.LocalDateTime;

/**
 * Contains information about the Movie being reviewed
 */
public class Review implements Model {
    /**
     * Contains datetime information on when the review is made
     */
    LocalDateTime timestamp;
    /**
     * rating of movie from 1 to 5
     */
    Integer rating;
    /**
     * review of the movie
     */
    String review = null;
    /**
     * name of the reviewer
     */
    String nameOfReviewer;
    /**
     * movie being reviewed
     */
    Movie movie;

    /**
     * Constructor for Review
     *
     * @param timestamp
     * @param rating
     * @param review
     * @param nameOfReviewer
     * @param movie
     */
    public Review(LocalDateTime timestamp, Integer rating, String review, String nameOfReviewer, Movie movie) {
        this.timestamp = timestamp;
        this.rating = rating;
        this.review = review;
        this.nameOfReviewer = nameOfReviewer;
        this.movie = movie;
    }

    /**
     * toString method for Review
     * @return
     */
    @Override
    public String toString() {
        return "Review{" +
                "timestamp=" + timestamp +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                ", nameOfReviewer='" + nameOfReviewer + '\'' +
                ", movie=" + movie +
                '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getNameOfReviewer() {
        return nameOfReviewer;
    }

    public void setNameOfReviewer(String nameOfReviewer) {
        this.nameOfReviewer = nameOfReviewer;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
