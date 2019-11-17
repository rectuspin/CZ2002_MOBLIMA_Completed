package view;

import model.AgeGroup;
import model.PublicHoliday;
import model.cinema.CinemaType;
import model.movie.MovieEnums;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static service.TicketPriceService.*;

//This class consist of all the sub menus views and holiday list interface for the system configuration settings

/**
 * Class to display the ticket price menu
 */
public class TicketPriceMenuView {

    /**
     * This method is a menu that would show a list of public holiday dates that is added by the admin. If there is
     * no public holidays, a message "There is no public holidays currently..." would be displayed
     *
     * @param isBack An option to show the back option or to disable it
     */
    public static void publicHolidayList(boolean isBack) {

        System.out.print("\n");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy");


        System.out.print("========================================================\n" +
                "|                 Public Holiday Dates                 |\n" +
                "========================================================\n");

        if (getPublicHolidayDates().size()== 0){
            System.out.print("| There is no public holidays currently...             |\n");
        }else {
            for (PublicHoliday publicHoliday : getPublicHolidayDates()) {
                System.out.format("| %-27s | %-22s |\n", publicHoliday.getPublicHolidayDate().format(dateFormat),
                        publicHoliday.getPublicHolidayName());
            }
        }

        if(isBack) {
            System.out.println( "|                                                      |");
            System.out.println( "| (1) Back                                             |");
            System.out.println( "========================================================");
            if (backOption()) {
                return;
            }
        }
        System.out.println( "========================================================");

    }

    /**
     * This method is a menu that would show a list of different movie type that is added by the admin.
     */
    public static void movieTypeChargesMenu(){

        System.out.print("\n");
        System.out.print(   "===================================================\n"+
                "|     Movie Type                  | Extra Charges |\n"+
                "===================================================\n");
        int backNo = 0;
        for (int i = 0; i < MovieEnums.MovieType.values().length; i++){
            System.out.format( "| %-30s  | %9s     |\n", "(" + (i+1) + ") " + MovieEnums.MovieType.values()[i],
                    String.format("%.2f", MovieEnums.MovieType.values()[i].getTicketPrice()));
            backNo++;
        }

        System.out.format("| (" + (backNo+1) + ") Back                        |               |\n");
        System.out.print("===================================================\n");
    }

    /**
     * This method is a menu that would show a list of different cinema type that is added by the admin.
     */
    public static void cinemaTypeChargesMenu(){

        System.out.print("\n");
        System.out.print(   "===================================================\n"+
                "|     Cinema Type                 | Extra Charges |\n"+
                "===================================================\n");
        int backNo = 0;
        for (int i = 0; i < CinemaType.values().length; i++){
            System.out.format( "| %-30s  | %9s     |\n", "(" + (i+1) + ") " + CinemaType.values()[i],
                    String.format("%.2f", CinemaType.values()[i].getTicketPrice()));
            backNo++;
        }

        System.out.format("| (" + (backNo+1) + ") Back                        |               |\n");
        System.out.print("===================================================\n");
    }

    /**
     * This method is a menu that would show a list of different categories for special discount based on the
     * citizen age that is added by the admin.
     */
    public static void citizenCategoryMenu(){

        System.out.print("\n");
        System.out.print(   "===================================================\n"+
                "|     Citizen Type                |    Discount   |\n"+
                "===================================================\n");
        int backNo = 0;
        for (int i = 1; i < AgeGroup.values().length; i++){
            System.out.format( "| %-30s  | %9s     |\n", "(" + (i) + ") " + AgeGroup.values()[i],
                    String.format("%.2f", AgeGroup.values()[i].getTicketPrice()));
            backNo++;
        }

        System.out.format("| (" + (backNo+1) + ") Back                        |               |\n");
        System.out.print("===================================================\n");
    }


    /**
     * This method is a menu that would show a list of options that would allow the admin to select and edit charges
     * or set/ remove public holiday dates
     * These options are:
     * 1. Listing the public holiday dates
     * 2. Adding a public holiday date
     * 3. Removing a public holiday date
     * 4. Setting the extra charges during public holidays
     * 5. Setting the extra charges during weekends
     */
    public static void publicHolidayMenu(){

        System.out.print("\n");
        System.out.format(  "=================================================================\n"+
                        "|               Public Holiday / Weekend Settings               |\n"+
                        "=================================================================\n"+
                        "| %-61s |\n" +
                        "| %-61s |\n" +
                        "| %-61s |\n" +
                        "| %-61s |\n" +
                        "| %-61s |\n" +
                        "| %-61s |\n" +
                        "=================================================================\n",
                "(1) List Public Holidays" ,
                "(2) Add a Public Holiday" ,
                "(3) Remove a Public Holiday" ,
                "(4) Set Public Holiday Charges (Current Charges: SGD " +
                        String.format("%.2f", getPublicHolidayCharges()) + ")" ,
                "(5) Set Weekend Charges (Current Charges: SGD " +
                        String.format("%.2f", getWeekendCharges()) + ")" ,
                "(6) Back");
    }

    /**
     * This method is a menu that would show a list of different options for the admin to select such as:
     * 1. Setting the base price
     * 2. Setting the movie type charges
     * 3. Setting the cinema type charges
     * 4. Setting the charges for different citizen that are different age
     * 5. Setting public holiday dates/ public holiday and weekend charges
     */
    public static void systemConfigurationMenu(){

        System.out.print("\n");
        System.out.format(  "===========================================================\n"+
                        "|                Configure System Settings                |\n"+
                        "===========================================================\n"+
                        "| %-55s |\n" +
                        "| %-55s |\n" +
                        "| %-55s |\n" +
                        "| %-55s |\n" +
                        "| %-55s |\n" +
                        "| %-55s |\n" +
                        "===========================================================\n",
                "(1) Set Base Price (Current Price: SGD " + String.format("%.2f", getBasePrice()) + ")",
                "(2) Movie Type Charges",
                "(3) Cinema Type Charges",
                "(4) Special Citizen Discount" ,
                "(5) Public Holiday/ Weekend Settings",
                "(6) Back"
        );
    }

    /**This method is used to allow the menu to go back to the previous interface. It would ask the user if they
     * would like to go back and if an invalid input is provided it will keep asking the user for the correct input
     * and an error message would be shown
     * @return true if user inputted 1
     * @return false if user inputted something other than 1
     */
    public static boolean backOption(){

        int opt;
        while (true) {
            Scanner in = new Scanner(System.in);
            try{
                System.out.print("Option: ");
                opt = in.nextInt();
                if (opt == 1) {   //Goes back to the previous interface
                    return true;
                } else{
                    System.out.println("\n[System: Invalid Input]\n");
                }
            } catch(Exception e){
                System.out.println("\n[System: Invalid Input]\n");
            }
        }
    }
}
