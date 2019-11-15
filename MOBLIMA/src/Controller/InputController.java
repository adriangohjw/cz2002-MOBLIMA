package Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputController {

    private static Scanner sc = new Scanner(System.in);
    public static final int INTEGER = 0;
    public static final int DOUBLE = 1;

    public static String getStringFromUser(){
        String input = "";
        while(input.equals("") || input == null){
            input = sc.nextLine();
            if(input.equals("")){
                System.out.println("Cannot be empty, try again!");
            }
        }
        return input;
    }

    public static double getNumberFromUser(int type){
        double input = -1;
        boolean validInput = false;
        while(!validInput){
            try{
                if(type==INTEGER){
                    input=sc.nextInt();
                }
                else if(type==DOUBLE){
                    input = sc.nextDouble();
                }
                validInput = true;
            }
            catch(InputMismatchException e){
                System.out.println("Must be a number!");
            }
        }
        return input;
    }
}
