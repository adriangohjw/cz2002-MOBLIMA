package Boundary;

import Controller.*;
import Model.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class CUR_Movie_Listing {
	private static MoviesController movieCtrl = new MoviesController();
	Scanner input = new Scanner(System.in);

	public void main() {
		boolean exit = false;
		while (!exit) {
			System.out.printf("Create/Update/Remove movie listing: \n" +
								"1. Create Movie Listing\n" +
								"2. Update Movie Listing\n" +
								"3. Remove Movie Listing\n" +
								"4. Return to Main Menu\n" +
								"Enter option: ");
			int option = input.nextInt();
			switch(option) {
			case 1: 
				createMovie();
				break;
			case 2:
				updateMovie();
				break;
			case 3:
				removeMovie();
				break;
			case 4:
				exit = true;
				break;
			}
		}
	}
	
	
public void createMovie() {

	System.out.println("Creating movie listing....");
	
	System.out.println("Enter movie title: ");
	String title = InputController.getStringFromUser();
	
	System.out.print("Movie types: \n" +
					   "1. 2D\n" +
					   "2. 3D\n" +
					   "3. Blockbuster\n" +
					   "Select movie type:");
	int typeNo = InputController.getIntFromUser();
	MovieType movieType;
	switch(typeNo) {
	case 1:
		movieType = MovieType.TWO_D;
		break;
	case 2:
		movieType = MovieType.THREE_D;
		break;
	case 3:
		movieType = MovieType.BLOCKBUSTER;
		break;
	default:
		System.out.println("Wrong input!\n" +
						   "Returning to menu...");
		return;
	}
	
	System.out.println("Enter movie synopsis: ");
	String synopsis = InputController.getStringFromUser();
	
	System.out.println("Enter movie rating (G / PG13 / NC16 / M18 / R21) :");
	String rating = InputController.getStringFromUser();
	
	System.out.println("Enter movie duration: ");
	double duration = InputController.getDoubleFromUser();
	
	System.out.println("Enter movie release date: ");
	LocalDate movieReleaseDate = InputController.getDateFromUser();
	
	System.out.println("Enter movie end date: ");
	LocalDate movieEndDate = InputController.getDateFromUser();
	
	if (movieEndDate.isBefore(movieReleaseDate)) {
		System.out.println("Invalid Movie End Date!\n" +
						   "Returning to menu");
		return;
	}
	
	System.out.println("Enter movie director: ");
	String director = InputController.getStringFromUser();
	
	System.out.println("Enter number of casts (atleast 2): ");
	int numCast = input.nextInt();
	if (numCast < 2) {
		System.out.println("Invalid number of casts!\n" +
						   "Returning to menu");
		return;
	}
	ArrayList<String> cast = new ArrayList<>();
	for (int i = 0; i < numCast; i++) {
		System.out.println("Enter name of cast " + i+1 + ":");
		cast.add(InputController.getStringFromUser());
		}
	
	movieCtrl.create(title, movieType, synopsis, rating, duration, movieReleaseDate, movieEndDate, director, cast);
	if (movieCtrl.read().isEmpty())
		return;
	System.out.println("Movie listing created....");
	}


public void updateMovie() {
    
	System.out.println("Updating movie...");
	SearchMovieUI listMovie = new SearchMovieUI();

	if(listMovie.listAllMovies()){
		System.out.println("Select movie to be updated: ");
		System.out.println("Movie id: ");
		int movie_id = InputController.getIntFromUser();
		if (movieCtrl.readByID(movie_id) == null) {
			System.out.println("Movie Id does not exist!" +
							   "Returning to menu...");
			return;
		}
		
		System.out.println("Select movie attribute to update");
		System.out.printf(  "1. Title\n" +
							"2. Type\n" +
							"3. Synopsis\n" +
							"4. Rating\n" +
							"5. Duration \n" +
							"6. Movie Release date\n" +
							"7. End of Showing date\n" +
							"8. Director\n" +
							"9. Cast\n" +
							"Enter option: ");
		int choice = InputController.getIntFromUser();

		switch(choice) {
			case 1:
				System.out.println("Enter new Title:");
				String title = InputController.getStringFromUser();
				movieCtrl.updateById(1, movie_id, title);
				break;
			case 2:
				System.out.print(  "Movie types: \n" +
								   "1. 2D\n" +
								   "2. 3D\n" +
								   "3. Blockbuster\n" +
								   "Select movie type:");
				int typeNo = InputController.getIntFromUser();
				MovieType movieType;
				switch(typeNo) {
				case 1:
					movieType = MovieType.TWO_D;
					break;
				case 2:
					movieType = MovieType.THREE_D;
					break;
				case 3:
					movieType = MovieType.BLOCKBUSTER;
					break;
				default:
					System.out.println("Wrong input!\n" +
									   "Returning to menu...");
					return;
				}
				movieCtrl.updateById(2, movie_id, movieType);
				break;
				
			case 3:
				System.out.println("Enter new Synopsis:");
				String synopsis = InputController.getStringFromUser();
				movieCtrl.updateById(3, movie_id, synopsis);
				break;

			case 4:
				System.out.println("Enter new Rating (G / PG13 / NC16 / M18 / R21) :");
				String rating = InputController.getStringFromUser();
				movieCtrl.updateById(4, movie_id, rating);
				break;

			case 5:
				System.out.println("Enter new Duration:");
				double duration = InputController.getDoubleFromUser();
				movieCtrl.updateById(5, movie_id, duration);
				break;

			case 6:
				System.out.println("Enter new Movie Release date:");
				LocalDate movieReleaseDate = InputController.getDateFromUser();
				if (movieReleaseDate.isAfter(movieCtrl.readByID(movie_id).getMovieEndDate())) {
					System.out.println("Invalid movie release date! \n" +
									   "Returning to menu...");
					return;
				}
				movieCtrl.updateById(6, movie_id, movieReleaseDate);
				break;

			case 7:
				System.out.println("Enter new End of Showing date:");
				LocalDate endOfShowing = InputController.getDateFromUser();
				if (endOfShowing.isBefore(movieCtrl.readByID(movie_id).getMovieReleaseDate())){
					System.out.println("Invalid movie release date! \n" +
									   "Returning to menu...");
					return;
				}
				movieCtrl.updateById(7, movie_id, endOfShowing);
				break;

			case 8:
				System.out.println("Enter new Director:");
				String director = InputController.getStringFromUser();
				movieCtrl.updateById(8, movie_id, director);
				break;

			case 9:
				ArrayList<String> cast = null;
				System.out.println("Enter number of new cast members (atleast 2): ");
				int i = InputController.getIntFromUser();
				if (i < 2) {
					System.out.println("Invalid number of casts!\n" +
									   "Returning to menu");
					return;
				}
				for (int j = 1; j <= i; j++) {
					System.out.println("Enter cast " + j + " :");
					cast.add(InputController.getStringFromUser());
				}
				movieCtrl.updateById(9,movie_id, cast);
				break;
			default:
				System.out.println("Returning to menu...");
				return;
		}
	}
	else{
		System.out.println("Returning to menu...");
	}
}
	
	

public void removeMovie(){
	System.out.println("Deleting movie...");
	SearchMovieUI listMovie = new SearchMovieUI();
	if(listMovie.listAllMovies()){
		System.out.println("Select movie to be deleted: ");
		System.out.println("Movie Id: ");
		int movieId = InputController.getIntFromUser();
		if (movieCtrl.readByID(movieId) == null) {
			System.out.println("Movie does not exist!" +
							   "Returning to menu");
			return;
		}
		movieCtrl.deleteById(movieId);
	}
	else{
		System.out.println("Returning to menu...");
	}
}

}
