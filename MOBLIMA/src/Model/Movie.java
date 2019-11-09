package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

public class Movie implements Serializable {

    private String title;
    private String type;
    private String synopsis;
    private String rating;
    private String movieReleaseDate;
    private String director;
    private ArrayList<String> cast;  // stored in DB as String, delimited by ||
    private ArrayList<Review> reviews;  // stored in DB as String, delimited by ||s

    public enum MovieStatus{
        COMING_SOON,
        PREVIEW,
        NOW_SHOWING,
        END_OF_SHOWING
    }

    public Movie(String title, String type, String synopsis, String rating, String movieReleaseDate, String director, ArrayList<String> cast){
        this.title = title;
        this.type = type;
        this.synopsis = synopsis;
        this.rating = rating;
        this.movieReleaseDate = movieReleaseDate;
        this.director = director;
        this.cast = cast;
        this.reviews = new ArrayList<Review>();
    }

	public String getTitle() {return this.title;}
    public void setTitle(String title){this.title = title;}

    public String getType(){return this.type;}
    public void setType(String type){this.type = type;}

    public String getSynopsis(){return this.synopsis;}
    public void setSynopsis(String synopsis){this.synopsis = synopsis;}
    
    public String getRating() {return this.rating;}
    public void setRating(String rating){this.rating = rating;}

    public String getMovieReleaseDate(){return this.movieReleaseDate;}
    public void setMovieReleaseDate(String movieReleaseDate){this.movieReleaseDate = movieReleaseDate;}

    public String getDirector(){return this.director;}
    public void setDirector(String director){this.director = director;}

    public ArrayList<String> getCast(){return this.cast;}
    public void setCast(ArrayList<String> cast){this.cast = cast;}

    public ArrayList<Review> getReviews(){return this.reviews;}
    public void setReviews(ArrayList<Review> reviews){this.reviews = reviews;}

    public String toString(){
        String castString = "";
        for (int i=0; i<getCast().size(); i++)
            castString = castString.concat(getCast().get(i) + ",");
        castString = castString.substring(0, castString.length()-1);

        String details = "";
        details += "Title: " + getTitle() + "\n"
                + "Type: " + getType() + "\n"
                + "Synopsis: " + getSynopsis() + "\n"
                + "Rating: " + getRating() + "\n"
                + "Release date: " +  getMovieReleaseDate() + "\n"
                + "Director: " + getDirector() + "\n"
                + "Cast: " + castString + "\n"
                + "Overall review rating: " + getOverallReviews();       
        return details + "\n";
    }

    public String getOverallReviews(){
        double sum = 0;
        if(reviews.size()>1){
            for(Review review : reviews){
                sum += review.getNumOfStars();
            }
            return String.valueOf(sum/reviews.size());
        }
        else {
            return "N/A";
        }
    }

    public MovieStatus getShowStatus() throws ParseException{
        Date current = new Date();
        Date Date_movieReleaseDate = new SimpleDateFormat("YYYY-mm-dd").parse(getMovieReleaseDate());
        long difference = Date_movieReleaseDate.getTime() - current.getTime();
	    float daysBetween = (difference / (1000*60*60*24));
        if(daysBetween>7){
            return MovieStatus.PREVIEW;
        }
        else if(daysBetween<=7 && daysBetween>0){
            return MovieStatus.COMING_SOON;
        }
        else if(daysBetween<-30){
            return MovieStatus.END_OF_SHOWING;
        }
        else{
            return MovieStatus.NOW_SHOWING;
        }
    }

    @Override
    public boolean equals(Object movie) {
        if (!(movie instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) movie;
        return 
            this.title.equals(other.getTitle()) 
            && this.type.equals(other.getType()) 
            && this.synopsis.equals(other.getSynopsis()) 
            && this.rating.equals(other.getRating())
            && this.movieReleaseDate.equals(other.getMovieReleaseDate()) 
            && this.director.equals(other.getDirector()) 
            && this.cast.equals(other.getCast()) 
            && this.reviews.equals(other.getReviews());
    }
}
