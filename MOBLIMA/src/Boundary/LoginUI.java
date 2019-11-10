package Boundary;

import Controller.*;
import Model.*;
import Model.Movie;

import java.util.*;

public class LoginUI {
    private String email;
    private String password;
    private boolean correctPassword = false;
    Scanner sc = new Scanner(System.in);

    LoginUI(){}

    public void main(){
        do{
            verfify();
            if(correctPassword==false){
                System.out.println("Wrong password or email. Please enter again.");
            }
            else {
                System.out.println("You have login sucessfully.");
            }
        } while(correctPassword == false);
    }

    public void verfify(){
        System.out.println("Please enter your email: ");
        email = sc.next();
        System.out.println("Password: ");
        password = sc.next();

        Admin admin = AdminsController.readByEmail(email);
        Movie_Goer movieGoer = MovieGoersController.readByEmail(email);

        if(admin!=null){
            user = admin;
        } else{
            user = movieGoer;
        }

        correctPassword = user.getPasswordHashed().equals(user.PasswordSHA256(email, password));
    }
}
