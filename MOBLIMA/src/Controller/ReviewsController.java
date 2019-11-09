package Controller;

import java.util.ArrayList;

import Model.*;

public class ReviewsController {

    private MoviesController movieCtrl;
    final String FILENAME = movieCtrl.FILENAME;

    public ReviewsController(MoviesController movieCtrl){
        this.movieCtrl = movieCtrl;
    }

    public void create(Movie movie, Review review) {
        ArrayList<Movie> allData = this.movieCtrl.read();
        ArrayList<Movie> returnData = new ArrayList<Movie>();
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);
            if (m.equals(movie)){
                ArrayList<Review> reviews = m.getReviews();
                reviews.add(review);
                m.setReviews(reviews);
            }
            returnData.add(m);
        }
        this.movieCtrl.replaceExistingFile(FILENAME, returnData);
    } 
}