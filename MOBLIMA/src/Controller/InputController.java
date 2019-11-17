package Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public static String getEmailFromUser(){
        String pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String input = "";
        boolean validInput = false;
        while(!validInput){
            input = sc.nextLine();
            if(input.matches(pattern)){
                validInput = true;
            }
            else{
                System.out.println("Must match email pattern!");
            }
        }
        return input;
    }

    public static String getMobileNumberFromUser(){
        String pattern = "\\d{8}";
        String input = "";
        boolean validInput = false;
        while(!validInput){
            input = sc.nextLine();
            if(input.matches(pattern) && (input.startsWith("9")||input.startsWith("8"))){
                validInput = true;
            }
            else{
                System.out.println("Must be valid mobile number (8 digits long, starts with either 8 or 9)");
            }
        }
        return input;
    }

    public static int getYesOrNoFromUser(){
        int input = -1;
        boolean validInput = false;
        while (!validInput){
            input = getIntFromUser();
            if(input==0 || input==1){
                validInput = true;
            }
            else{
                System.out.println("Must be either 0 or 1");
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
            }
            else{
                System.out.println("Wrong input!");
            }
            sc.nextLine();
        }
        return input;
    }

    public static int getPositiveIntFromUser(){
        int input = -1;
        boolean validInput = false;
        while(!validInput) {
            if(sc.hasNextInt()){
                input = sc.nextInt();
                if(input>0)
                    validInput = true;
            }
            else{
                System.out.println("Wrong input!");
            }
            sc.nextLine();
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
