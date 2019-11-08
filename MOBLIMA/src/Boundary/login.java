package MOBILA.Boundary;

import MOBILA.Controller.*;
import MOBILA.Model.*;
import Model.Movie;

import java.util.*;

public class login {
    private String email;
    private String password;
    private boolean correctPassword = false;
    Scanner sc = new Scanner(System.in);

    login(){}

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
        email = sc.nextLine();
        System.out.println("Password: ");
        password = sc.nextLine();

        User user = usersController.readyByEmail(); //should only be an object but not array of object
        correctPassword = user.getPassword().equals(password); //should have one more function getPassword, and should not be hash password
    }
}
