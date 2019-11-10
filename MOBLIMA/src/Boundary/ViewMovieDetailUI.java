package Boundary;

import Controller.*;
import Model.*;
import Model.Movie;

import java.util.*;

public class ViewMovieDetailUI {
    private String title;
    private int another = 1;
    private MoviesController moviesController = new MoviesController();
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
        ArrayList<Movie> movieList = moviesController.readByAttribute(0, title);
        movieList.forEach(movie -> movie.toString());
        
        System.out.println("Another movie? 1 for [y] and 0 for [no]");
        another = sc.nextInt();

        if(another==0){
            System.out.println("Exit!");
        }
    }
    
}
