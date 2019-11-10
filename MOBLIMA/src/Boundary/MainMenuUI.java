package Boundary;

import java.io.IOException;
import java.util.Scanner;

public class CURshowtimes {
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
			updateMovie();
			break;
		case 3:
			removeMovie();
			break;
		}

	public void createSession() {
		System.out.println("Creating Session...");
		System.out.println("Enter session code: ");
		String cinemaCode = input.nextLine();
		System.out.println("Enter movie title: ");
	    String movieTitle = input.nextLine();
	    
	    System.out.println("Enter session date: ");
	    Date session
	    Date sessionDate,
	    String sessionTime,
	    SeatsAvailability seatsAvailability
	}
}

// package Boundary;

// import Controller.*;
// import java.util.Scanner;

// public class MainMenuUI {
// 	static Scanner sc = new Scanner(System.in);
// 	private MainController mainCtrl;
	
// 	public MainMenuUI(MainController mainCtrl) {
//         this.mainCtrl = mainCtrl;
//     }

// 	public void displayMenu() {
// 		int choice;
// 		do {
// 			//print menu options
//             choice = sc.nextInt();
// 		} while (mainCtrl.chooseAction(choice) == -1); 
// 	}	
// }
