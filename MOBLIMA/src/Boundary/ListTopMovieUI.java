package Boundary;

import Controller.*;
import Model.*;

import java.io.IOException;
import java.util.*;

public class ListTopMovieUI {
	private int choice;
	private MoviesController moviesCtrl;

	public ListTopMovieUI() {
		this.moviesCtrl = new MoviesController();
	}

	public ListTopMovieUI(MoviesController moviesCtrl) {
		this.moviesCtrl = moviesCtrl;
	}

	Scanner sc = new Scanner(System.in);

	public void main(){
		while (choice != 3)
			display();
	}

	public void display() {
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

	public void listWithRatings(){
		int amount = 0;
		ArrayList<Movie> movieList = moviesCtrl.read(); // there are two readByAttribute, which to be used?
		Collections.sort(movieList, new SortByRating());

		if(amount < 5){
			amount = movieList.size();
		}

		for (int i = 0; i < amount; i++) {
			printMovie(movieList.get(i));
		}
		ViewMovieDetailUI view = new ViewMovieDetailUI();
		view.main();
	}

	public void listWithSales(){
		int amount = 0;
		ArrayList<Movie> movieList = moviesCtrl.read(); // there are two readByAttribute, which to be used?
		Collections.sort(movieList, new SortBySales());

		if(movieList.size()<5){
			amount = movieList.size();
		}

		for (int i = 0; i < amount; i++) {
			printMovie(movieList.get(i));
		}
		ViewMovieDetailUI view = new ViewMovieDetailUI();
		view.main();
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
		if(ratingA.equals("N/A") && ratingB.equals("N/A")) return 0;
		if(ratingA.equals("N/A")) return -1;
		if(ratingB.equals("N/A")) return 1;
		double difference = Double.parseDouble(ratingA) - Double.parseDouble(ratingB);
		if (difference > 0) return -1;
		if (difference < 0) return 1;
		return 0;
	}
}

class SortBySales implements Comparator<Movie> {
	public int compare(Movie a, Movie b) {
		TransactionsController transCtrl = new TransactionsController();
		ArrayList<Transaction> transList = transCtrl.read();
		int salesA = 0, salesB = 0;
		for (int i = 0; i < transList.size(); i++) {
			if (transList.get(i).getMovie().equals(a)) salesA++;
			if (transList.get(i).getMovie().equals(b)) salesB++;
		}
		return salesA - salesB;
	}
}
