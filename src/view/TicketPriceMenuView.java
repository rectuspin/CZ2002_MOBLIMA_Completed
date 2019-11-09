package view;

import model.AgeGroup;
import model.PublicHoliday;
import model.cinema.CinemaType;
import model.movie.MovieEnums;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static service.TicketPriceService.*;

//This class consist of all the sub menus views and holiday list interface for the system configuration settings

public class TicketPriceMenuView {


    public static void publicHolidayList(boolean isBack){
        /**This method is a menu that would show a list of public holiday dates that is added by the admin. If there is
         * no public holidays, a message "There is no public holidays currently..." would be displayed
         * @param isBack           An option to show the back option or to disable it
         */
        System.out.print("\n");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy");

        System.out.print("========================================================\n" +
                "|                 Public Holiday Dates                 |\n" +
                "========================================================\n");
        if (getPublicHolidayDates().size()== 0){
            System.out.print("| There is no public holidays currently...             |\n");
        }else {
            for (PublicHoliday publicHoliday : getPublicHolidayDates()) {
                System.out.format("| %-30s %21s |\n", publicHoliday.getPublicHolidayDate().format(dateFormat),
                        publicHoliday.getPublicHolidayName());
            }
        }
        System.out.println("========================================================");
        if(isBack) {
            System.out.println("[1 Back]");
            if (backOption()) {
                return;
            }
        }
    }

    public static void movieTypeChargesMenu(){
        /**This method is a menu that would show a list of different movie type that is added by the admin.
         */
        System.out.print("\n");
        System.out.print(   "===========================================================\n"+
                            "|                    Movie Type Charge                    |\n"+
                            "===========================================================\n");
        int backNo = 0;
        for (int i = 0; i < MovieEnums.MovieType.values().length; i++){
            System.out.format( "| %-54s  |\n", "(" + (i+1) + ") " + MovieEnums.MovieType.values()[i] +
                                " (Current Charges: " +
                                String.format("%.2f", MovieEnums.MovieType.values()[i].getTicketPrice()) + ")");
            backNo++;
        }
        System.out.format("| %-54s  |\n", "(" + (backNo+1) + ") " +
                            "Back");
        System.out.print("===========================================================\n");
    }

    public static void cinemaTypeChargesMenu(){
        /**This method is a menu that would show a list of different cinema type that is added by the admin.
         */
        System.out.print("\n");
        System.out.print(   "============================================================\n"+
                            "|                    Cinema Type Charge                    |\n"+
                            "============================================================\n");
        int backNo = 0;
        for (int i = 0; i < CinemaType.values().length; i++){
            System.out.format( "| %-55s  |\n","(" + (i+1) + ") " + CinemaType.values()[i] +
                                " (Current Charges: " +
                                String.format("%.2f", CinemaType.values()[i].getTicketPrice()) + ")");
            backNo++;
        }
        System.out.format("| %-55s  |\n", "(" + (backNo+1) + ") " +
                "Back");
        System.out.print("============================================================\n");
    }

    public static void citizenCategoryMenu(){
        /**This method is a menu that would show a list of different categories for special discount based on the
         * citizen age that is added by the admin.
         */
        System.out.print("\n");
        System.out.print(   "============================================================\n"+
                            "|                     Citizen Discount                     |\n"+
                            "============================================================\n");
        int backNo = 0;
        for (int i = 1; i < AgeGroup.values().length; i++){
            System.out.format( "| %-55s  |\n","(" + i + ") " + AgeGroup.values()[i] +
                    " (Current Discount: " +
                    String.format("%.2f", AgeGroup.values()[i].getTicketPrice()) + ")");
            backNo++;
        }
        System.out.format("| %-55s  |\n", "(" + (backNo+1) + ") " +
                "Back");
        System.out.print("============================================================\n");
    }


    //Display all the available public holidays settings in an interface
    public static void publicHolidayMenu(){
        /**This method is a menu that would show a list of options that would allow the admin to select and edit charges
         * or set/ remove public holiday dates
         * These options are:
         * 1. Listing the public holiday dates
         * 2. Adding a public holiday date
         * 3. Removing a public holiday date
         * 4. Setting the extra charges during public holidays
         * 5. Setting the extra charges during weekends
         */
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

    public static void systemConfigurationMenu(){
        /**This method is a menu that would show a list of different options for the admin to select such as:
         * 1. Setting the base price
         * 2. Setting the movie type charges
         * 3. Setting the cinema type charges
         * 4. Setting the charges for different citizen that are different age
         * 5. Setting public holiday dates/ public holiday & weekend charges
         */
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
                            "(1) Set Base Price (Current Price: " + String.format("%.2f", getBasePrice()) + ")",
                            "(2) Movie Type Charges",
                            "(3) Cinema Type Charges",
                            "(4) Special Citizen Discount" ,
                            "(5) Public Holiday/ Weekend Settings",
                            "(6) Back"
                            );
    }

    //Back option to allow user to go back to previous interface (Used in public holiday list interface)
    public static boolean backOption(){
        /**This method is used to allow the menu to go back to the previous interface. It would ask the user if they
         * would like to go back and if an invalid input is provided it will keep asking the user for the correct input
         * and an error message would be shown
         * @return true if user inputted 1
         * @return false if user inputted something other than 1
         */
        int opt;
        while (true) {
            Scanner in = new Scanner(System.in);
            try{
                System.out.print("Option: ");
                opt = in.nextInt();
                if (opt == 1) {   //Goes back to the previous interface
                    return true;
                }
                else{
                    System.out.println("[System: Invalid Input]");
                }
            } catch(Exception e){
                System.out.println("[System: Invalid Input]");
            }
        }
    }
}
