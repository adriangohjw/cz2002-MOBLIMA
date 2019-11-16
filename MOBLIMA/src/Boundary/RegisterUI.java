package Boundary;

import Controller.*;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class RegisterUI {

    private String email;
    private String password;
    private String password2;
    private int role;
    private boolean consistentPassword = false;
    private MovieGoersController movieGoersCtrl;
    private AdminsController adminsCtrl;
    Scanner sc = new Scanner(System.in);

    RegisterUI() {
        this.movieGoersCtrl = new MovieGoersController();
        this.adminsCtrl = new AdminsController();
    };

    RegisterUI(MovieGoersController movieGoersCtrl, AdminsController adminsCtrl){
        this.movieGoersCtrl = movieGoersCtrl;
        this.adminsCtrl = adminsCtrl;
    }

    public void main(){
        do {
            inputRegister();
            if(consistentPassword){
                if(role == 1)
                    movieGoersCtrl.create(email, password);
                else 
                    adminsCtrl.create(email, password);
                System.out.println("You have registered successfully");
            }
            else {
                System.out.println("Password not consistent. Enter again");
            }
        }
        while(!consistentPassword);
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
        consistentPassword = password.equals(password2);
    }
}
