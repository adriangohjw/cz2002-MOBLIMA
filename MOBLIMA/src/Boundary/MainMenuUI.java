package Boundary;

import Controller.CinemasController;
import Controller.CineplexesController;
import Controller.InputController;
import Model.Cinema;
import Model.CinemaType;
import Model.Cineplex;
import Model.SeatingPlan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Controller.InputController.*;

public class MainMenuUI {
	
	public static void main(String[] args) {
		initializeSystem();

		boolean exit = false;
		while (!exit) {
			System.out.println("Welcome to MOBLIMA!\n" +
					"1. Admin\n" +
					"2. Movie Goer\n" +
					"3. New user\n" +
					"4. Exit");
		switch(InputController.getIntFromUser()) {
			case 1:
				admin_login();
				break;
			case 2:
				movie_goer();
				break;
			case 3: 
				register();
				break;
			case 4:
				exit = true;
				System.out.println("Exiting MOBLIMA");
				break;
			default:
				System.out.println("Wrong input!");
			}
			
		}
	}

	private static void initializeSystem() {
		CineplexesController cineplexesController = new CineplexesController();
		CinemasController cinemasController = new CinemasController();
		if(cinemasController.read().size()==0){
			cinemasController.create("First",String.valueOf(100),CinemaType.PREMIUM,new SeatingPlan(10,10));
			cinemasController.create("First",String.valueOf(101),CinemaType.PREMIUM,new SeatingPlan(10,10));
			cinemasController.create("First",String.valueOf(102),CinemaType.STANDARD,new SeatingPlan(10,10));
		}
		if(cineplexesController.read().size()==0){
			ArrayList<Cinema> cinemas = cinemasController.read();
			cineplexesController.create("First", cinemas);
		}
		/*for(Cineplex cineplex: cineplexesController.read()){
			System.out.println(cineplex.toString());
		}*/
	}

	public static void admin_login(){
		LoginUI admin_login = new LoginUI(1);
		boolean loggedIn = admin_login.main();
		while (loggedIn) {
			System.out.println("Select action:\n"+
					   "1.Create/Update/Remove movie listing\n"+
					   "2.Create/Update/Remove movie session\n"+
					   "3.Configure system settings\n" +
					   "4.Search/List movies\n" +
					   "5.Log out");
			switch(InputController.getIntFromUser()) {
				case 1:
					CUR_Movie_Listing curMovieListing = new CUR_Movie_Listing();
					curMovieListing.main();
					break;
				case 2:
					CUR_Sessions curSessions = new CUR_Sessions();
					curSessions.main();
					break;
				case 3:
					ConfigureSystemSetting Conf = new ConfigureSystemSetting();
					Conf.main();
					break;
				case 4:
					SearchMovieUI searchMovieUI = new SearchMovieUI();
					searchMovieUI.main();
					break;
				case 5:
					loggedIn = false;
					System.out.println("Logged out successfully!");
					System.out.println();
					System.out.println();
					break;
				default:
					System.out.println("Try again!");
					break;
			}
		}
	}
	
	public static void movie_goer() {
		LoginUI movie_goer_login = new LoginUI(0);
		boolean exit = false;
		while (!exit) {
			System.out.println("Select action:\n"+
					   "1.Search/List movie\n"+
					   "2.View movie details\n"+
					   "3.Check seat availibility\n" +
					   "4.Book ticket\n" +
					   "5.View booking history\n" +
					   "6.List Top 5 movies\n" +
					   "7.Rate Movie\n" +
					   "8.Exit");
			switch(InputController.getIntFromUser()) {
				case 1:
					SearchMovieUI search_movie_ui = new SearchMovieUI();
					search_movie_ui.main();
					break;
				case 2:
					ViewMovieDetailUI view_movie_detail_ui = new ViewMovieDetailUI();
					view_movie_detail_ui.main();
					break;
				case 3:
					//CheckSeatUI check_seat_ui = new CheckSeatUI();
					//check_seat_ui.main();
					break;
				case 4:

					break;
				case 5:
					break;
				case 6:
					ListTopMovieUI listTopMovieUI = new ListTopMovieUI();
					listTopMovieUI.main();
					break;
				case 7:
					RateMovieUI rateMovieUI = new RateMovieUI();
					rateMovieUI.display();
					break;
				case 8:
					System.out.println("Exiting...");
					exit = true;
					break;
				default:
					System.out.println("Try again!");
					break;
			}
					
		}

	}
	
	public static void register(){
		RegisterUI register_ui = new RegisterUI();
		register_ui.main();
		
	}
}
