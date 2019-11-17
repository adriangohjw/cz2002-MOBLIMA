package Controller;

import java.util.ArrayList;

import Model.*;

public class ReviewsController {

    private MoviesController movieCtrl;
    public String FILENAME;

    @SuppressWarnings("static-access")
    public ReviewsController(){
        this.movieCtrl = new MoviesController();
        this.FILENAME = movieCtrl.FILENAME;
    }

    @SuppressWarnings("static-access")
    public ReviewsController(MoviesController movieCtrl){
        this.movieCtrl = movieCtrl;
        this.FILENAME = movieCtrl.FILENAME;
    }

    public void create(Movie movie, String username, double numOfStars, String additionalComment) {
        Review review = new Review(username, numOfStars, additionalComment);
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

    public MoviesController getMovieCtrl(){
        return this.movieCtrl;
    }
}