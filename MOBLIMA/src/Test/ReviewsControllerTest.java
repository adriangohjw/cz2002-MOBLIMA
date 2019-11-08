package Test;

import java.util.ArrayList;

import Controller.ReviewsController;
import Model.Review;

public class ReviewsControllerTest {
    public static void main(String[] args) throws Exception {

        ReviewsController reviewsController = new ReviewsController();
        ArrayList<Review> reviewListing = new ArrayList<Review>();

        // creating test values
        reviewListing.add(new Review("adrian1@gmail.com", 3.0, "average"));
        reviewListing.add(new Review("adrian2@gmail.com", 3.0, "average"));

        // testing ReviewsController.create()
        System.out.println(".....Testing ReviewsController.create()");
        reviewListing.forEach(review->reviewsController.create(review));
        readAllAndPrint(reviewsController);

        // testing ReviewsController.read()
        System.out.println(".....Testing ReviewsController.read()");
        readAllAndPrint(reviewsController);
    }

    public static void readAllAndPrint(ReviewsController reviewsController){
        ArrayList<Review> reviewListing = reviewsController.read();       
        reviewListing.forEach(n->System.out.println(n));
    }
}