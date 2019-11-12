package Boundary;

import Controller.*;
import Model.*;

import java.io.IOException;
import java.util.*;

public class CUR_Movie_Listing {
	private static MoviesController movieCtrl = new MoviesController();


	public static void main() throws ClassNotFoundException, IOException{
		Scanner input = new Scanner(System.in);
		System.out.println("Create/Update/Remove movie listing: /n" +
							"1. Create Movie Listing/n" +
							"2. Update Movie Listing/n" +
							"3. Remove Movie Listing");
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
		}
	}
	
public static void createMovie()throws ClassNotFoundException, IOException{
	Scanner input = new Scanner(System.in);
	System.out.println("Creating movie listing...");
	System.out.println("Enter movie title: ");
	String title = input.nextLine();
	System.out.println("Enter movie type: ");
	String type = input.nextLine();
	System.out.println("Enter movie synopsis: ");
	String synopsis = input.nextLine();
	System.out.println("Enter movie rating: ");
	String rating = input.nextLine();
	System.out.println("Enter movie release date: ");
	String movieReleaseDate = input.nextLine();
	System.out.println("Enter movie director: ");
	String director = input.nextLine();
	System.out.println("Enter number of casts: ");
	int numCast = input.nextInt();
	ArrayList<String> cast = null;
	for (int i = 0; i < numCast; i++) {
		System.out.println("Enter name of cast " + i+1 + ":");
		cast.add(input.nextLine());		
		}
	movieCtrl.create(title, type, synopsis, rating, movieReleaseDate, director, cast);
	System.out.println("Movie listing created.");
	}

public static void updateMovie() throws ClassNotFoundException, IOException {
	Scanner input = new Scanner(System.in);
	String attr = ""; 
	System.out.println("Updating movie...");
	SearchMovieUI listMovie = SearchMovieUI();
	System.out.println("Select movie to be updated: ");
	listMovie.listAllMovies();
	System.out.println("Movie id: ");
	int movie_id = input.nextInt();
	System.out.print("Select movie attribute to update");
	System.out.println("1. title\n" + 
					   "2. type\n" +
					   "3. rating\n" +
					   "4. movie release date\n");
	int choice = input.nextInt();
	switch(choice) {
	case 1:
		attr = "TITLE";
		break;
	case 2:
		attr = "TYPE";
		break;
	case 3:
		attr = "RATING";
		break;
	case 4:
		attr = "MOVIE_RELEASE_DATE";
		break;
		}
	System.out.println("Enter new " + attr + " :");
	String newValue = input.nextLine();
	movieCtrl.updateByAttribute(choice, newValue);
	}
	
	

public static void removeMovie() throws ClassNotFoundException, IOException {
	Scanner input = new Scanner(System.in);
	System.out.println("Deleting movie...");
	SearchMovieUI listMovie = SearchMovieUI();
	System.out.println("Select movie to be updated: ");
	listMovie.listAllMovies();
	System.out.println("Movie Id: ");
	int movieId = input.nextInt();
	movieCtrl.deleteByAttribute(movieId);
}

}
