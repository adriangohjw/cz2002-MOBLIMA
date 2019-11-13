package Boundary;

import Controller.*;
import Model.*;

import java.io.IOException;
import java.util.*;

public class ListTopMovieUI {
	private int choice;
	private String title;
	private MoviesController moviesCtrl;

	public ListTopMovieUI() {
		this.moviesCtrl = new MoviesController();
	}

	public ListTopMovieUI(MoviesController moviesCtrl) {
		this.moviesCtrl = moviesCtrl;
	}

	Scanner sc = new Scanner(System.in);

	public void main() throws ClassNotFoundException, IOException {
		while (choice != 3)
			display();
	}

	public void display() throws ClassNotFoundException, IOException {
		System.out.println("Select 1 to list top 5 movies according to ratings.");
		System.out.println("Select 2 to list top 5 movies according to ticket sales.");
		System.out.println("Select 3 to go back.");

		choice = sc.nextInt();
		switch (choice) {
		case 1:
			listWithRatings();
			break;
		case 2:
			listWithSales();
			break;
		case 3:
			System.out.println("Going back...");
			break;
		default:
			System.out.println("Invalid input! Please try again.");
		}
	}

	public void listWithRatings() throws ClassNotFoundException, IOException {
		ArrayList<Movie> movieList = moviesCtrl.read(); // there are two readByAttribute, which to be used?
		Collections.sort(movieList, new SortByRating());
		for (int i = 0; i < 5; i++) {
			printMovie(movieList.get(i));
		}
		viewMovieDetail();
	}

	public void listWithSales() throws ClassNotFoundException, IOException {
		ArrayList<Movie> movieList = moviesCtrl.read(); // there are two readByAttribute, which to be used?
		Collections.sort(movieList, new SortBySales());
		for (int i = 0; i < 5; i++) {
			printMovie(movieList.get(i));
		}
		viewMovieDetail();
	}

	public void viewMovieDetail() throws ClassNotFoundException, IOException {
        System.out.println("Choose movie to view detail: ");
        title = sc.next();
        ViewMovieDetailUI viewMovieDetailUI = new ViewMovieDetailUI(title);
        viewMovieDetailUI.display();            
    }
    
    public void printMovie(Movie movie){
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Rating: " + movie.getOverallReviews());
        System.out.println("-------------------");
    }
}

class SortByRating implements Comparator<Movie> {
	public int compare(Movie a, Movie b) {
		String ratingA = a.getOverallReviews();
		String ratingB = b.getOverallReviews();
		if(ratingA == "N/A" && ratingB == "N/A") return 0;
		if(ratingA == "N/A") return -1;
		if(ratingB == "N/A") return 1;
		double difference = Double.parseDouble(ratingA) - Double.parseDouble(ratingB);
		if (difference > 0) return 1;
		if (difference < 0) return -1;
		return 0;
	}
}

class SortBySales implements Comparator<Movie> {
	public int compare(Movie a, Movie b) {
		return 0; //implement a way to find the number of ticket sales
	}
}
