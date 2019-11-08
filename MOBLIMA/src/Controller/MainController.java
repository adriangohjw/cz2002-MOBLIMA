package Controller;

import java.util.ArrayList;

import Boundary.*;
import Model.*;


public class MainController {

    private MoviesController movieCtrl;
    private UsersController userCtrl;

    private AppController appCtrl;
    
    public final static String movieFileName = MoviesController.FILENAME;
    public final static String userFileName = UsersController.FILENAME;

    private ArrayList<Movie> movieListing;
    private ArrayList<User> userListing;
    
    public MainController() {

		// include Boundary
		
		openFromDB();
		setAllCtrl();
    }
    
    private void openFromDB() {
        movieListing = movieCtrl.read();
        userListing = userCtrl.read();
    }

    private void setAllCtrl() {
        
        // initialized Boundary with Controllers
    }

	private void saveToDB() {

        movieCtrl.replaceExistingFile(movieFileName, movieListing);
        userCtrl.replaceExistingFile(movieFileName, userListing);
    }

    public int chooseAction(int choice) {

        // actions based on what users choose
        switch (choice){
            case 1:
                break;
            default:
                break;
            
        }
    }
}