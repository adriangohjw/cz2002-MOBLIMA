package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Session {

	private Cinema cinema;
	private Movie movie;
	private Date sessionDate;
	private String sessionTime;
	private SeatsAvailability seatsAvailability;

    public Session(Cinema cinema, Movie movie, Date sessionDate, String sessionTime){
    	this.cinema = cinema;
    	this.movie = movie;
    	this.sessionDate = sessionDate;
    	this.sessionTime = sessionTime;
    	this.seatsAvailability = new SeatsAvailability;
    }

    public Movie getMovie() {
        return movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Date getSessionDate() {
        return sessionDate;
    }
    
    public String getSessionTime() {
    	return sessionTime;
    }

    public String getSessionDateToString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(sessionDate);
    }

    public String getSessionDetails(){
        return getSessionDateToString() + ", " + movie.getTitle() + ", " + cinema.getName();
    }
}
