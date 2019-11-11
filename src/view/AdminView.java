package view;

import controller.ChangeMovieListing;
import controller.ChangeShowtimeListing;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AdminView {


    public static void adminMainMenuView() {
        Scanner sc = new Scanner(System.in);
        int choice, temp;
        int ticket_price;
        String input_id, admin_id = null, user_id = null;
        String input_pw, admin_pw = null, user_pw = null;

        boolean userLoginFlag = false;
        boolean sameUserID = false;

        HashMap<String, String> mapInFile = new HashMap<String, String>();


        String currentLine;

        //main page
        do {
            System.out.println("(1) Login");                        // admin login
            System.out.println("(2) Create an account");            // create account
            System.out.println("(3) ");                             // additional functionalities
            System.out.println("(4) ");
            System.out.println("(5) ");
            System.out.println("(6) ");
            System.out.println("(7) Exit");                         // exit
            System.out.println("\n Enter the number of your choice:");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    do {
                        System.out.println("Type 1 to go back to Main Page or type any other number to continue to log in");
                        temp = sc.nextInt();
                        if (1 == temp)
                            break;

                        //Log in
                        String temp_s = sc.nextLine();
                        System.out.printf("Enter ID:");
                        input_id = sc.nextLine();
                        System.out.printf("Enter password:");
                        input_pw = sc.nextLine();
                        try {
                            FileReader frStream = new FileReader("admin_data.txt");
                            BufferedReader brStream = new BufferedReader(frStream);

                            FileInputStream fis = new FileInputStream("login_data.txt");
                            Scanner fileRead = new Scanner(fis);
                            admin_id = brStream.readLine();
                            admin_pw = brStream.readLine();

                            //read data from file line by line:
                            while (fileRead.hasNextLine()) {
                                currentLine = fileRead.nextLine();
                                //now tokenize the currentLine:
                                StringTokenizer st = new StringTokenizer(currentLine, "=", false);
                                //put tokens ot currentLine in map
                                mapInFile.put(st.nextToken(), st.nextToken());
                                //print All data in MAP

                            }

                            fis.close();

                            //For checking the user data
                            for (Map.Entry<String, String> m : mapInFile.entrySet()) {
                                System.out.println(m.getKey() + " : " + m.getValue());
                                if (input_id.equals(m.getKey()) && (input_pw.equals(m.getValue())))
                                    userLoginFlag = true;
                            }
                            //for checking the admin data
                            System.out.println("admin id: " + admin_id + " / admin pw:" + admin_pw);

                            brStream.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("File Read Error!" + e.getMessage());
                            System.exit(0);
                        } catch (IOException e) {
                            System.out.println("IO Error!" + e.getMessage());
                            e.printStackTrace();
                            System.exit(0);
                        }

                        //Admin Login
                        if ((0 == input_id.compareTo(admin_id)) && (0 == input_pw.compareTo(admin_pw))) {
                            do {
                                System.out.println("**Admin Account Logged In!**\n");

                                System.out.println("(1)Configure setting");
                                System.out.println("(2)Change movie listing ");
                                System.out.println("(3)Change cinema showtimes");
                                System.out.println("(4)Go back");

                                choice = sc.nextInt();
                                switch (choice) {
                                    case 1:
                                        //configure setting
                                        break;
                                    case 2:
                                        //change movie listing

                                        ChangeMovieListing changeMovieListing = new ChangeMovieListing();
                                        do {
                                            System.out.println("(1)Create Movie listing");
                                            System.out.println("(2)Update Movie listing");
                                            System.out.println("(3)Remove Movie listing");
                                            System.out.println("(4)Go back");
                                            switch (choice) {
                                                case 1:
                                                    ChangeMovieListing.Create();
                                                    break;
                                                case 2:
                                                    ChangeMovieListing.Update(ChangeMovieListing.list);
                                                    break;
                                                case 3:
                                                    ChangeMovieListing.Remove(ChangeMovieListing.list);
                                                    break;
                                                default:
                                                    break;
                                            }
                                            choice = sc.nextInt();
                                        } while (choice < 3);
                                        break;
                                    case 3:
                                        //Change cinema showtimes
                                        ChangeShowtimeListing changeShowtimeListing = new ChangeShowtimeListing();
                                        do {
                                            System.out.println("(1)Create Showtime listing");
                                            System.out.println("(2)Update Showtime listing");
                                            System.out.println("(3)Remove Showtime listing");
                                            System.out.println("(4)Go back");
                                            switch (choice) {
                                                case 1:
                                                    ChangeShowtimeListing.Create();
                                                    break;
                                                case 2:
                                                    ChangeShowtimeListing.Update(ChangeShowtimeListing.list);
                                                    break;
                                                case 3:
                                                    ChangeShowtimeListing.Remove(ChangeShowtimeListing.list);
                                                    break;
                                                default:
                                                    break;
                                            }
                                            choice = sc.nextInt();
                                        } while (choice < 3);
                                        break;
                                }
                            } while (choice < 3);
                        }

                        //if a Moviegoer logs in
                        else if (userLoginFlag == true) {
                            //Create Moviegoer Object
                            //user ID==input_id, user password=input_pw
                            System.out.println("User " + input_id + " Logged In!");

                            //Write Moviegoer choice below:
                            //do{}while();

                        }
                        //Login Fail
                        else {
                            System.out.println("Failed to login");
                        }
                    } while (true);
                    break;
                case 2:
                    //Creating an account
                    do {
                        System.out.println("Creating an account");
                        String temp_s = sc.nextLine();
                        System.out.printf("Enter ID:");
                        input_id = sc.nextLine();
                        System.out.printf("Enter password:");
                        input_pw = sc.nextLine();
                        try {
                            FileInputStream fis = new FileInputStream("login_data.txt");
                            Scanner fileRead = new Scanner(fis);

                            while (fileRead.hasNextLine()) {

                                currentLine = fileRead.nextLine();
                                //now tokenize the currentLine:
                                StringTokenizer st = new StringTokenizer(currentLine, "=", false);
                                //put tokens ot currentLine in map
                                mapInFile.put(st.nextToken(), st.nextToken());
                                //print All data in MAP
                            }

                            //For checking the user data
                            for (Map.Entry<String, String> m : mapInFile.entrySet()) {
                                System.out.println(m.getKey() + " : " + m.getValue());
                                String temp2 = m.getKey();
                                if (temp2.equals(input_id)) {
                                    System.out.println("User ID already exists. Try with a different ID");
                                    sameUserID = true;
                                }
                                System.out.println();
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("File Read Error!" + e.getMessage());
                            System.exit(0);
//                        } catch (IOException e) {
//                            System.out.println("IO Error!" + e.getMessage());
//                            e.printStackTrace();
//                            System.exit(0);
//                        }
                        }
                        while (sameUserID == true) ;


                        try {
                            PrintWriter pw = new PrintWriter(new FileWriter("login_data.txt", true));
                            pw.printf("\n" + input_id + "=" + input_pw);

                            pw.flush();
                            pw.close();
                            //fos.close();
                        } catch (Exception e) {
                        }
                        System.out.println("done");
                        break;
//                        case 3:
//                            break;
//                        case 4:
//                            break;
//                        case 5:
//                            break;
//                        case 6:
//                            break;
//                        case 7:
//                            break;


                    } while (choice < 7);
                    sc.close();
            }
        } while (choice < 7);
    }
}