package Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Session implements Serializable {

	private Movie movie;
	private String sessionDate;  // dd/MM/yyyy
	private String sessionTime;  // hh:mm
    private SeatingPlan seatsAvailability;

    public Session(Movie movie, String sessionDate, String sessionTime, SeatingPlan seatingPlan){
    	this.movie = movie;
    	this.sessionDate = sessionDate;
    	this.sessionTime = sessionTime;
        this.seatsAvailability = seatingPlan;
    }
    
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }
    
    public String getSessionTime() {
    	return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public SeatingPlan getSeatsAvailability(){
        return this.seatsAvailability;
    }

    public void setSeatsAvailability(SeatingPlan seatsAvailability){
        this.seatsAvailability = seatsAvailability;
    }

    public String getSessionDateToString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(sessionDate);
    }

    /* INCOMPLETE
    public double getPrice(){
        double basePrice = 8.5;
        boolean hasWeekendMarkup;

        Calendar c1, c2 = Calendar.getInstance();
        c1.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(this.sessionDate));
        int dayOfWeek = c1.get(Calendar.DAY_OF_WEEK);
        c2.setTime(new SimpleDateFormat("hh:mm").parse(this.sessionTime));
        Time time = c2.get(Calendar.TIME);
        if (dayOfWeek == 5 || dayOfWeek == 6){
            hasWeekendMarkup = true;
        } else if (dayOfWeek == 4) {

        } else {
            hasWeekendMarkup = false;
        }


        if (this.sessionDate.get())
    }
    */
}
