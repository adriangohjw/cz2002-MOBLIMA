package Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static Date getDateFromUser(){
        Date result = null;
        String date;
        boolean validInput = false;
        while(!validInput){
            try{
                date = sc.nextLine();
                result = new SimpleDateFormat("dd-MM-yyyy").parse(date);
                validInput = true;
            }
            catch(ParseException e){
                System.out.println("Must be in DD-MM-YYYY format!");
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
