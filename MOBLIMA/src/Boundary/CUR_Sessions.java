package Boundary;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import Controller.SessionsController;

public class CUR_Sessions {

	private SessionsController sessionsCtrl;

	public CUR_Sessions() {
		// empty constructor
	}

	public CUR_Sessions (SessionsController sessionsCtrl){
		this.sessionsCtrl = sessionsCtrl;
	}

	public void setSessionsCtrl(SessionsController sessionsCtrl){
		this.sessionsCtrl = sessionsCtrl;
	}
	
	Scanner input = new Scanner(System.in);

	public void main() throws ClassNotFoundException, IOException{
		System.out.println("Create/Update/Remove session: /n" +
						   "1. Create Movie Session/n" +
						   "2. Update Movie Session/n" +
						   "3. Remove Movie Session");
		int option = input.nextInt();
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
			default:
				break;
		}
	}

	public void createSession() {
		System.out.println("Creating Session...");
		System.out.println("Enter session code: ");
		String cinemaCode = input.nextLine();
		System.out.println("Enter movie title: ");
	    String movieTitle = input.nextLine();
	    System.out.println("Enter session date: ");
	    Date session = (Date) input.nextLine();
	    Date sessionDate;
	    String sessionTime;
	    SeatsAvailability seatsAvailability
	};
	
	public void updateSession() {
		System.out.println("Updating Movie..")
		System.out.println("Select session to be updated: ");
		
		System.out.println("Session Id: ")
		int sessionId = input.nextInt();
	}
		
	public void removeSession() {
		System.out.println("Deleting Session: ");
		System.out.println("Select session to be deleted: ");
		
		System.out.println("Session Id: ");
		int sessionId = input.nextInt();
	}
}