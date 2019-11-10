package Boundary;

import Controller.*;
import Model.*;

import java.util.*;

public class SearchMovieUI {
    private String title;
    private String type;
    private String director;
    private String cast;
    private int option;
    private MoviesController moviesController = new MoviesController();

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
        ArrayList<Movie> movieList = moviesController.readByAttribute(0, title); //there are two readByAttribute, which to be used?
        movieList.forEach(movie -> printMovie(movie));
    }


    public void searchByType(){
        System.out.println("Enter movie type: ");
        type = sc.next();
        ArrayList<Movie> movieList = moviesController.readByAttribute(1, type);
        movieList.forEach(movie -> movie.toString());
    }

}
