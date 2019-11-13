package Boundary;

import Controller.*;
import Model.*;

import java.io.IOException;
import java.util.*;

public class ViewMovieDetailUI {
    private String title;
    private MoviesController moviesCtrl;
    Scanner sc = new Scanner(System.in);

    ViewMovieDetailUI(String _title) {
        this.title = _title;
        this.moviesCtrl = new MoviesController();
    };

    ViewMovieDetailUI(MoviesController moviesCtrl, String _title) {
        this.title = _title;
        this.moviesCtrl = moviesCtrl;
    }

    public void display() throws ClassNotFoundException, IOException {
        ArrayList<Movie> movieList = moviesCtrl.readByAttribute(moviesCtrl.TITLE, title);
        movieList.forEach(movie -> System.out.println(movie.toString()));
    }   
}