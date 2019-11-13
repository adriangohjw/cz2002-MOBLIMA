package Model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class Session implements Serializable {

    private Movie movie;
    private String sessionDateTime;
    private SeatingPlan seatsAvailability;

    public Session(Movie movie, String sessionDateTime, SeatingPlan seatingPlan) {
        this.movie = movie;
        this.sessionDateTime = sessionDateTime;
        this.seatsAvailability = seatingPlan;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getSessionDateTime() {
        return sessionDateTime;
    }

    public void setSessionDateTime(String sessionDateTime) {
        this.sessionDateTime = sessionDateTime;
    }

    public String getSessionDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(this.getSessionDateTime());
    }

    public SeatingPlan getSeatsAvailability() {
        return this.seatsAvailability;
    }

    public void setSeatsAvailability(SeatingPlan seatsAvailability) {
        this.seatsAvailability = seatsAvailability;
    }
    
    public boolean isWeekend(){
        String dayOfWeek = getDayOfWeekString();
        if (dayOfWeek.equals("Sat") || dayOfWeek.equals("Sun"))
            return true;
        else if (dayOfWeek.equals("Fri") && getTime().isBefore(LocalTime.of(18, 0)))
            return true;
        else
            return false;
    }

    private String getDayOfWeekString() {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("E");
        try {
            String dayOfWeek_Str = outputFormat.format(inputFormat.parse(this.sessionDateTime));
            return dayOfWeek_Str;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private LocalTime getTime() {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat newFormat = new SimpleDateFormat("hh:mm");
        String hourMin = null;
        try {
            hourMin = newFormat.format(inputFormat.parse(this.sessionDateTime));
            LocalTime returnData = LocalTime.parse(hourMin);
        return returnData;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "Title: " + movie.getTitle() + ", DateTime: " + sessionDateTime;
    }
}
