package BusinessLayer;

import java.util.ArrayList;
import java.util.regex.Pattern;

import CustomException.UsersExceptions.ExistingUserException;
import CustomException.UsersExceptions.InvalidEmailAddressException;

import Model.Admin;
import Model.Movie_Goer;
import Controller.AdminsController;
import Controller.MovieGoersController;;

public class UsersLayer {

    static AdminsController adminsCtrl = new AdminsController();
    static MovieGoersController movieGoersCtrl = new MovieGoersController();

    public static boolean isValidUser(String username) {

        boolean isValid = true;

        if (isEmailValid(username) == false)
            isValid = false;
            
        if (isExistingUser(username)){
            try {
                throw new ExistingUserException();
            } catch (ExistingUserException e) {
                System.out.println(e);
            }
            isValid = false;
        }
        
        return isValid;
    }

    public static boolean isExistingUser(String username) {

        ArrayList<Admin> allAdmins = adminsCtrl.read();
        ArrayList<Movie_Goer> allMovieGoers = movieGoersCtrl.read();

        for (int i=0; i<allAdmins.size(); i++) {
            if (allAdmins.get(i).getEmail().equals(username))
                return true;
        }

        for (int j=0; j<allMovieGoers.size(); j++) {
            if (allMovieGoers.get(j).getEmail().equals(username))
                return true;
        }

        return false;
    }

    public static boolean isEmailValid(String email) { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) {
            try {
                throw new InvalidEmailAddressException();
            } catch (InvalidEmailAddressException e) {
                System.out.println(e);
            }
            return false; 
        }
        return pat.matcher(email).matches(); 
    }
}