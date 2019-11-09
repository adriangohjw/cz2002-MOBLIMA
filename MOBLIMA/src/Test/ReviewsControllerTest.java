package Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Controller.MoviesController;
import Controller.ReviewsController;
import Model.*;

public class ReviewsControllerTest {
    public static void main(String[] args) throws Exception {

        MoviesController movieCtrl = new MoviesController();
        new File(movieCtrl.FILENAME).delete();
        ReviewsController reviewsController = new ReviewsController(movieCtrl);
        
        // creating test values
        ArrayList<Movie> movieListing = new ArrayList<Movie>();
        ArrayList<Review> reviewListing = new ArrayList<Review>();
        String currentDate = new SimpleDateFormat("YYYY-MM-dd").format(new Date());

        Review review1 = new Review("adrian1@gmail.com", 3.0, "average");
        Review review2 = new Review("adrian2@gmail.com", 3.0, "average");

        ArrayList<String> listCast =  new ArrayList<String>();
        listCast.add("A_cast1");
        listCast.add("A_cast2");
        Movie movie1 = new Movie("A", "Blockbuster", "A_synopsis", "NC16", currentDate, "A_director", listCast);

        listCast.clear();
        listCast.add("B_cast1");
        listCast.add("B_cast2");
        reviewListing.clear();
        reviewListing.add(review2);
        Movie movie2 = new Movie("B", "Blockbuster", "B_synopsis", "NC16", currentDate, "B_director", listCast);
        movie2.setReviews(reviewListing);

        movieListing.add(movie1);
        movieListing.add(movie2);
        movieListing.forEach(movie->movieCtrl.create(movie));

        // testing ReviewsController.create()
        System.out.println(".....Testing ReviewsController.create() - Before");
        readAllAndPrint(movieCtrl);
        System.out.println(".....Testing ReviewsController.create() - After (1)");
        reviewsController.create(movie1, review1);
        readAllAndPrint(movieCtrl);
        System.out.println(".....Testing ReviewsController.create() - After (2)");
        reviewsController.create(movie2, review1);
        readAllAndPrint(movieCtrl);
    }

    public static void readAllAndPrint(MoviesController movieCtrl){   
        ArrayList<Movie> movieListing = movieCtrl.read();
        ArrayList<Review> reviewListing;
        Movie m;
        for (int i=0; i<movieListing.size(); i++){
            m = movieListing.get(i);
            System.out.println("Movie: " + m.getTitle());
            reviewListing = m.getReviews();
            for (int j=0; j<reviewListing.size(); j++){
                System.out.println(reviewListing.get(j));
            }
        }  
    }
}