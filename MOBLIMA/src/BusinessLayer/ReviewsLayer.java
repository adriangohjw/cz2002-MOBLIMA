package BusinessLayer;

import java.util.ArrayList;

import Controller.MoviesController;
import CustomException.ReviewsException.StarsOutOfRangeException;

import BusinessLayer.UsersLayer;

import Model.Movie;
import Model.Review;

public class ReviewsLayer {

    public static MoviesController moviesCtrl = new MoviesController();

    public static boolean isReviewValid(Movie movie, String username, double numOfStars, String additionalComment) {

        boolean isValid = true;

        if (UsersLayer.isExistingUser(username) == false)
            isValid = false;

        if (isNumOfStarsValid(numOfStars) == false)
            isValid = false;

        if (isExistingReview(movie, username))
            isValid = false;

        return isValid;
    }

    public static boolean isExistingReview(Movie movie, String username) {
        if (MoviesLayer.isExistingMovie(movie.getTitle())){
            Movie movieInDB = moviesCtrl.readByID(movie.getId());
            ArrayList<Review> reviews = movieInDB.getReviews();
            for (Review review : reviews) {
                if (review.getUsername().equals(username))
                    return true;
            }
        } 
        return false;
    }

    public static boolean isNumOfStarsValid(double numOfStars) {
        if (numOfStars < 0 || numOfStars > 5) {
            try {
                throw new StarsOutOfRangeException();
            } catch (StarsOutOfRangeException e) {
                System.out.println(e);
            }
            return false;
        } else {
            return true;
        }
    }
}