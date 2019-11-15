package Boundary;

import Controller.*;
import Model.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class LoginUI {
    private String email;
    private String password;
    private int role;
    private boolean correctPassword = false;
    private AdminsController adminsCtrl;
    private MovieGoersController movieGoersCtrl;

    public LoginUI(int _role) {
        this.role = _role;
        this.adminsCtrl = new AdminsController();
        this.movieGoersCtrl = new MovieGoersController();
    }

    public LoginUI(int _role, AdminsController adminsCtrl, MovieGoersController moviesGoerCtrl) {
        this.role = _role;
        this.adminsCtrl = adminsCtrl;
        this.movieGoersCtrl = moviesGoerCtrl;
    }

    Scanner sc = new Scanner(System.in);

    public boolean main() {
        boolean result;
        do{
            result = verify();
            if(!result){
                System.out.println("Wrong password or email. Please enter again.");
            }
        } while (!result);
        System.out.println("You have login sucessfully.");
        return true;
    }

    public boolean verify() {
        System.out.println("Please enter your email: ");
        email = sc.next();
        System.out.println("Password: ");
        password = sc.next();

        if (role == 1) {
            Admin user = adminsCtrl.readByEmail(email);
            if(user == null){
                return false;
            }
            else{
                return user.validatePassword(password);
            }
        } else {
            Movie_Goer user = movieGoersCtrl.readByEmail(email);
            if(user == null){
                return false;
            }
            else{
                return user.validatePassword(password);

            }
        }
    }
}
