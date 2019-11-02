package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Session {

	private int id;
	private Movie movie;
	private Date sessionDate;
	private String sessionTime;
	private SeatingPlan seatsAvailability;

    public Session(int id, Movie movie, Date sessionDate, String sessionTime, SeatingPlan seatingPlan){
    	this.id = id;
    	this.movie = movie;
    	this.sessionDate = sessionDate;
    	this.sessionTime = sessionTime;
    	this.seatsAvailability = seatingPlan;
    }
    
    public int getId() {
    	return id;
    }

    public Movie getMovie() {
        return movie;
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

}
