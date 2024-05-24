package id.dojo;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.ZoneId;

public class Utils {
    static Scanner scan = new Scanner(System.in);

    static String getDate(){
        Instant sekarang = Instant.now();
        ZonedDateTime zonedDateTime = sekarang.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        String waktuTerformat = zonedDateTime.format(formatter);

        return waktuTerformat;
    }

    static void message(String message){
        System.out.println();
        border();
        System.out.printf("| %-39s |\n", message);
        border();

        System.out.println("\nEnter untuk keluar...");
        scan.nextLine();
    }

    static void border(){
        System.out.println("-------------------------------------------");
    }

    static void border(String add){
        System.out.println("-------------------------------------------" + add);
    }

    static void menuHeader(String title){
        System.out.println("\n=== " + title + " ===\n");
    }

    static void menuItem(String number, String name){
        System.out.printf("| %-6s | %-30s |%n",number, name);
    }
    static void menuAccount(String name, String userName){
        System.out.printf("| %-10s | %-26s |%n", name, userName);
    }

    static void menuAccountDetail(String name, String userName, String password){
        System.out.printf("| %-10s | %-26s | %-16s |%n", name, userName, password);
    }

    static void menuHistory(String date, String name, String oldPassword, String newPassword){
        System.out.printf("| %-26s | %-26s | %-16s | %-16s |%n", date, name, oldPassword, newPassword);
    }

    static String input(String text){
        System.out.printf("%s : ", text);
        return scan.nextLine();
    }
}
