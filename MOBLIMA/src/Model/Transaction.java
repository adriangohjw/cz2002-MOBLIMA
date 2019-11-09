package Model;

import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Transaction implements Serializable {

	private String TID; 		// XXXYYYYMMDDhhmm (XXX is cinema code and YYYYDDMMhhmm is datetime)
	private Movie_Goer movieGoer;
	
	public Transaction(String cinemaCode, Movie_Goer movieGoer) {
		String formattedTimestamp = new SimpleDateFormat("YYYYMMddHHmm").format(new Date());
		this.TID = cinemaCode.concat(formattedTimestamp);
		this.movieGoer = movieGoer;
	}

	public String getTID() {
		return TID;
	}

	public Movie_Goer getMovieGoer() {
		return movieGoer;
	}

	public String toString(){
		String toReturn = "";
		toReturn 	+= "TID: " + getTID() + "\n"
					+ "Name: " + getMovieGoer().getName() + "\n"
					+ "Mobile number: " + getMovieGoer().getMobileNumber() + "\n"
					+ "Email: " + getMovieGoer().getEmail();
		return toReturn; 
	}
}
