package Boundary;

import Controller.*;
import Model.*;

import java.io.IOException;
import java.util.*;

public class SearchMovieUI {
    private String title;
    private String type;
    private String director;
    private String cast;
    private int option;
    private int id;
    private MoviesController moviesCtrl;

    SearchMovieUI() {
        this.moviesCtrl = new MoviesController();
    }

    SearchMovieUI(MoviesController moviesCtrl) {
        this.moviesCtrl = moviesCtrl;
    }

    Scanner sc = new Scanner(System.in);

    public void main() throws ClassNotFoundException, IOException {
        while (option != 5) {
            display();
        }
    }

    public void display() throws ClassNotFoundException, IOException {
        System.out.println("Hello, you can search for movie by: ");
        System.out.println("1. Search by movie title");
        System.out.println("2. Search by movie type");
        System.out.println("3. List all movie title");
        System.out.println("4. View movie detail");
        System.out.println("5. Exit");
        option = sc.nextInt();
        switch (option) {
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
             viewMovieDetail();
             break;
        case 5:
            System.out.println("Exit!");
            break;
        default:
            System.out.println("Invalid input! Please try again.");
        }
    }

    public void searchByTitle() throws ClassNotFoundException, IOException {
        System.out.println("Enter movie title: ");
        title = sc.next();
        ArrayList<Movie> movieList = moviesCtrl.readByAttribute(moviesCtrl.TITLE, title);
        movieList.forEach(movie -> printMovie(movie));
    }

    public void searchByType() throws ClassNotFoundException, IOException {
        System.out.println("Enter movie type: ");
        type = sc.next();
        ArrayList<Movie> movieList = moviesCtrl.readByAttribute(moviesCtrl.TYPE, type);
        movieList.forEach(movie -> printMovie(movie));
    }
   
    public void viewMovieDetail() throws ClassNotFoundException, IOException {
        System.out.println("Enter movie ID to view movie detail: ");
        id = sc.nextInt();
        ViewMovieDetailUI viewMovieDetailUI = new ViewMovieDetailUI(id);
        viewMovieDetailUI.display();            
    }
    
    public void listAllMovies(){
        ArrayList<Movie> movieList = moviesCtrl.read();
        movieList.forEach(movie -> printMovie(movie));
    }
    
    public void printMovie(Movie movie){
        System.out.println("Movie ID: " + movie.getID());
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Release Date: " + movie.getMovieReleaseDate());
        System.out.println("-------------------");
    }
}
