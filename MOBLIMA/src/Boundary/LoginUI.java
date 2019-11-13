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

    public void main() throws ClassNotFoundException, NoSuchAlgorithmException, IOException {
        do {
            verify();
            if (correctPassword == false) {
                System.out.println("Wrong password or email. Please enter again.");
            } else {
                System.out.println("You have login sucessfully.");
            }
        } while (correctPassword == false);
    }

    public void verify() throws ClassNotFoundException, IOException, NoSuchAlgorithmException {
        System.out.println("Please enter your email: ");
        email = sc.next();
        System.out.println("Password: ");
        password = sc.next();

        if (role == 1) {
            Admin user = adminsCtrl.readByEmail(email);
            correctPassword = user.getPasswordHashed().equals(user.PasswordSHA256(email, password));
        } else {
            Movie_Goer user = movieGoersCtrl.readByEmail(email);
            correctPassword = user.getPasswordHashed().equals(user.PasswordSHA256(email, password));
        }
    }
}