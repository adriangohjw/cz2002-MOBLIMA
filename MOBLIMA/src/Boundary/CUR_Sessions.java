package Boundary;

import java.io.IOException;
import java.time.LocalDateTime;

import Controller.*;
import Model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CUR_Sessions {
	private MoviesController movieCtrl = new MoviesController();
	private CinemasController cinemaCtrl = new CinemasController();
	private CineplexesController cineplexCtrl = new CineplexesController();
	private SessionsController sessionCtrl = new SessionsController();
	
	public void main(){
		boolean exit = false;
		while(!exit) {
		System.out.println("Create/Update/Remove session: \n" +
						   "1. Create Movie Session\n" +
						   "2. Update Movie Session\n" +
						   "3. Remove Movie Session\n" +
						   "4. Return to Main Menu");
		int option = InputController.getIntFromUser();
		switch(option) {
			case 1:
				createSession();
				break;
			case 2:
				updateSession();
				break;
			case 3:
				removeSession();
				break;
			case 4:
				exit = true;
				break;
			}
		}
	}

	public void createSession() {

		System.out.println("Creating Session...");

		System.out.println("Enter cinema code: ");
		String cinemaCode = InputController.getStringFromUser();
		if (cinemaCtrl.readByAttribute(0, cinemaCode).isEmpty()) {
			System.out.println("Cinema does not exist!\n"+
							   "Returning to menu...");
			return;
		}

		System.out.println("Enter movie id: ");
	    int movie_id = InputController.getIntFromUser();
	    if (movieCtrl.readByID(movie_id) == null) {
			System.out.println("Movie does not exist!\n"+
							   "Returning to menu...");
			return;
		};

	    System.out.println("Enter session date and time: ");
	    LocalDateTime sessionDateTime = InputController.getDateTimeFromUser();
	    Movie movie = movieCtrl.readByID(movie_id);
	    sessionCtrl.create(cinemaCode, movie, sessionDateTime);

	    System.out.println("Session successfully created!");

	}
	
	public void updateSession() {
		
		System.out.println("Updating Session...");
		
		System.out.println("Enter Cineplex: ");
		cineplexCtrl.ListCineplex();
		System.out.println("Enter Cineplex Name:");
		String cineplexName = InputController.getStringFromUser();
		if (cineplexCtrl.readByName(cineplexName) == null) {
			System.out.println("Cineplex does not exist!\n" +
							   "Returning to menu...");
			return;
		}
		
		ArrayList<Cinema> cinemaList = cineplex.getCinemas();
		cinemaList.forEach(Cinema -> printCinema(Cinema));

		System.out.print("Enter session id: ");
		int session_id = InputController.getIntFromUser();
		if (sessionCtrl.readById(session_id) == null) {
			System.out.println("session id does not exist!\n" +
							   "Returning to menu...");
			return;
		}
		
		System.out.println("Select attribute to update: \n" +
						   "1. Movie\n" +
						   "2. Date & Time \n");
		int choice = InputController.getIntFromUser();

		switch(choice) {
		case 1:
			System.out.println("Enter new Movie id: ");
			int movie_id = InputController.getIntFromUser();
			if (movieCtrl.readByID(movie_id) == null) {
				System.out.println("Movie does not exist!\n"+
								   "Returning to menu... ");
				break;
			};
			sessionCtrl.updateById(0, session_id, movieCtrl.readByID(movie_id));
			break;
			
		case 2:
			System.out.println("Enter new Date & Time: ");
			LocalDateTime dateTime = InputController.getDateTimeFromUser();
			sessionCtrl.updateById(1, session_id, dateTime);
			break;
			
		}
		System.out.println("Session " + session_id + " successfully updated!");
	}
	
		
	public void removeSession() {
		
		System.out.println("Deleting Session: ");
		System.out.println("Select Cineplex:");
		ArrayList<Cineplex> cineplexList = cineplexCtrl.read();
		cineplexList.forEach(Cineplex -> printCineplex(Cineplex));
		System.out.println("Enter Cineplex Name:");
		String cineplexName = InputController.getStringFromUser();
		Cineplex cineplex = cineplexCtrl.readByName(cineplexName);
		if (cineplex == null) {
			System.out.println("Cineplex does not exist!\n" +
							   "Returning to menu...");
			return;
		}
		
		ArrayList<Cinema> cinemaList = cineplex.getCinemas();
		cinemaList.forEach(Cinema -> printCinema(Cinema));
		System.out.print("Enter session id: ");
		int sessionId = InputController.getIntFromUser();
		if (sessionCtrl.readById(sessionId) == null) {
			System.out.println("session id does not exist!\n" +
							   "Returning to menu...");
			return;
		}	
		sessionCtrl.delete(sessionId);
		System.out.println("Session " + sessionId + " successfully deleted!");
	}
	
	
	public void printCinema(Cinema cinema) {
		System.out.println("Cinema code" + cinema.getCode());
		ArrayList<Session> sessionList = cinema.getSessions();
		sessionList.forEach(session -> printSession(session));
	}
	
	public void printSession(Session session) {
		System.out.println("Session id: " + session.getId() + "\n" +
						   "Movie Title: " + session.getMovie().getTitle() + "\n" +
						   "Session DateTime: " + session.getSessionDateTime());
	}
	
	public void printCineplex(Cineplex cineplex) {
		System.out.println(cineplex.getName());
	}
}
