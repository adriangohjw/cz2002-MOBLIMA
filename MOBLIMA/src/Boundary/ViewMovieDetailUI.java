package Boundary;

import Controller.*;
import Model.*;

import java.util.*;

public class ViewMovieDetailUI {
    private MoviesController moviesCtrl;
    Scanner sc = new Scanner(System.in);

    ViewMovieDetailUI() {
        this.moviesCtrl = new MoviesController();
    };

    ViewMovieDetailUI(MoviesController moviesCtrl) {
        this.moviesCtrl = moviesCtrl;
    }

    public void main(){
        System.out.println("Enter movie ID to view movie detail (-1 to exit): ");
        int id = InputController.getIntFromUser();
        if(id == -1){
            return;
        }
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
