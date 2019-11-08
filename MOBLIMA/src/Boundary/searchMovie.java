package Boundary;

import Controller.*;
import Model.*;

import java.util.*;

public class searchMovie {
    private String title;
    private String type;
    //private String synopsis;
    //private Date movieReleaseDate;
    private String director;
    private String cast;
    private int option;

    Scanner sc = new Scanner(System.in);

    searchMovie(){}

    public void main(){
        while(option!=5){
            display();
        }
    }


    public void display(){
        System.out.println("Hello, you can search for movie by: ");
        System.out.println("1. Search by movie title");
        System.out.println("2. Search by movie type");
        System.out.println("3. Search by director");
        System.out.println("4. Search by cast");
        System.out.println("5. Exit");
        option = sc.nextInt();
            switch(option){
            case 1: searchByTitle();
                    break;
            case 2: searchByType();
                    break;
            case 3: searchByDirector();
                    break;
            case 4: searchByCast();
                    break;
            case 5: System.out.println("Exit!");
        }
    }


    public void searchByTitle(){
        System.out.println("Enter movie title: ");
        title = sc.next();
        Movie[] movieList = movieRatingsController.readByTitle(title);
        movieList.forEach(movie -> printMovie(movie));
    }


    public void searchByType(){
        System.out.println("Enter movie type: ");
        type = sc.next();
        Movie[] movieList = movieRatingsController.readByType(type);
        movieList.forEach(movie -> printMovie(movie));
    }
    //How can search for Sysnopsis because the description cannot be exactly the same


    public void searchByDirector(){
        System.out.println("Enter director: ");
        director = sc.next();
        Movie[] movieList = movieRatingsController.readByDirector(director);
        movieList.forEach(movie -> printMovie(movie));
    }


    public void searchByCast(){
        System.out.println("Enter cast: ");
        cast = sc.next();
        Movie[] movieList = movieRatingsController.readByCast(cast);
        movieList.forEach(movie -> printMovie(movie));
    }


    public void printMovie(Movie movie){
        System.out.println(movie.getTitle());
        System.out.println(movie.getType());
        System.out.println(movie.getSynopsis());
        System.out.println(movie.getShowStatus());
        System.out.println("---------------------");
    }   

}
