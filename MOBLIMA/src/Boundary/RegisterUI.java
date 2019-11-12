package Boundary;

import Controller.*;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class RegisterUI {

    RegisterUI() {
    };

    private String email;
    private String password;
    private String password2;
    private int role;
    private boolean consistenPassword = false;
    private MovieGoersController movieGoersController = new MovieGoersController();
    private AdminsController adminsController = new AdminsController();
    Scanner sc = new Scanner(System.in);

    public void main() throws NoSuchAlgorithmException {
        do {
            inputRegister();
            if(consistenPassword==true){
                if(role == 1)
                    movieGoersController.create(email, password);
                else 
                    adminsController.create(email, password);
                System.out.println("You have registered successfully");
            }
            else {
                System.out.println("Password not consistent. Please enter again");
            }
        }
        while(consistenPassword==false);
    }

    public void inputRegister(){
        System.out.println("Please enter your email: ");
        email = sc.next();
        System.out.println("Your role: 1 for [movie-goer], 2 for [admin]");
        role = sc.nextInt();
        System.out.println("Password: ");
        password = sc.next();
        System.out.println("Password again to verify: ");
        password2 = sc.next();
        consistenPassword = password.equals(password2);
    }
}
