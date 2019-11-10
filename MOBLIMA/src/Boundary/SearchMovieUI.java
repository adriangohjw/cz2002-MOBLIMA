package Boundary;

import Controller.*;
import Model.*;

import java.util.*;

public class SearchMovieUI {
    private String title;
    private String type;
    // private String synopsis;
    // private Date movieReleaseDate;
    private String director;
    private String cast;
    private int option;

    Scanner sc = new Scanner(System.in);

    SearchMovieUI (){};

    public void main(){
        while(option!=3){
            display();
        }
    }


    public void display(){
        System.out.println("Hello, you can search for movie by: ");
        System.out.println("1. Search by movie title");
        System.out.println("2. Search by movie type");
        System.out.println("3. Exit");
        option = sc.nextInt();
            switch(option){
            case 1: searchByTitle();
                    break;
            case 2: searchByType();
                    break;
            case 3: System.out.println("Exit!");
        }
    }


    public void searchByTitle(){
        System.out.println("Enter movie title: ");
        title = sc.next();
        ArrayList<Movie> movieList = MoviesController.readByAttribute(0, title); //there are two readByAttribute, which to be used?
        movieList.forEach(movie -> printMovie(movie));
    }


    public void searchByType(){
        System.out.println("Enter movie type: ");
        type = sc.next();
        ArrayList<Movie> movieList = MoviesController.readByAttribute(1, type);
        movieList.forEach(movie -> printMovie(movie));
    }

    public void printMovie(Movie movie){
        System.out.println("Title: "+ movie.getTitle());
        System.out.println("Type: " + movie.getType());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Status: " + movie.getShowStatus());
        System.out.print("Director: " + movie.getDirector() + "  |  Cast: ");
        movie.getCast().forEach(cast -> System.out.print(cast + "   "));
        System.out.println("---------------------");
    }   

}
