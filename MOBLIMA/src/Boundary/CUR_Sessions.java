package Boundary;

import java.io.IOException;
import Controller.*;
import Model.Movie;
import Model.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CUR_Sessions {
	private MoviesController movieCtrl = new MoviesController();
	private SessionsController sessionCtrl = new SessionsController();
	
	public void main(){
		System.out.println("Create/Update/Remove session: \n" +
						   "1. Create Movie Session\n" +
						   "2. Update Movie Session\n" +
						   "3. Remove Movie Session");
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
			}
		}

	public void createSession() {
		
		System.out.println("Creating Session...");
		System.out.println("Enter cinema code: ");
		String cinemaCode = InputController.getStringFromUser();
		System.out.println("Enter movie id: ");
	    int movie_id = InputController.getIntFromUser();
	    System.out.println("Enter session date and time: ");
	    LocalDateTime sessionDateTime = InputController.getDateTimeFromUser();
	    Movie movie = movieCtrl.readByID(movie_id);
	    sessionCtrl.create(cinemaCode, movie, sessionDateTime);

	}
	
	public void updateSession() {
		
		System.out.println("Updating Session...");
		System.out.println("Select session to be updated: ");
		ArrayList<Session> allSessions = sessionCtrl.read();
		allSessions.forEach(Session -> printSession(Session));
		System.out.print("Enter session id: ");
		int session_id = InputController.getIntFromUser();
		
		System.out.println("Select attribute to update: \n" +
						   "1. Movie\n" +
						   "2. Date & Time");
		int choice = InputController.getIntFromUser();
		switch(choice) {
		case 1:
			System.out.println("Enter new Movie id: ");
			int movie_id = InputController.getIntFromUser();
			sessionCtrl.updateById(0, session_id, movieCtrl.readByID(movie_id));
			break;
			
		case 2:
			System.out.println("Enter new Date & Time: ");
			LocalDateTime dateTime = InputController.getDateTimeFromUser();
			sessionCtrl.updateById(1, session_id, dateTime);
			break;
		}
	}
	
		
	public void removeSession() {
		
		System.out.println("Deleting Session: ");
		System.out.println("Select session to be deleted: ");
		ArrayList<Session> allSessions = sessionCtrl.read();
		allSessions.forEach(Session -> printSession(Session));
		System.out.print("Enter session id: ");
		int sessionId = InputController.getIntFromUser();
		
		sessionCtrl.delete(sessionId);
	}
	
	public void printSession(Session session) {
		System.out.println("Session id: " + session.getId() + "\n" +
						   "Movie Title: " + session.getMovie().getTitle() + "\n" +
						   "Session DateTime: " + session.getSessionDateTimeToString() + "\n" +
							"Is Weekend: " + session.isWeekend());
	}
}
