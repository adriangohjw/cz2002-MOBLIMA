package Boundary;

import Controller.*;
import Model.*;
import Model.Movie;

import java.util.*;

public class viewMovieDetail{
    private String title;
    private int another = 1;
    Scanner sc = new Scanner(System.in);

    viewMovieDetail(){}

    public void main(){
        while(another==1){
            display();
        }
    }


    public void display(){
        System.out.println("Enter the movie title");
        title = sc.next();
        Movie[] movieList = movieRatingsController.readByTitle(title);
        movieList.forEach(movie -> printMovie(movie));
        
        System.out.println("Another movie? 1 for [y] and 0 for [no]");
        another = sc.nextInt();

        if(another==0){
            System.out.println("Exit!");
        }
    }

    public void printMovie(Movie movie){
        System.out.println(movie.getTitle());
        System.out.println(movie.getType());
        System.out.println(movie.getSynopsis());
        System.out.println(movie.getShowStatus());
        System.out.println(movie.getOverallRating());
        System.out.println(movie.getReviews());
        System.out.println(movie.getDirector());
        System.out.println(movie.getCast());
        System.out.println("---------------------");
    }
    
}
