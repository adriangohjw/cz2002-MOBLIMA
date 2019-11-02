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
    private String movieRating;
    private String synopsis;
    private Date movieReleaseDate;
    private String director;
    private ArrayList<String> cast;
    private ArrayList<Rating> ratings;

    public enum MovieStatus{
        COMING_SOON,
        PREVIEW,
        NOW_SHOWING,
        END_OF_SHOWING
    }

    public Movie(String title, String type, String movieRating, String synopsis, Date movieReleaseDate, String director, ArrayList<String> cast){
        this.title = title;
        this.type = type;
        this.movieRating = movieRating;
        this.synopsis = synopsis;
        this.movieReleaseDate = movieReleaseDate;
        this.director = director;
        this.cast = cast;
        this.ratings = new ArrayList<Rating>();
    }

    public String getTitle(){
        return title;
    }

    public String getType(){
        return type;
    }

    public String getMovieRating(){
        return movieRating;
    }

    public String getSynopsis(){
        return synopsis;
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

    public void addRating(Rating rating){
        ratings.add(rating);
    }

    public ArrayList<Rating> getRatings(){
        return ratings;
    }

    public String getDetailsToString(){
        String details = "";
        details += "Title: " + title + "\n"
                + "Director: " + director +"\n"
                + "Type: " + type + "\n"
                + "Rating: " + movieRating + "\n"
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
        if(ratings.size()>1){
            for(Rating rating : ratings){
                sum += rating.getNumOfStars();
            }
            return String.valueOf(sum/ratings.size());
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
