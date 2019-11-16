package Boundary;

import Controller.*;
import Model.*;

import java.io.IOException;
import java.util.*;

public class ViewMovieDetailUI {
    private int id;
    private MoviesController moviesCtrl;
    Scanner sc = new Scanner(System.in);

    ViewMovieDetailUI() {
        this.moviesCtrl = new MoviesController();
    };

    ViewMovieDetailUI(MoviesController moviesCtrl) {
        this.moviesCtrl = moviesCtrl;
    }

    public void main(){
        System.out.println("Enter movie ID to view movie detail: ");
        id = InputController.getIntFromUser();
        Movie movie = moviesCtrl.readByID(id);
        if(movie == null){
            System.out.println("Movie with this id doesn't exist!\n     Returning to main menu...");
        }
        else{
            String movieString = movie.toString();
            System.out.println(movieString);
        }
    }   
}
