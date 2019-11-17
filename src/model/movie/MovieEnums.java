package model.movie;

import model.Model;

/**
 * Class containing enums for Movies
 */
public class MovieEnums implements Model {
    public enum MovieRating {
        G("General"),
        PG("Parental Guidance"),
        PG13("Parental Guidance 13 years and below"),
        NC16("No children under 16"),
        M18("Mature 18"),
        R21("Restricted 21");

        private String description;

        MovieRating(String description) {
            this.description = description;
        }

        /**
         * This method would return the description of each movie rating
         *
         * @return The description of each movie rating
         */
        public String toString() {

            return description;
        }
    }

    public enum MovieStatus {
        ComingSoon(0),
        Preview(1),
        NowShowing(2),
        NotShowing(3);

        private final int statusCode;

        MovieStatus(int statusCode) {
            this.statusCode = statusCode;
        }
    }

    public enum MovieType {
        THREE_DIMENSION("3D", 14),
        DIGITAL("Digital", 12),
        IMAX("IMAX", 16);


        private String description;
        protected double prices;

        MovieType(String description, double prices) {

            this.description = description;
            this.prices = prices;
        }

        /**This method would return the extra charges for the specific movie type
         * @return The extra charges for the specific movie type
         */
        public double getTicketPrice() {

            return prices;
        }

        /**This method would set the extra charges for the specific movie type
         * @param prices         The extra charges for the specific movie type
         */
        public void setTicketPrice(double prices) {

            this.prices = prices;
        }
    }

    public enum Subtitle {
        English,
        Mandarin,
        Malay,
        Tamil,
        None
    }

    public enum Language {
        ENGLISH,
        MANDARIN,
        MALAY,
        TAMIl
    }
}
