package Model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Session implements Serializable {

    private Movie movie;
    private LocalDateTime sessionDateTime;
    private SeatingPlan seatsAvailability;
    private int id;

    public Session(Movie movie, LocalDateTime sessionDateTime, SeatingPlan seatingPlan, int id) {
        this.movie = movie;
        this.sessionDateTime = sessionDateTime;
        this.seatsAvailability = seatingPlan;
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getSessionDateTime() {
        return sessionDateTime;
    }

    public void setSessionDateTime(LocalDateTime sessionDateTime) {
        this.sessionDateTime = sessionDateTime;
    }

    public String getSessionDateTimeToString() {
        return sessionDateTime.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy HH:mm"));
    }

    public SeatingPlan getSeatsAvailability() {
        return this.seatsAvailability;
    }

    public void setSeatsAvailability(SeatingPlan seatsAvailability) {
        this.seatsAvailability = seatsAvailability;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public boolean isWeekend(){
        if(sessionDateTime.getDayOfWeek() == DayOfWeek.SATURDAY || sessionDateTime.getDayOfWeek() == DayOfWeek.SUNDAY){
            return true;
        }
        else if(sessionDateTime.getDayOfWeek() == DayOfWeek.FRIDAY && sessionDateTime.getHour()>18){
            return true;
        }
        else{
            return false;
        }
    }

    public String toString() {
        return "id: " + this.getId() + " Title: " + movie.getTitle() + ", DateTime: " + this.getSessionDateTime() + "is Weekend: " + isWeekend();
    }
}
