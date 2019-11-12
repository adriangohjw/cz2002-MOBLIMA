package Boundary;

import Controller.*;
import Model.*;
import Model.Movie;

import java.util.*;

public class LoginUI {
    private String email;
    private String password;
    private int role;
    private boolean correctPassword = false;
    AdminsController adminsController = new AdminsController();
    MovieGoersController movieGoersController = new MovieGoersController();
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
        
        System.out.println("Your role: 1 for [movie-goer], 2 for [admin]");
        
        if(role==1){
            Admin user = adminsController.readByEmail(email);
        }
        else {
            Movie_Goer user = movieGoersController.readByEmail(email);
        }
       
        correctPassword = user.getPasswordHashed().equals(user.PasswordSHA256(email, password));
    }
}
