package Boundary;

import java.io.IOException;
import java.util.Scanner; 

public class MainMenuUI {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to MOBLIMA!\n" +
						   "1. Admin\n" +
						   "2. Movie Goer\n" +
				   		   "3. New user\n" +
						   "4. Exit");
		int choice = input.nextInt();
		boolean exit = false;
		while (!exit) {
		switch(choice) {
			case 1:
				admin_login();
				break;
			case 2:
				movie_goer();
				break;
			case 3: 
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
	
	public static void admin_login() throws ClassNotFoundException, IOException {
		LoginUI admin_login = new LoginUI(1);
		admin_login.main();
		boolean log_out = false;
		Scanner input = new Scanner(System.in);
		while (log_out) {
			System.out.println("Select action:\n"+
					   "1.Create/Update/Remove movie listing\n"+
					   "2.Create/Update/Remove movie session\n"+
					   "3.Configure system settings\n" +
					   "4.Log out");
			int choice = input.nextInt();
			switch(choice) {
				case 1:
					CUR_Movie_Listing.main();
					break;
				case 2:
					CURshowtimes CUR2 = new CURshowtimes();
					CUR2.main();
					break;
				case 3:
					ConfigureSystemSetting Conf = new ConfigureSystemSetting();
					Conf.main();
					break;
				case 4:
					log_out = true;
					System.out.println("Logged out successfully!");
					break;
				default:
					System.out.println("Try again!");
					break;
			}
					
		}
	}
	
	public static void movie_goer() {
		LoginUI movie_goer_login = new LoginUI(0);
		Scanner input = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			System.out.println("Select action:\n"+
					   "1.Search/List movie\n"+
					   "2.View movie details\n"+
					   "3.Check seat availibility\n" +
					   "4.Book ticket\n" +
					   "5.View booking history\n" +
					   "6.List Top 5 movies\n" +
					   "7.exit");
			int choice = input.nextInt();
			switch(choice) {
				case 1:
					SearchMovieUI search_movie_ui = new SearchMovieUI();
					search_movie_ui.main();
					break;
				case 2:
					ViewMovieDetailUI view_movie_detail_ui = new ViewMovieDetailUI();
					view_movie_detail_ui.main();
					break;
				case 3:
					CheckSeatUI check_seat_ui = new CheckSeatUI();
					check_seat_ui.main();
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
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Try again!");
					break;
			}
					
		}

	}
}
