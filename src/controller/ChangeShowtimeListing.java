package controller;
import model.cinema.ShowTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeShowtimeListing {

    public static List list = new ArrayList();
    public static List Create() {
        System.out.println("How many showtimes do you want to add?");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
           // Showtime showtime = sc.nextLine();
            ShowTime showTime=new ShowTime();
           list.add(showTime);
        }
        System.out.println("You created a new showtime list.");
        return list;
    }

    public static List Update(List list) {
        System.out.println("Do you want to add or delete a showtime?");
        System.out.println("1. Add");
        System.out.println("2. Delete");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice == 1) {
            //Showtime showtime = sc.nextLine();
            ShowTime showTime=new ShowTime();
            list.add(showTime);
        }
        else {
            System.out.println("What showtime do you want to delete (type in its number)?");
            System.out.println(list);
            int del = sc.nextInt();
            list.remove(del);
        }
        System.out.println("You updated the showtime list.");
        return list;
    }

    public static List Remove(List list) {
        list.clear();
        System.out.println("You cleared the showtime list.");
        return list;
    }
}
