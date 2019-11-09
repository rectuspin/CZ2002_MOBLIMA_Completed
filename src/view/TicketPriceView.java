package view;

import model.AgeGroup;
import model.cinema.CinemaType;
import model.movie.MovieEnums;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static service.TicketPriceService.*;
import static view.TicketPriceMenuView.*;


public class TicketPriceView {

    //This is the main control view
    public static void ticketPriceView() throws ParseException {
        /**This method is basically an interface that consist of a menu which would accept an input from the admin based
         * one the available option which allows the user to set what they want to set. The available options are:
         * 1. Setting the base price
         * 2. Setting the movie type charges
         * 3. Setting the cinema type charges
         * 4. Setting the charges for different citizen that are different age
         * 5. Setting public holiday dates/ public holiday & weekend charges
         * @throws ParseException
         */
        while (true) {
            TicketPriceMenuView.systemConfigurationMenu();
            try {
                System.out.print("Option: ");
                Scanner in = new Scanner(System.in);
                int opt = in.nextInt();
                if (opt == 1){
                    System.out.println("> Set Base Price: ");
                    System.out.print("   Enter Price: ");
                    double price = in.nextDouble();
                    setBasePrice(price);
                }
                else if (opt == 2) {
                    System.out.print("\n");
                    movieTypeSettings();
                } else if (opt == 3) {
                    System.out.print("\n");
                    cinemaTypeSettings();
                } else if (opt == 4) {
                    citizenCategorySettings();
                } else if (opt == 5) {
                    publicHolidaySettings();
                } else if (opt == 6) {
                    return;
                }
            }catch (Exception e){
                System.out.println("[System: Invalid Input]");
            }
        }
    }

    //Movie Type Settings
    //Settings function for different movie type charges
    public static void movieTypeSettings(){
        /**This method is basically an interface that consist of a menu which would accept an input from the admin based
         * one the available options. A list of different movie types would be shown here and selecting it would allow
         * the admin to change the charges for that specific movie type.
         */
        while (true) {
            movieTypeChargesMenu();   //Display each movie type to set additional charges
            Scanner in = new Scanner(System.in);
            System.out.print("Option: ");
            int opt = in.nextInt();   //Select an option of which movie type to be selected
            if (0 < opt && opt <= MovieEnums.MovieType.values().length) {
                System.out.println("> Movie Type Charges: " + MovieEnums.MovieType.values()[opt - 1]);
                System.out.print("   New Charges: ");
                double charges = in.nextDouble();   //Input the amount for the extra charges
                setMovieTypeCharges(opt, charges);   //Sets the charges for each movie type
                System.out.println("[System: Movie Type Charges Set Successfully]");
            }
            else if (opt == MovieEnums.MovieType.values().length+1){
                return;
            }
            else{
                System.out.println("[System: Invalid Input]");
            }
        }
    }

    //Cinema Type Settings
    //Settings function for different cinema type charges
    public static void cinemaTypeSettings(){
        /**This method is basically an interface that consist of a menu which would accept an input from the admin based
         * one the available options. A list of different cinema types would be shown here and selecting it would allow
         * the admin to change the charges for that specific cinema type.
         */
        while (true) {
            cinemaTypeChargesMenu();   //Display each cinema type to set additional charges
            Scanner in = new Scanner(System.in);
            System.out.print("Option: ");
            int opt = in.nextInt();   //Select an option of which cinema type to be selected
            if (0 < opt && opt <= CinemaType.values().length) {
                System.out.println("> Cinema Type Charges: " + CinemaType.values()[opt - 1]);
                System.out.print("   New Charges: ");
                double charges = in.nextDouble();   //Input the amount for the extra charges
                setCinemaTypeCharges(opt, charges);   //Sets the charges for each cinema type
                System.out.println("[System: Cinema Type Charges Set Successfully]");
            }
            else if (opt == CinemaType.values().length+1){
                return;
            }
            else{
                System.out.println("[System: Invalid Input]");
            }
        }
    }

    //Age Group Settings
    //Settings function for different age group charges
    public static void citizenCategorySettings(){
        /**This method is basically an interface that consist of a menu which would accept an input from the admin based
         * one the available options. A list of categories that applies to different citizen for special discounts would
         * be shown here and selecting it would allow the admin to change the charges for that specific category. (e.g.
         * Student Price, Senior Citizen, Child)
         */
        while (true) {
            citizenCategoryMenu();   //Display each citizen category to set additional charges
            Scanner in = new Scanner(System.in);
            System.out.print("Option: ");
            int opt = in.nextInt();   //Select an option of which  citizen category to be selected
            if (0 < opt && opt <= AgeGroup.values().length-1) {
                System.out.println("> Special Citizen Discount: " + AgeGroup.values()[opt]);
                System.out.print("   Discount Amount: ");
                double discount = in.nextDouble();   //Input the amount for the extra charges
                setAgeGroupCharges(opt, discount);   //Sets the charges for each citizen category
                System.out.println("[System: Special Citizen Discounts Set Successfully]");
            }
            else if (opt == AgeGroup.values().length){
                return;
            }
            else{
                System.out.println("[System: Invalid Input]");
            }
        }
    }

    //Public Holiday Settings
    //Settings function for adding public holiday dates
    public static void publicHolidaySettings() throws ParseException {
        /**This method is basically an interface that consist of a menu which would accept an input from the admin based
         * one the available options. A list of options would be shown here such as adding public holiday dates and
         * setting the charges for public holiday and weekend.
         */
        Scanner in = new Scanner(System.in);
        int opt;
        while (true){
            publicHolidayMenu();   //Display the public holiday settings menu
            System.out.print("Option: ");
            opt = in.nextInt();   //Takes an input from user of selected option
            if (opt == 1){
                TicketPriceMenuView.publicHolidayList(true);
            }
            else if (opt == 2){
                //Takes date as a string input
                try {
                    System.out.println("> Add a Public Holiday");
                    System.out.print("   Public Holiday's title: ");
                    String name = in.next();
                    System.out.print("   Public Holiday's date (e.g. DD/MM/YYYY): ");
                    String Date = in.next();
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(Date, dateFormat);            //Adds the date into the array for reference
                    if (addPublicHolidayDates(name, date)) {
                        System.out.println("[System: Public Holiday has been added!]");
                    }else{
                        System.out.println("[System: Duplicated Date detected!]");
                    }
                    TicketPriceMenuView.publicHolidayList(true);
                }catch (Exception e){
                    System.out.println("[System: Invalid Input]");
                }
            }
            else if (opt == 3){
                try {
                    TicketPriceMenuView.publicHolidayList(false);
                    System.out.println("> Remove a Public Holiday");
                    System.out.print("   Public Holiday's date (e.g. DD/MM/YYYY): ");
                    String Date = in.next();
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(Date, dateFormat);            //Adds the date into the array for reference
                    if (removePublicHolidayDates(date)){
                        System.out.println("[System: Public Holiday has been removed!]");
                    }else{
                        System.out.println("[System: Date not found!]");
                    }
                    TicketPriceMenuView.publicHolidayList(true);
                }catch (Exception e){
                    System.out.println("[System: Invalid Input]");
                }
            }
            else if (opt == 4){
                System.out.println("> Set Public Holiday Charges [Current Charges: " + String.format("%.2f", getPublicHolidayCharges()) + "]");
                System.out.print("   New Charges: ");
                double charges = in.nextDouble();
                setPublicHolidayCharges(charges);
                System.out.println("[System: Public Holiday Charges Set Successfully]");
            }
            else if (opt == 5){
                System.out.println("> Set Weekend Charges [Current Charges: " + String.format("%.2f", getWeekendCharges()) + "]");
                System.out.print("   New Charges: ");
                double charges = in.nextDouble();
                setWeekendCharges(charges);
                System.out.println("[System: Weekend Charges Set Successfully]");
            }
            else if (opt == 6){
                return;   //Goes to previous page
            }
        }
    }
}
