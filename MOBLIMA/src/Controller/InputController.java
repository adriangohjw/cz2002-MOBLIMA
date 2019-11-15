package Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputController {

    private static Scanner sc = new Scanner(System.in);

    public static String getStringFromUser(){
        String input = "";
        while(input.equals("")){
            input = sc.nextLine();
            if(input.equals("")){
                System.out.println("Cannot be empty, try again!");
            }
        }
        return input;
    }

    public static int getIntFromUser(){
        int input = -1;
        boolean validInput = false;
        while(!validInput) {
            if(sc.hasNextInt()){
                input = sc.nextInt();
                validInput = true;
                sc.nextLine();
            }
            else{
                System.out.println("Must be a non-decimal number!");
            }
        }
        return input;
    }

    public static double getDoubleFromUser(int range){
        double input = -1;
        boolean validInput = false;
        while(!validInput) {
            if(sc.hasNextDouble()){
                input = sc.nextDouble();
                if(input>=0 && input<=5){
                    validInput = true;
                    sc.nextLine();
                }
                else{
                    System.out.println("Must be within range 0-5! ");
                }
            }
            else{
                System.out.println("Must be double type! (decimal number)");
            }
        }
        return input;
    }

    public static LocalDateTime getDateTimeFromUser(){
        LocalDateTime result = null;
        String date;
        boolean validInput = false;
        while(!validInput){
            try{
                date = sc.nextLine();
                result = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                validInput = true;
            }
            catch(DateTimeParseException e){
                System.out.println("Must be of pattern DD/MM/YYYY HH:MM!");
            }
        }
        return result;
    }

    public static LocalDate getDateFromUser(){
        LocalDate result = null;
        String date;
        boolean validInput = false;
        while(!validInput){
            try{
                date = sc.nextLine();
                result = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                validInput = true;
            }
            catch(DateTimeParseException e){
                System.out.println("Must be of pattern DD/MM/YYYY!");
            }
        }
        return result;
    }

    public static double getDoubleFromUser(){
        double input = -1;
        boolean validInput = false;
        while(!validInput) {
            if(sc.hasNextDouble()){
                input = sc.nextDouble();
                validInput = true;
                sc.nextLine();
            }
            else{
                System.out.println("Must be a double type! (decimal number)");
            }
        }
        return input;
    }
}
