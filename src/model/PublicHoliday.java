package model;

import java.io.Serializable;
import java.time.LocalDate;

public class PublicHoliday implements Serializable {
    private String publicHolidayName;
    private LocalDate publicHolidayDate;


    public PublicHoliday(String publicHolidayName, LocalDate publicHolidayDate) {

        /**This is a constructor for public holiday
         * @param publicHolidayName         The name for the public holiday
         * @param publicHolidayDate         The date for the public holiday
         */
        this.publicHolidayName = publicHolidayName;
        this.publicHolidayDate = publicHolidayDate;
    }


    public String getPublicHolidayName() {
        /**This method is used to get and return the name of the public holiday
         * @return The public holiday's name
         */
        return publicHolidayName;
    }


    public LocalDate getPublicHolidayDate() {
        /**This method is used to get and return the date of the public holiday
         * @return The public holiday's date
         */
        return publicHolidayDate;
    }
}
