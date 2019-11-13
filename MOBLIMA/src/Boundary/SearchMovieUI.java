/* package Boundary;

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
        while(option!=4){
            display();
        }
    }


    public void display(){
        System.out.println("Hello, you can search for movie by: ");
        System.out.println("1. Search by movie title");
        System.out.println("2. Search by movie type");
        System.out.println("3. List all movie title");        
        System.out.println("4. Exit");
        option = sc.nextInt();
            switch(option){
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
            	break;
            default: 
            	System.out.println("Invalid input! Please try again.");
        }
    }


    public void searchByTitle(){
        System.out.println("Enter movie title: ");
        title = sc.next();
        ArrayList<Movie> movieList = moviesController.readByAttribute(0, title); //there are two readByAttribute, which to be used?
        movieList.forEach(movie -> printMovie(movie));
        viewMovieDetail();
    }


    public void searchByType(){
        System.out.println("Enter movie type: ");
        type = sc.next();
        ArrayList<Movie> movieList = moviesController.readByAttribute(1, type);
        movieList.forEach(movie -> printMovie(movie));
        viewMovieDetail();
    }
   
    
    public void viewMovieDetail(){
        System.out.println("Choose movie to view detail: ");
        title = sc.next();
        ViewMovieDetailUI viewMovieDetailUI = new ViewMovieDetailUI(title);
        viewMovieDetailUI.display();            
    }
    
    public void listAllMovies(){
        ArrayList<Movie> movieList = moviesController.read();
        movieList.forEach(movie -> printMovie(movie));
    }
    
    public void printMovie(Movie movie){
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Release Date: " + movie.getMovieReleaseDate());
        System.out.println("-------------------");
    }
}
 */