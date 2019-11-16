package Boundary;

import Controller.InputController;
import Controller.MoviesController;
import Controller.ReviewsController;
import Model.Movie;
import Model.Review;

import java.util.ArrayList;

public class RateMovieUI {
    private MoviesController moviesController = new MoviesController();
    private ReviewsController reviewsController = new ReviewsController();
    private SearchMovieUI searchMovieUI = new SearchMovieUI();

    public void display(){
        System.out.println("Input your username: ");
        String username = InputController.getStringFromUser();
        if(searchMovieUI.listAllMovies()){
            System.out.print("Select the title of movie which you want to rate: ");
            String title = InputController.getStringFromUser();
            ArrayList<Movie> movie = moviesController.readByAttribute(MoviesController.TITLE, title);
            System.out.println("Input number of stars (0-5):");
            double stars = InputController.getDoubleFromUser(5);
            System.out.println("Input additional comment");
            String comment = InputController.getStringFromUser();
            reviewsController.create(movie.get(0),new Review(username, stars, comment));
        }
    }
}
