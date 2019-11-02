package app;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Session {

    private Date sessionDate;
    private Movie movie;
    private Cinema cinema;

    public Session(Date sessionDate, Movie movie, Cinema cinema){
        this.sessionDate = sessionDate;
        this.movie = movie;
        this.cinema = cinema;
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

    public String getSessionDateToString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(sessionDate);
    }

    public String getSessionDetails(){
        return getSessionDateToString() + ", " + movie.getTitle() + ", " + cinema.getName();
    }
}
