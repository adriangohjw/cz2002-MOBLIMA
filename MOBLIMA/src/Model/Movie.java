package Model;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.SimpleFormatter;

public class Movie {

    private String title;
    private String type;
    private String synopsis;
    private String rating;
    private Date movieReleaseDate;
    private String director;
    private ArrayList<String> cast;
    private ArrayList<Review> reviews;

    public enum MovieStatus{
        COMING_SOON,
        PREVIEW,
        NOW_SHOWING,
        END_OF_SHOWING
    }

    public Movie(String title, String type, String synopsis, String rating, Date movieReleaseDate, String director, ArrayList<String> cast){
        this.title = title;
        this.type = type;
        this.synopsis = synopsis;
        this.rating = rating;
        this.movieReleaseDate = movieReleaseDate;
        this.director = director;
        this.cast = cast;
        // no ratings when movie is instantiated
    }

    public String getTitle(){
        return title;
    }

    public String getType(){
        return type;
    }

    public String getSynopsis(){
        return synopsis;
    }
    
    public String getRating() {
    	return rating;
    }

    public Date getMovieReleaseDate(){
        return movieReleaseDate;
    }

    public String getMovieReleaseDateToString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(movieReleaseDate);
    }

    public String getDirector(){
        return director;
    }

    public ArrayList<String> getCast(){
        return cast;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public ArrayList<Review> getReviews(){
        return reviews;
    }

    public String getDetailsToString(){
        String details = "";
        details += "Title: " + title + "\n"
                + "Director: " + director +"\n"
                + "Type: " + type + "\n"
                + "Rating: " + getOverallRating() + "\n"
                + "Release date: " + getMovieReleaseDateToString() + "\n"
                + "Synopsis: " + synopsis + "\n"
                + "Overall review rating: " + getOverallRating() + "\n"
                + "Cast: ";
        for(String actor : cast){
            details += actor + ", ";
        }
        return details + "\n";
    }

    public String getOverallRating(){
        double sum = 0;
        if(reviews.size()>1){
            for(Review review : reviews){
                sum += review.getNumOfStars();
            }
            return String.valueOf(sum/reviews.size());
        }
        else{
            return "N/A";
        }
    }

    public MovieStatus getShowStatus(){
        Date current = new Date();
        long differenceInDays = TimeUnit.MILLISECONDS.toDays(movieReleaseDate.getTime() - current.getTime());
        if(differenceInDays>7){
            return MovieStatus.PREVIEW;
        }
        else if(differenceInDays<=7 && differenceInDays>0){
            return MovieStatus.COMING_SOON;
        }
        else if(differenceInDays<-30){
            return MovieStatus.END_OF_SHOWING;
        }
        else{
            return MovieStatus.NOW_SHOWING;
        }
    }

}
