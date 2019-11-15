package Boundary;

import Controller.*;
import Model.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

public class MakeBookingUI {
    private String movieTitle;
    private String cinemaCode;
    private String showingDateTime;
    private Session queriedSession;
    private SeatingPlan seatAvailability;
    private String priceType;
    
    private CinemasController cinemaCtrl;
    private MoviesController movieCtrl;
    private SessionsController sessCtrl;
    private TransactionsController transCtrl;
    
    Scanner sc = new Scanner(System.in);
    
    public MakeBookingUI() {
        this.cinemaCtrl = new CinemasController();
        this.movieCtrl = new MoviesController();
        this.transCtrl = new TransactionsController();
        this.sessCtrl = new SessionsController();
    }

    public MakeBookingUI(CinemasController cinemaCtrl, MoviesController movieCtrl, SessionsController sessCtrl, TransactionsController transCtrl) {
        this.cinemaCtrl = cinemaCtrl;
        this.movieCtrl = movieCtrl;
        this.sessCtrl = sessCtrl;
        this.transCtrl = transCtrl;
    }
    
    public void main() throws ClassNotFoundException, IOException {
        System.out.println("Here are the available movies: "); 
        //showAvailableMovies();
        ArrayList<Cinema> cinemaList = showAvailableSessions();
        pickDateTimeCode(cinemaList);
        reserveSeat();
        //need to find a way to show prices
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
    	ArrayList<Cinema> tempCinemaList = new ArrayList<Cinema>();
    	System.out.println("Choose movie to view available sessions: ");
    	movieTitle = sc.next();
    	ArrayList<Cinema> cinemaList = cinemaCtrl.read();
    	for (int i = 0; i < cinemaList.size(); i++) {
    		for(int j = 0; j < cinemaList.get(i).getSessions().size(); j++) {
    			tempSession = cinemaList.get(i).getSessions().get(j);
    			if (tempSession.getMovie().getTitle() == movieTitle) {
    				System.out.println(tempSession.toString());
    				System.out.println(cinemaList.get(i).toString());
    				tempCinemaList.add(cinemaList.get(i));
    			}
    		}
    	}
    	return tempCinemaList;
    }
    
    public void pickDateTimeCode(ArrayList<Cinema>mainCinemaList) {
    	sc.nextLine();
    	Session tempSession;
    	Cinema tempCinema;
    	System.out.println("Choose your cinema code: ");
    	cinemaCode = sc.nextLine();
    	System.out.println("Choose your viewing date and time (in format yyyy-MM-dd hh:mm): ");
    	showingDateTime = sc.nextLine();
    	
    	for (int i = 0; i < mainCinemaList.size(); i++) {
    		tempCinema = mainCinemaList.get(i);
    		if (tempCinema.getCode() == cinemaCode) {
    			for(int j = 0; j < tempCinema.getSessions().size(); j++) {
    				tempSession = tempCinema.getSessions().get(j);
    				if (tempSession.getSessionDateTime().equals(showingDateTime)) {
    					seatAvailability = tempSession.getSeatsAvailability();
    					queriedSession = tempSession;
    					break; //only one session for that date time?
    				}
    			}
    		}
    	}
    }
    
    
    public void reserveSeat() {
    	sc.nextLine();
    	int id;
    	seatAvailability.printLayout();
    	System.out.println("Choose your seat id: ");
    	id = sc.nextInt(); //from 0 to row * column - 1
    	while (seatAvailability.checkSeats(id)) {
    		System.out.println("Seat already taken! Try again.");
        	System.out.println("Choose your seat id: ");
        	id = sc.nextInt();
    	}
    	seatAvailability.assignSeats(id);
    	sessCtrl.updateSeatsAvailability(queriedSession.getId(), seatAvailability);
    }
    
    public void makeTransaction() {
    	// need to find a way to get username???
    	//Transaction newTransaction = new Transaction();
    	//transCtrl.create(newTransaction);
    }
}
