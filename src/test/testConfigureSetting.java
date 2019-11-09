package test;

import controller.ChangeMovieListing;
import controller.ChangeShowtimeListing;

import java.util.Scanner;

public class testConfigureSetting {
    public static void main(String[] args) {
        int choice;
        ChangeMovieListing changeMovieListing=new ChangeMovieListing();
        ChangeShowtimeListing changeShowtimeListing=new ChangeShowtimeListing();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("(1) Change Movie Listing");
            System.out.println("(2) Change Showtime Listing");
            System.out.println("\n Enter the number of your choice:");

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("(1) Create Movie Listing");
                    System.out.println("(2) Update Movie Listing");
                    System.out.println("(3) Remove Movie Listing");
                    System.out.println("\n Enter the number of your choice:");
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            changeMovieListing.Create();
                            break;
                        case 2:
                            changeMovieListing.Update(changeMovieListing.list);
                            break;
                        case 3:
                            changeMovieListing.Remove(changeMovieListing.list);
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    System.out.println("(1) Create Showtime Listing");
                    System.out.println("(2) Update Showtime Listing");
                    System.out.println("(3) Remove Showtime Listing");
                    System.out.println("\n Enter the number of your choice:");

                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            changeShowtimeListing.Create();
                            break;
                        case 2:
                            changeShowtimeListing.Update(changeShowtimeListing.list);
                            break;
                        case 3:
                            changeShowtimeListing.Remove(changeMovieListing.list);
                            break;
                        default:
                            break;
                    }
            }
        }while(true);
    }
}
