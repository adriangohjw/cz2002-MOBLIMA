/* package Controller;

import java.util.ArrayList;

import Boundary.*;
import Model.*;


public class MainController {

    private MoviesController movieCtrl;
    private AdminsController adminCtrl;

    private AppController appCtrl;
    
    public final static String movieFileName = MoviesController.FILENAME;
    public final static String userFileName = AdminsController.FILENAME;

    private ArrayList<Movie> movieListing;
    private ArrayList<Admin> adminListing;
    
    public MainController() {

		// include Boundary
		
		openFromDB();
		setAllCtrl();
    }
    
    private void openFromDB() {
        movieListing = movieCtrl.read();
        adminListing = adminCtrl.read();
    }

    private void setAllCtrl() {
        
        // initialized Boundary with Controllers
    }

	private void saveToDB() {
        movieCtrl.replaceExistingFile(movieFileName, movieListing);
        adminCtrl.replaceExistingFile(movieFileName, adminListing);
    }

    public int chooseAction(int choice) {

        // actions based on what users choose
        switch (choice){
            case 1:
                break;
            default:
                break;
            
        }
        return 1;
    }
} */