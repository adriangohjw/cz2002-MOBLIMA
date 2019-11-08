package Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Controller.*;
import static Controller.MoviesController.FILENAME;
import static Controller.MoviesController.TITLE;
import static Controller.MoviesController.TYPE;
import static Controller.MoviesController.SYNOPSIS;
import static Controller.MoviesController.RATING;
import static Controller.MoviesController.MOVIE_RELEASE_DATE;
import static Controller.MoviesController.DIRECTOR;
import static Controller.MoviesController.CAST;
import static Controller.MoviesController.REVIEWS;

import Model.*;

public class MoviesControllerTest {
    public static void main(String[] args) throws Exception {

        new File(FILENAME).delete();
        
        MoviesController moviesController = new MoviesController();
        ArrayList<Movie> movieListing = new ArrayList<Movie>();

        // creating test values
        String currentDate = new SimpleDateFormat("YYYY-MM-dd").format(new Date());

        ArrayList<String> listCast =  new ArrayList<String>();
        listCast.add("A_cast1");
        listCast.add("A_cast2");
        Movie movie1 = new Movie("A", "Blockbuster", "A_synopsis", "NC16", currentDate, "A_director", listCast);

        listCast.clear();
        listCast.add("B_cast1");
        listCast.add("B_cast2");
        Movie movie2 = new Movie("B", "Blockbuster", "B_synopsis", "NC16", currentDate, "B_director", listCast);

        movieListing.add(movie1);
        movieListing.add(movie2);

        // Testing MoviesController.create()
        System.out.println(".....Testing MoviesController.create()");
        movieListing.forEach(movie->moviesController.create(movie));
        readAllAndPrint(moviesController.read());

        // testing MoviesController.read()
        System.out.println(".....Testing MoviesController.read()");
        readAllAndPrint(moviesController.read());

        // testing MoviesController.readByAttribute()
        System.out.println(".....Testing MoviesController.readByAttribute() - Test 1");
        movieListing = moviesController.readByAttribute(TITLE, "A");
        readAllAndPrint(movieListing);
        System.out.println(".....Testing MoviesController.readByAttribute() - Test 2");
        movieListing = moviesController.readByAttribute(RATING, "NC16");
        readAllAndPrint(movieListing);

        // testing MoviesController.updateByAttribute()
        System.out.println(".....Testing MoviesController.updateByAttribute()");
        moviesController.updateByAttribute(MOVIE_RELEASE_DATE, "2019-11-08", "2019-12-08");
        readAllAndPrint(moviesController.read());

        // testing MoviesController.delete()
        System.out.println(".....Testing MoviesController.delete()");
        moviesController.deleteByAttribute(DIRECTOR, "B_director");
        readAllAndPrint(moviesController.read());
    }

    public static void readAllAndPrint(ArrayList<Movie> movieListing){     
        movieListing.forEach(n->System.out.println(n));
    }
}