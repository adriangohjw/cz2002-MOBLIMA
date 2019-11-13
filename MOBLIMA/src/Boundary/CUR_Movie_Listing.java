package Boundary;

import Controller.*;
import Model.*;
import java.io.IOException;
import java.util.*;

public class CUR_Movie_Listing {
	private static MoviesController movieCtrl = new MoviesController();
	private Scanner input = new Scanner(System.in);

	public void main() throws ClassNotFoundException, IOException{
		System.out.printf("Create/Update/Remove movie listing: \n" +
							"1. Create Movie Listing\n" +
							"2. Update Movie Listing\n" +
							"3. Remove Movie Listing\n" +
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
		}
	}
	
	
public void createMovie()
	throws ClassNotFoundException, IOException{

	System.out.println("Creating movie listing....");
	
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
	
	System.out.println("Enter movie end date: ");
	String movieEndDate = input.nextLine();
	
	System.out.println("Enter movie director: ");
	String director = input.nextLine();
	
	System.out.println("Enter number of casts: ");
	int numCast = input.nextInt();
	ArrayList<String> cast = null;
	for (int i = 0; i < numCast; i++) {
		System.out.println("Enter name of cast " + i+1 + ":");
		cast.add(input.nextLine());		
		}
	MovieType movieType = null;
	for(MovieType m: MovieType.values())
		if(m.toString()==type)
		{
			movieType=m;
			break;
		}
	movieCtrl.create(title, movieType, synopsis, rating, movieReleaseDate, movieEndDate, director, cast);
	System.out.println("Movie listing created....");
	}


public void updateMovie() 
		throws ClassNotFoundException, IOException {
	String attr = ""; int i; 
	
	System.out.println("Updating movie...");
	SearchMovieUI listMovie = new SearchMovieUI();
	System.out.println("Select movie to be updated: ");
	listMovie.listAllMovies();
	System.out.println("Movie id: ");
	int movie_id = input.nextInt();
	System.out.println("Select movie attribute to update");
	System.out.printf("1. Title\n" + 
					   "2. Type\n" +
					   "3. Synopsis\n" +
					   "4. Rating\n" +
					   "5. Movie Release date\n" +
					   "6. End of Showing date\n" +
					   "7. Director\n" +
					   "8. Cast\n" +
					   "Enter option: ");
	int choice = input.nextInt();
	
	switch(choice) {
	case 1:
		System.out.println("Enter new Title:");
		String title = input.nextLine();
		movieCtrl.updateById(1, movie_id, title);
		break;
	case 2:
		System.out.println("Enter new Type:");
		String type = input.nextLine();
		movieCtrl.updateById(2, movie_id, type);
		break;
		
	case 3:
		System.out.println("Enter new Synopsis:");
		String synopsis = input.nextLine();
		movieCtrl.updateById(3, movie_id, synopsis);
		break;
		
	case 4:
		System.out.println("Enter new Rating:");
		String rating = input.nextLine();
		movieCtrl.updateById(4, movie_id, rating);
		break;
		
	case 5:
		System.out.println("Enter new Movie Release date:");
		String movieReleaseDate = input.nextLine();
		movieCtrl.updateById(5, movie_id, movieReleaseDate);
		break;
		
	case 6: 
		System.out.println("Enter new End of Showing date:");
		String endOfShowing = input.nextLine();
		movieCtrl.updateById(6, movie_id, endOfShowing);
		break;
		
	case 7: 
		System.out.println("Enter new Director:");
		String director = input.nextLine();
		movieCtrl.updateById(6, movie_id, director);
		break;
		
	case 8:
		ArrayList<String> cast = null;
		System.out.println("Enter number of new cast members: ");
		i = input.nextInt();
		for (int j = 1; j <= i; j++) {
			System.out.println("Enter cast " + j + " :");
			cast.add(input.nextLine());
		}
		movieCtrl.updateById(8,movie_id, cast);
		break;
	}
}
	
	

public void removeMovie() throws ClassNotFoundException, IOException {
	System.out.println("Deleting movie...");
	SearchMovieUI listMovie = new SearchMovieUI();
	System.out.println("Select movie to be deleted: ");
	listMovie.listAllMovies();
	System.out.println("Movie Id: ");
	int movieId = input.nextInt();
	movieCtrl.deleteById(movieId);
}

}
