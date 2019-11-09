package controller;
import model.movie.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeMovieListing {

    public static List list = new ArrayList();
    public static List Create() {
        System.out.println("How many movies do you want to add?");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            //Movie movie = sc.nextLine();
            Movie movie=new Movie();
            list.add(movie);
        }
        System.out.println("You created a new movie list.");
        return list;
    }

    public static List Update(List list) {
        System.out.println("Do you want to add or delete a movie?");
        System.out.println("1. Add");
        System.out.println("2. Delete");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice == 1) {
            //Movie movie = sc.nextLine();
            Movie movie=new Movie();
            list.add(movie);
        }
        else {
            System.out.println("What movie do you want to delete (type in its number)?");
            System.out.println(list);
            int del = sc.nextInt();
            list.remove(del);
        }
        System.out.println("You updated the movie list.");
        return list;
    }

    public static List Remove(List list) {
        list.clear();
        System.out.println("You cleared the movie list.");
        return list;
    }
}
