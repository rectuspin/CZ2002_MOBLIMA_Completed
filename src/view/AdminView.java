package view;

import java.util.Scanner;

import static view.TicketPriceView.ticketPriceView;

public class AdminView {
    public static void adminView() {
        Scanner sc = new Scanner(System.in);
        int choice, choice_main;
        //main page
        do {
            System.out.println( "\n======================================\n" +
                                "|            Admin Setting           |\n" +
                                "======================================\n" +
                                "| (1) Configure setting              |\n" +
                    "| (2) Movie listings                 |\n" +
                                "| (3) Change cinema showtime         |\n"+
                                "| (4) Go back                        |\n" +
                                "======================================");
            System.out.print("Option: ");
            choice_main = sc.nextInt();
            switch (choice_main) {
                case 1:
                    //configure setting
                    try {
                        ticketPriceView();
                    }catch (Exception e){
                        System.out.println("[System: There is an error in displaying the admin panel]");
                    }
                    break;
                case 2:
                    //change movie listing
                    do {
                        System.out.println( "\n======================================\n" +
                                            "|        Movie Listing Setting       |\n" +
                                            "======================================\n" +
                                            "| (1) Create Movie listing           |\n" +
                                            "| (2) Update Movie listing           |\n" +
                                            "| (3) Remove Movie listing           |\n"+
                                            "| (4) Go back                        |\n" +
                                            "======================================");
                        System.out.print("Option: ");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                ChangeMovieListingView.create();
                                break;
                            case 2:
                                ChangeMovieListingView.update();
                                break;
                            case 3:
                                ChangeMovieListingView.remove();
                                break;
                            default:
                                break;
                        }

                    } while (choice < 4);
                    break;
                case 3:
                    //Change cinema showtimes
                    ChangeShowtimeListingView changeShowtimeListingView = new ChangeShowtimeListingView();
                    do {
                        System.out.println( "\n=======================================\n" +
                                            "|       Showtime Listing Setting      |\n" +
                                            "=======================================\n" +
                                            "| (1) Create Showtime listing         |\n" +
                                            "| (2) Update Showtime listing         |\n" +
                                            "| (3) Remove Showtime listing         |\n"+
                                            "| (4) Go back                         |\n" +
                                            "=======================================");
                        System.out.print("Option: ");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                ChangeShowtimeListingView.create();
                                break;
                            case 2:
                                ChangeShowtimeListingView.update();
                                break;
                            case 3:
                              //  ChangeShowtimeListing.Remove();
                                break;
                            default:
                                break;
                        }
                    } while (choice < 4);
                    break;
            }
        } while (choice_main < 4);
    }
}