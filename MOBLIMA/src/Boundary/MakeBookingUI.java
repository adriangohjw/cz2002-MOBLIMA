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
        System.out.println("Here are the available movies: "); 
        showAvailableMovies();
        ArrayList<Cinema> cinemaList = showAvailableSessions();
        pickDateTimeCode(cinemaList);
        reserveSeat();   
        priceShowcase();     
        makeTransaction();
    }
    
    public void showAvailableMovies() {
    	ArrayList<Movie> movieList = movieCtrl.read();
    	for (int i = 0; i < movieList.size(); i++) {
    		if (movieList.get(i).getShowStatus() == MovieStatus.NOW_SHOWING) {
    			System.out.println(movieList.get(i).getTitle());
    		}
    	}
    }
    
    public ArrayList<Cinema> showAvailableSessions() {
    	Session tempSession;
    	Cinema tempCinema;
    	boolean printedCinemaCode =  false;
    	ArrayList<Cinema> tempCinemaList = new ArrayList<Cinema>();
    	System.out.println("Choose movie to view available sessions: ");
    	movieTitle = InputController.getStringFromUser();
    	ArrayList<Cinema> cinemaList = cinemaCtrl.read();
    	for (int i = 0; i < cinemaList.size(); i++) {
    		printedCinemaCode = false;
    		tempCinema = cinemaList.get(i);
    		for(int j = 0; j < tempCinema.getSessions().size(); j++) {
    			tempSession = tempCinema.getSessions().get(j);
    			if (tempSession.getMovie().getTitle().equals(movieTitle)) {
    				if (!printedCinemaCode) {
    					System.out.println("Cinema code: " + tempCinema.getCode() + "; cinema type: " + tempCinema.getCinemaType());
    					System.out.println("Available sessions for this cinema:");
    				}
    				printedCinemaCode = true;
    				System.out.println("DateTime: " + tempSession.getSessionDateTime());
    			}
    		}
    	}
    	return tempCinemaList;
    }
    
    public void pickDateTimeCode(ArrayList<Cinema>mainCinemaList) {
    	Session tempSession;
    	Cinema tempCinema;
    	System.out.println("Choose your cinema code: ");
    	cinemaCode = InputController.getStringFromUser();
    	System.out.println("Choose your viewing date and time (in format yyyy-MM-dd hh:mm): ");
    	viewingDateTime = InputController.getDateTimeFromUser();
    	
    	for (int i = 0; i < mainCinemaList.size(); i++) {
    		tempCinema = mainCinemaList.get(i);
    		if (tempCinema.getCode() == cinemaCode) {
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
    }
    
    public void priceShowcase() {
    	System.out.println("Enter your age type (Student, Senior): ");
    	String priceTypeString = InputController.getStringFromUser();
    	if (priceTypeString.equals("Student")) priceCtrl.computePrice(queriedSession, queriedCinema, PriceType.STUDENT);
    	if (priceTypeString.equals("Senior")) priceCtrl.computePrice(queriedSession, queriedCinema, PriceType.SENIOR_CITIZEN);
    }
    
    public void makeTransaction() {
    	Movie_Goer user = movieGoerCtrl.readByEmail(email);
    	Movie movie = queriedSession.getMovie();
    	String TID = cinemaCode + viewingDateTime.format(DateTimeFormatter.ofPattern("YYYYDDMMhhmm"));;
    	Transaction newTransaction = new Transaction(TID, user, movie);
    	transCtrl.create(newTransaction);
    }
}
