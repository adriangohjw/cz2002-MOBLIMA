package BusinessLayer;

import java.util.ArrayList;

import CustomException.UsersExceptions.ExistingUserException;

import Model.Admin;
import Model.Movie_Goer;
import Controller.AdminsController;
import Controller.MovieGoersController;;

public class UsersLayer {

    static AdminsController adminsCtrl = new AdminsController();
    static MovieGoersController movieGoersCtrl = new MovieGoersController();

    public static boolean isExistingUser(String username) {

        ArrayList<Admin> allAdmins = adminsCtrl.read();
        ArrayList<Movie_Goer> allMovieGoers = movieGoersCtrl.read();

        for (int i=0; i<allAdmins.size(); i++) {
            if (allAdmins.get(i).getEmail().equals(username)){
                try {
                    throw new ExistingUserException();
                } catch (ExistingUserException e) {
                    System.out.println(e);
                }
                return true;
            }
        }

        for (int j=0; j<allMovieGoers.size(); j++) {
            if (allMovieGoers.get(j).getEmail().equals(username)){
                try {
                    throw new ExistingUserException();
                } catch (ExistingUserException e) {
                    System.out.println(e);
                }
                return true;
            }
        }

        return false;
    }
}