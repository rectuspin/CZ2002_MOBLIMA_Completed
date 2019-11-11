/*
package test;

import controller.DBController;

import java.io.IOException;
import java.text.ParseException;

import static view.TicketPriceView.ticketPriceView;

public class SetTicketChargesTest {
    public static void main(String[] args) throws ParseException, IOException {
        DBController DBService = DBController.getInstance();
     */
/*   DBService.createDB("CinemaTypePriceDB");
        DBService.createDB("MovieTypePriceDB");
        DBService.createDB("TicketPriceInfoDB");*//*

        //Implement it at the code where the program is just starting up
        try {
            DBService.loadTicketPriceInfoDatabase();
        }catch(Exception e){
            ticketPriceView();
            DBService.saveTicketPriceInfoDatabase();
            return;
        }

        //Implement it at the Configure System Settings option at the admin module
        ticketPriceView();

        //Implement it at the code where the program is about to exit
        DBService.saveTicketPriceInfoDatabase();
    }
}
*/
