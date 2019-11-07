package Boundary;
import java.util.Scanner;

import sun.security.util.Password;

public class UserInterface {
    public static void Menu(){
        System.out.println("Welcome to MOBILA Cineplex");
        System.out.println("--------------------------");
        System.out.println("Choose one option: ");
        System.out.println("1: Log in");
        System.out.println("2: Sign up");
        System.out.println("3: Explore movies");

        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch(option){
            case 1: login();
                    break;
            case 2: signup();
                    break;
            case 3: explore();
                    break;
        }
    }

    public static void login(){
        String email;
        String password;
        boolean checkPassword;
        int role;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Your email: ");
            email = sc.next();
            System.out.println("Password: ");
            password = sc.next();
            System.out.println("1 for admin and 2 for movie-goer");
            role = sc.nextInt();
            User user = new User(email, password, role); //search for User given email and then return password
            checkPassword = user.validatePassword(password);
            if(!checkPassword){
                System.out.println("Your email or password is incorrect");
            }
        } while (!checkPassword);

        System.out.println("You have logged in!");
    }



    public static void signup(){
        String email;
        String password;
        String password2;
        int role;

        Scanner sc = new Scanner(System.in);
        System.out.println("Your email: ");
        do {
            email = sc.next();
        System.out.println("Password: ");
        password = sc.next();
        System.out.println("Check password again");
        password2 = sc.next();
        }
        while(password!=password2);

        System.out.println("1 for admin and 2 for movie-goer");
        role = sc.nextInt();
        User user = new User(email, password, role); // store into the database
        System.out.println("You have registered!");
    }



    public static void explore(){
        //Call from the Movie Controller
    }


}
