package Boundary;

import Controller.*;
import Model.*;

import java.io.IOException;
import java.util.*;

public class SearchMovieUI {
    private String title;
    private String type;
    private int option;
    private MoviesController moviesCtrl;

    SearchMovieUI() {
        this.moviesCtrl = new MoviesController();
    }

    SearchMovieUI(MoviesController moviesCtrl) {
        this.moviesCtrl = moviesCtrl;
    }


    public void main(){
        while (option!=4) {
            display();
        }
    }

    public void display(){
        System.out.println("1. Search by movie title");
        System.out.println("2. Search by movie type");
        System.out.println("3. List all movie title");
        System.out.println("4. Exit");
        switch (option = InputController.getIntFromUser()) {
        case 1:
            searchByTitle();
            break;
        case 2:
            searchByType();
            break;
        case 3:
            listAllMovies();
            break;
        case 4:
            System.out.println("Exit!");
            return;
        default:
            System.out.println("Invalid input! Please try again.");
        }
    }

    public boolean searchByTitle(){
        System.out.println("Enter movie title: ");
        title = InputController.getStringFromUser();
        ArrayList<Movie> movieList = moviesCtrl.readByAttribute(moviesCtrl.TITLE, title);
        if(movieList.isEmpty()){
            System.out.println("No search results matching given title!");
            return false;
        }
        else{
            movieList.forEach(movie -> printMovie(movie));
            return true;
        }
    }

    public boolean searchByType(){
        System.out.println("Enter movie type: ");
        type = InputController.getStringFromUser();
        ArrayList<Movie> movieList = moviesCtrl.readByAttribute(moviesCtrl.TYPE, type);
        if(movieList.isEmpty()){
            System.out.println("No search results matching given type!");
            return false;
        }
        else{
            movieList.forEach(movie -> printMovie(movie));
            return true;
        }
    }
   
    
    public boolean listAllMovies(){
        ArrayList<Movie> movieList = moviesCtrl.read();
        if(movieList.isEmpty()){
            System.out.println("No movies to be listed!");
            return false;
        }
        else{
            movieList.forEach(movie -> printMovie(movie));
            return true;
        }
    }
    
    public void printMovie(Movie movie){
        int id = movie.getId();
        String title = movie.getTitle();
        String releaseDate = movie.getMovieReleaseDateToString();
        String movieString = "Movie ID: " + id + "\n" + "Title: " + title + "\n" + "Release Date: " + releaseDate;
        System.out.println(movieString);
        System.out.println("-------------------");
    }
}
