package Boundary;

import Controller.*;
import Model.*;

import java.io.IOException;
import java.util.*;

public class ViewMovieDetailUI {
    private int id;
    private MoviesController moviesCtrl;
    Scanner sc = new Scanner(System.in);

    ViewMovieDetailUI(int _id) {
        this.id = _id;
        this.moviesCtrl = new MoviesController();
    };

    ViewMovieDetailUI(MoviesController moviesCtrl, int _id) {
        this.id = _id;
        this.moviesCtrl = moviesCtrl;
    }

    public void display() throws ClassNotFoundException, IOException {
        Movie movie = moviesCtrl.readByID(id);
        movie.toString();
    }   
}
