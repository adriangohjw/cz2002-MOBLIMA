package Model;

import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Transaction implements Serializable {

	private String TID; // XXXYYYYMMDDhhmm (XXX is cinema code and YYYYDDMMhhmm is datetime)
	private Movie_Goer movieGoer;
	private Movie movie;
	
	public Transaction(String cinemaCode, Movie_Goer movieGoer, Movie movie) {
		String formattedTimestamp = new SimpleDateFormat("YYYYMMddHHmm").format(new Date());
		this.TID = cinemaCode.concat(formattedTimestamp);
		this.movieGoer = movieGoer;
		this.movie = movie;
	}

	public String getTID() {
		return TID;
	}

	public Movie_Goer getMovieGoer() {
		return movieGoer;
	}

	public Movie getMovie(){
		return movie;
	}

	public String toString(){
		String toReturn = "";
		toReturn 	+= "TID: " + getTID() + "\n"
					+ "Name: " + getMovieGoer().getName() + "\n"
					+ "Mobile number: " + getMovieGoer().getMobileNumber() + "\n"
					+ "Email: " + getMovieGoer().getEmail()+ "\n"
					+ "Movie: " + getMovie().getTitle();
		return toReturn; 
	}
}
