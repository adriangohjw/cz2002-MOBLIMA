package Boundary;

import Controller.*;
import Model.*;
import Model.Movie;

import java.util.*;

public class ViewMovieDetailUI {
    private String title;
    private int another = 1;
    Scanner sc = new Scanner(System.in);

    ViewMovieDetailUI(){};

    public void main(){
        while(another==1){
            display();
        }
    }


    public void display(){
        System.out.println("Enter the movie title");
        title = sc.next();
        ArrayList<Movie> movieList = MoviesController.readByAttribute(0, title);
        movieList.forEach(movie -> printMovie(movie));
        
        System.out.println("Another movie? 1 for [y] and 0 for [no]");
        another = sc.nextInt();

        if(another==0){
            System.out.println("Exit!");
        }
    }

    public void printMovie(Movie movie){
        System.out.println("Title: "+ movie.getTitle());
        System.out.println("Type: " + movie.getType());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Status: " + movie.getShowStatus());
        System.out.print("Director: " + movie.getDirector() + "  |  Cast: ");
        movie.getCast().forEach(cast -> System.out.print(cast + "   "));
        System.out.println("Rating: " + movie.getRating());
        System.out.println("Review: ");
        ArrayList<Review> reviews = movie.getReviews();
        int size = reviews.size();
        if(size <=3 ){
            reviews.forEach(review->printReview(review));
        } else {
            for (int i=0; i<3; i++){
                printReview(reviews.get(i));
            }
        }
        
        System.out.println("---------------------");
    }
    
    public void printReview(Review review){
        System.out.print("Star: " + review.getNumOfStars());
        if(review.getAdditionalComment() != null){
            System.out.println(" | " + review.getAdditionalComment());
        }
    }
    
}
