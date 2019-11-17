package Model;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Movie implements Serializable {

    private int id;
    private String title;
    private MovieType type;
    private String synopsis;
    private String rating;
    private double duration;
    private LocalDate movieReleaseDate;
    private LocalDate movieEndDate;
    private String director;
    private ArrayList<String> cast;
    private ArrayList<Review> reviews; 
    

    public Movie(
            int id, String title, MovieType type, String synopsis, String rating, double duration, LocalDate movieReleaseDate, LocalDate movieEndDate, String director, ArrayList<String> cast
    ){
        this.id = id;
        this.title = title;
        this.type = type;
        this.synopsis = synopsis;
        this.rating = rating;
        this.duration = duration;  // in hours e.g. 1.5 == 1hours 30mins
        this.movieReleaseDate = movieReleaseDate;
        this.movieEndDate = movieEndDate;
        this.director = director;
        this.cast = cast;
        this.reviews = new ArrayList<Review>();
    }

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

	public String getTitle() {return this.title;}
    public void setTitle(String title){this.title = title;}

    public MovieType getType(){return this.type;}
    public void setType(MovieType type){this.type = type;}

    public String getSynopsis(){return this.synopsis;}
    public void setSynopsis(String synopsis){this.synopsis = synopsis;}
    
    public String getRating() {return this.rating;}
    public void setRating(String rating){this.rating = rating;}

    public double getDuration() {return this.duration;}
    public void setDuration(double duration) {this.duration = duration;}

    public LocalDate getMovieReleaseDate(){return this.movieReleaseDate;}
    public void setMovieReleaseDate(LocalDate movieReleaseDate){this.movieReleaseDate = movieReleaseDate;}

    public String getMovieReleaseDateToString(){
        return movieReleaseDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
    }

    public String getMovieEndDateToString(){
        return movieEndDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
    }

    public LocalDate getMovieEndDate(){return this.movieEndDate;}
    public void setMovieEndDate(LocalDate movieEndDate){this.movieEndDate = movieEndDate;}
    
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

        String reviews = "";
        for(int i = 0; i<getReviews().size();i++){
            reviews += getReviews().get(i).toString() + "\n\n";
        }
        if(reviews.equals(""))
            reviews = "N/A";

        String details = "";
        details += "ID: " + getId() + "\n"
                + "Title: " + getTitle() + "\n"
                + "Type: " + getType() + "\n"
                + "Status: " + getShowStatus().toString() + "\n"
                + "Synopsis: " + getSynopsis() + "\n"
                + "Rating: " + getRating() + "\n"
                + "Duration: " + String.valueOf(getDuration()) + " hour(s)\n"
                + "Release date: " +  getMovieReleaseDateToString() + "\n"
                + "End date: " +  getMovieEndDateToString() + "\n"
                + "Director: " + getDirector() + "\n"
                + "Cast: " + castString + "\n"
                + "Overall review rating: " + getOverallReviews() + "\n"
                + "Reviews: \n\n" + reviews;
        return details + "\n";
    }

    public String getOverallReviews(){
        double sum = 0;
        if(reviews.size()>1){
            for(Review review : reviews){
                sum += review.getNumOfStars();
            }
            DecimalFormat df = new DecimalFormat("#.##");
            return df.format(sum/reviews.size());
        }
        else {
            return "N/A";
        }
    }

    public MovieStatus getShowStatus() {
        LocalDate current = LocalDate.now();
        if (current.isAfter(movieEndDate))
            return MovieStatus.END_OF_SHOWING;
        else {
            float daysBetween = Duration.between(current.atStartOfDay(), movieReleaseDate.atStartOfDay()).toDays();
            if (daysBetween > 7) {
                return MovieStatus.COMING_SOON;
            } else if (daysBetween <= 7 && daysBetween > 0) {
                return MovieStatus.PREVIEW;
            } else {
                return MovieStatus.NOW_SHOWING;
            }
        }
    }

    @Override
    public boolean equals(Object movie) {
        if (!(movie instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) movie;
        return 
            this.id == other.getId()
            && this.title.equals(other.getTitle()) 
            && this.type.equals(other.getType()) 
            && this.synopsis.equals(other.getSynopsis()) 
            && this.rating.equals(other.getRating())
            && this.movieReleaseDate.equals(other.getMovieReleaseDate()) 
            && this.movieEndDate.equals(other.getMovieEndDate()) 
            && this.director.equals(other.getDirector()) 
            && this.cast.equals(other.getCast()) 
            && this.reviews.equals(other.getReviews());
    }
}
