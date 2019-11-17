package Boundary;

import Controller.*;
import Model.*;


public class LoginUI {
    private String email;
    private String password;
    private int role;
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

    public boolean main() {
        boolean result;
        boolean exit = false;
        do{
            result = verify();
            if(!result){
                System.out.println("Wrong password or email.");
                System.out.println("1. Enter again");
                System.out.println("2. Exit");
                if(InputController.getIntFromUser()==2){
                    exit = true;
                }
            }
        } while (!result&&!exit);
        if(result){
            System.out.println("You have logged in sucessfully.\n\n");
            return true;
        }
        return false;
    }

    public boolean verify() {
        System.out.println("Please enter your email: ");
        email = InputController.getStringFromUser();
        System.out.println("Password: ");
        password = InputController.getStringFromUser();

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
