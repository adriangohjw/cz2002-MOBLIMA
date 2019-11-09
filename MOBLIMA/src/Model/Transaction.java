package Model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Transaction {

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
}
