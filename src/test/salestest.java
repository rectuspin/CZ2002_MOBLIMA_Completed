package test;

import controller.DBController;
import model.AgeGroup;
import model.account.Customer;
import model.cinema.*;
import model.movie.Movie;
import model.transaction.Booking;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.*;
import java.util.Map.Entry;

public class salestest {
    public static void main(String[] args) throws IOException, ParseException {
        DBController DBService = new DBController();
        //DBService.loadTicketPriceInfoDatabase();
        DBController dbController = new DBController();

        Customer customer = new Customer("asdfsa", "asdfsaf", 3241234);
        Seat[] seat = new Seat[0];
        ArrayList<String> cast = new ArrayList<String>();
        cast.add("Robert Downey Jr.");
        cast.add("Terrence Howard");
        cast.add("Jeff Bridges");
        cast.add("Shaun Toub");
        cast.add("Gwyneth Paltrow");
        String synopsis = "Tony Stark, who has inherited the defense contractor Stark Industries from his father Howard Stark, is in war-torn Afghanistan with his friend and military liaison, Lieutenant Colonel James Rhodes, to demonstrate the new \"Jericho\" missile. After the demonstration, the convoy is ambushed and Stark is critically wounded by a missile used by the attackers: one of his company's own. He is captured and imprisoned in a cave by a terrorist group called the Ten Rings. Yinsen, a fellow captive doctor, implants an electromagnet into Stark's chest to keep the shrapnel shards that wounded him from reaching his heart and killing him. Ten Rings leader Raza offers Stark freedom in exchange for building a Jericho missile for the group, but he and Yinsen know that Raza will not keep his word. \n" +
                "Stark and Yinsen secretly build a small, powerful electric generator called an arc reactor to power Stark's electromagnet and a prototype suit of powered armor to aid in their escape. Although they keep the suit hidden almost to completion, the Ten Rings discover their hostages' intentions and attack the workshop. Yinsen sacrifices himself to divert them while the suit is completed. The armored Stark battles his way out of the cave to find the dying Yinsen, then burns the Ten Rings' weapons in anger and flies away, crashing in the desert and destroying the suit. After being rescued by Rhodes, Stark returns home and announces that his company will cease manufacturing weapons. Obadiah Stane, his father's old partner and the company's manager, advises Stark that this may ruin Stark Industries and his father's legacy. In his home workshop, Stark builds a sleeker, more powerful version of his improvised armor suit as well as a more powerful arc reactor for his chest. Personal assistant Pepper Potts places the original reactor inside a small glass showcase. Though Stane requests details, a suspicious Stark decides to keep his work to himself. \n" +
                "At a charity event held by Stark Industries, reporter Christine Everhart informs Stark that his company's weapons were recently delivered to the Ten Rings and are being used to attack Yinsen's home village, Gulmira. Stark dons his new armor and flies to Afghanistan, where he saves the villagers. While flying home, Stark is attacked by two fighter jets. He reveals his secret identity to Rhodes over the phone in an attempt to end the attack. Meanwhile, the Ten Rings gather the pieces of Stark's prototype suit and meet with Stane, who has been trafficking arms to criminals worldwide and has staged a coup to replace Stark as Stark Industries' CEO by hiring the Ten Rings to kill him. He subdues Raza and has the rest of the group killed. Stane has a massive new suit reverse engineered from the wreckage. Seeking to track his company's illegal shipments, Stark sends Potts to hack into its database. She discovers that Stane hired the Ten Rings to kill Stark, but the group reneged. Potts meets with Agent Phil Coulson of S.H.I.E.L.D., an intelligence agency, to inform him of Stane's activities. \n" +
                "Stane's scientists cannot duplicate Stark's miniaturized arc reactor, so Stane ambushes Stark at his home and takes the one from his chest. Stark manages to get to his original reactor to replace it. Potts and several S.H.I.E.L.D. agents attempt to arrest Stane, but he dons his suit and attacks them. Stark fights Stane but is outmatched without his new reactor to run his suit at full capacity. The fight carries Stark and Stane to the top of the Stark Industries building, and Stark instructs Potts to overload the large arc reactor powering the building. This unleashes a massive electrical surge that causes Stane and his armor to fall into the exploding reactor, killing him. The next day, at a press conference, Stark defies suggestions from S.H.I.E.L.D. and publicly admits to being \"Iron Man.\" \n" +
                "In a post-credits scene, S.H.I.E.L.D. Director Nick Fury visits Stark at home, telling him that Iron Man is not \"the only superhero in the world\", and explaining that he wants to discuss the \"Avenger Initiative\". ";

        Movie movie = new Movie("Iron Man", "Jon Favreau", cast, synopsis);
        Movie movie1 = new Movie("Iron Man 2", "Jon Favreau", cast, synopsis);
        Movie movie2 = new Movie("Iron Man 3", "Jon Favreau", cast, synopsis);
        Movie movie3 = new Movie("Iron Man 4", "Jon Favreau", cast, synopsis);
        Movie movie4 = new Movie("Iron Man 5", "Jon Favreau", cast, synopsis);
        Movie movie5 = new Movie("Iron Man 6", "Jon Favreau", cast, synopsis);
        Movie movie6 = new Movie("Iron Man 7", "Jon Favreau", cast, synopsis);
        Movie movie7 = new Movie("Iron Man 8", "Jon Favreau", cast, synopsis);

        Cineplex cineplex  = new Cineplex("JURONG POINT");
        Cinema cinema = new Cinema("HALL 1");
        cinema.setCinemaType(CinemaType.PLATINUM_MOVIE_SUITES);
        ShowTime showTime = new ShowTime(movie,cinema, cineplex);
        ShowTime showTime1 = new ShowTime(movie1,cinema, cineplex);
        ShowTime showTime2 = new ShowTime(movie2,cinema, cineplex);
        ShowTime showTime3 = new ShowTime(movie3,cinema, cineplex);
        ShowTime showTime4 = new ShowTime(movie4,cinema, cineplex);
        ShowTime showTime5 = new ShowTime(movie5,cinema, cineplex);
        ShowTime showTime6 = new ShowTime(movie6,cinema, cineplex);
        ShowTime showTime7 = new ShowTime(movie7,cinema, cineplex);


        Booking booking = new Booking(LocalDate.now(), LocalTime.now(), showTime, seat, customer);
        Booking booking1 = new Booking(LocalDate.now(), LocalTime.now(), showTime1, seat, customer);
        Booking booking2 = new Booking(LocalDate.now(), LocalTime.now(), showTime2, seat, customer);
        Booking booking3 = new Booking(LocalDate.now(), LocalTime.now(), showTime3, seat, customer);
        Booking booking4 = new Booking(LocalDate.now(), LocalTime.now(), showTime4, seat, customer);
        Booking booking5 = new Booking(LocalDate.now(), LocalTime.now(), showTime5, seat, customer);
        Booking booking6 = new Booking(LocalDate.now(), LocalTime.now(), showTime6, seat, customer);
        Booking booking7 = new Booking(LocalDate.now(), LocalTime.now(), showTime7, seat, customer);

        booking.setCinema(cinema);
        booking.makeBooking(AgeGroup.STUDENT_PRICE);

        booking.getPrice(LocalDate.now());
        dbController.addSales(booking);
       // BookingMenuView.bookingMenu(booking);
        System.out.println(booking.getPrice(LocalDate.now()));

        int i;
        for(i=0;i<8;i++){booking.makeBooking();}
        for(i=0;i<7;i++){booking1.makeBooking();}
        for(i=0;i<4;i++){booking2.makeBooking();}
        for(i=0;i<5;i++){booking3.makeBooking();}
        for(i=0;i<6;i++){booking4.makeBooking();}
        for(i=0;i<3;i++){booking5.makeBooking();}
        for(i=0;i<1;i++){booking6.makeBooking();}
        for(i=0;i<2;i++){booking7.makeBooking();}

        dbController.addSales(booking);
        dbController.addSales(booking1);
        dbController.addSales(booking2);
        dbController.addSales(booking3);
        dbController.addSales(booking4);
        dbController.addSales(booking5);
        dbController.addSales(booking6);
        dbController.addSales(booking7);

        HashMap<String, Integer> tempTopMovies = new HashMap<String, Integer>();
        tempTopMovies=dbController.getSales();
        Object[] a = tempTopMovies.entrySet().toArray();
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o2).getValue()
                        .compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });
        /*
        for (Object e : a) {
            System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
                    + ((Map.Entry<String, Integer>) e).getValue());
        }

         */

        List<Entry<String, Integer>> top5Movies = findGreatest(tempTopMovies, 5);
        Collections.reverse(top5Movies);
        System.out.println("Top 5 Movies:");
        for (Entry<String, Integer> entry : top5Movies)
        {
            System.out.println(entry.getKey());
        }

        //System.out.println(dbController.getSalesFigure("Iron Man"));
        //System.out.println(dbController.getSales());

    }


    private static <K, V extends Comparable<? super V>> List<Entry<K, V>> findGreatest(Map<K, V> map, int n)
    {
        Comparator<? super Entry<K, V>> comparator =
                new Comparator<Entry<K, V>>()
                {
                    @Override
                    public int compare(Entry<K, V> e0, Entry<K, V> e1)
                    {
                        V v0 = e0.getValue();
                        V v1 = e1.getValue();
                        return v0.compareTo(v1);
                    }
                };
        PriorityQueue<Entry<K, V>> highest =
                new PriorityQueue<Entry<K,V>>(n, comparator);
        for (Entry<K, V> entry : map.entrySet())
        {
            highest.offer(entry);
            while (highest.size() > n)
            {
                highest.poll();
            }
        }

        List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
        while (highest.size() > 0)
        {
            result.add(highest.poll());
        }
        return result;
    }

}
