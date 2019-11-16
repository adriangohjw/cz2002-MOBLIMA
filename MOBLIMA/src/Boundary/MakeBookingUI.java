package Boundary;

import Controller.*;
import Model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MakeBookingUI {
    private String email;
    private String movieTitle;
    private String cinemaCode;
    private LocalDateTime viewingDateTime;
    private Cinema queriedCinema;
    private Session queriedSession;
    private SeatingPlan seatAvailability;

    private CinemasController cinemaCtrl;
    private MoviesController movieCtrl;
    private MovieGoersController movieGoerCtrl;
    private SessionsController sessCtrl;
    private PriceController priceCtrl;
    private TransactionsController transCtrl;
    
    public MakeBookingUI() {
        this.cinemaCtrl = new CinemasController();
        this.movieCtrl = new MoviesController();
        this.movieGoerCtrl = new MovieGoersController();
        this.transCtrl = new TransactionsController();
        this.priceCtrl = new PriceController();
        this.sessCtrl = new SessionsController();
    }

    public MakeBookingUI(String email) {
        this.cinemaCtrl = new CinemasController();
        this.movieCtrl = new MoviesController();
        this.movieGoerCtrl = new MovieGoersController();
        this.transCtrl = new TransactionsController();
        this.sessCtrl = new SessionsController();
        this.priceCtrl = new PriceController();
        this.email = email;
    }
    
    public void setCinemaCtrl (CinemasController cinemaCtrl) {
    	this.cinemaCtrl = cinemaCtrl;
    }
    
    public void setMovieCtrl (MoviesController movieCtrl) {
    	this.movieCtrl = movieCtrl;
    }
    
    public void setTransCtrl (TransactionsController transCtrl) {
    	this.transCtrl = transCtrl;
    }
    
    public void setSessCtrl (SessionsController sessCtrl) {
    	this.sessCtrl = sessCtrl;
    }
    
    public void main() {
        ArrayList<Cinema> cinemaList;
        System.out.println("Here are the available movies: "); 
        showAvailableMovies();
        System.out.println("Choose cineplex (-1 to return): ");
        System.out.println();
        ArrayList<Cineplex> cineplexes = cineplexesCtrl.read();
        for(int i = 0; i < cineplexes.size(); i++) {
        	System.out.println((i+1) + ". " + cineplexes.get(i).getName());
		}
        int choice = InputController.getIntFromUser();
        cinemaList = showAvailableSessions(cineplexes.get(choice-1).getName());
        if (choice == -1) return;
        while (cinemaList.size() == 0) {
        	System.out.println("No available sessions for this cineplex! Enter another one.");
            choice = InputController.getIntFromUser();
            if (choice == -1) return;
            cinemaList = showAvailableSessions(cineplexes.get(choice-1).getName());
        }
        pickDateTimeCode(cinemaList);
    }
    
    public void showAvailableMovies() {
		System.out.println();
    	ArrayList<Movie> movieList = movieCtrl.read();
    	for (int i = 0; i < movieList.size(); i++) {
    		if (movieList.get(i).getShowStatus() == MovieStatus.NOW_SHOWING) {
    			System.out.println(movieList.get(i).getTitle());
    		}
    	}
    	System.out.println();
    }
    
    public ArrayList<Cinema> showAvailableSessions(String cineplexName) {
    	Session tempSession;
    	Cinema tempCinema;
    	boolean printedCinemaCode =  false;
    	boolean printSeparator = false;
    	ArrayList<Cinema> tempCinemaList = new ArrayList<Cinema>();
    	System.out.println();
    	System.out.println("Enter movie title to view available sessions: ");
    	movieTitle = InputController.getStringFromUser();
    	ArrayList<Cinema> cinemaList = cinemaCtrl.readByCineplexName(cineplexName);
		System.out.println("------------------------------------------------------");
		for (int i = 0; i < cinemaList.size(); i++) {
    		printedCinemaCode = false;
    		tempCinema = cinemaList.get(i);
    		for(int j = 0; j < tempCinema.getSessions().size(); j++) {
    			tempSession = tempCinema.getSessions().get(j);
    			if (tempSession.getMovie().getTitle().equals(movieTitle)) {
    				if (!printedCinemaCode) {
    					System.out.println("Cinema code: " + tempCinema.getCode() + "		Cinema type: " + tempCinema.getCinemaType());
    					System.out.println();
    					System.out.println("Available screening times of " + movieTitle+ " in this cinema:");
    					System.out.println();
    				}
    				printedCinemaCode = true;
    				System.out.println("	Date: " + tempSession.getSessionDateTimeToString());
    				System.out.println();
    				tempCinemaList.add(tempCinema);
    				printSeparator = true;
    			}
    		}
    		if(printSeparator){
    			System.out.println("------------------------------------------------------");
    			printSeparator = false;
			}
    	}
    	return tempCinemaList;
    }
    
    public void pickDateTimeCode(ArrayList<Cinema> mainCinemaList) {
    	Session tempSession;
    	Cinema tempCinema;
    	System.out.println("Choose your cinema code: ");
    	cinemaCode = InputController.getStringFromUser();
    	System.out.println("Choose your viewing date and time (in format DD/MM/YYYY HH:MM): ");
    	viewingDateTime = InputController.getDateTimeFromUser();
    	for (int i = 0; i < mainCinemaList.size(); i++) {
    		tempCinema = mainCinemaList.get(i);
    		if (tempCinema.getCode().equals(cinemaCode)) {
    			for(int j = 0; j < tempCinema.getSessions().size(); j++) {
    				tempSession = tempCinema.getSessions().get(j);
    				if (tempSession.getSessionDateTime().equals(viewingDateTime)) {
    					seatAvailability = tempSession.getSeatsAvailability();
    					queriedSession = tempSession;
    					queriedCinema = tempCinema;
    					break; //only one session for that date time
    				}
    			}
    		}
    	}
		priceShowcase();
	}
    
    public void reserveSeat() {
    	int id;
    	seatAvailability.printLayout();
    	System.out.println("Choose your seat id: ");
    	id = InputController.getIntFromUser(); //from 0 to row * column - 1
    	while (seatAvailability.checkSeats(id)) {
    		System.out.println("Seat already taken! Try again.");
        	System.out.println("Choose your seat id: ");
        	id = InputController.getIntFromUser();
    	}
    	seatAvailability.assignSeats(id);
    	sessCtrl.updateSeatsAvailability(queriedSession.getId(), seatAvailability);
		makeTransaction();
    }
    
    public void priceShowcase() {
    	double price = 0;
    	boolean validInput = false;
    	while(!validInput){
			System.out.println("Enter your age type (Student, Senior, Standard): ");
			String priceTypeString = InputController.getStringFromUser();
			if (priceTypeString.equals("Student")){
				price = priceCtrl.computePrice(queriedSession, queriedCinema, PriceType.STUDENT);
				validInput = true;
			}
			else if (priceTypeString.equals("Senior")){
				price = priceCtrl.computePrice(queriedSession, queriedCinema, PriceType.SENIOR_CITIZEN);
				validInput = true;
			}
			else if(priceTypeString.equals("Standard")){
				price = priceCtrl.computePrice(queriedSession, queriedCinema, PriceType.NORMAL);
				validInput = true;
			}
			else{
				System.out.println("Wrong input!");
			}
		}
    	System.out.println("Total price is equal: " + price + " SGD");
    	System.out.print("Do you want to continue? Yes (0)/ No (1): ");
    	int choice = InputController.getYesOrNoFromUser();
    	if(choice == 0){
    		reserveSeat();
		}
    	else{
    		return;
		}
    }
    
    public void makeTransaction() {
    	Movie_Goer user = movieGoerCtrl.readByEmail(email);
    	Movie movie = queriedSession.getMovie();
    	String TID = cinemaCode + viewingDateTime.format(DateTimeFormatter.ofPattern("YYYYddMMhhmm"));;
    	Transaction newTransaction = new Transaction(TID, user, movie);
    	transCtrl.create(newTransaction);
    }
}
