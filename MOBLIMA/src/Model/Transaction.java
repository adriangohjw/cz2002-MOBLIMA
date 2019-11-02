package app;

import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Transaction {

	private String TID; 		// XXXYYYYMMDDhhmm (XXX is cinema code and YYYYDDMMhhmm is datetime)
	private String movieGoer;  	// will user's email to do retrieve the details of the movie goer
	
	public Transaction(String cinemaCode, String movieGoer) {
		String formattedTimestamp = new SimpleDateFormat("YYYYMMddHHmm").format(new Date());
		this.TID = cinemaCode.concat(formattedTimestamp);
		this.movieGoer = movieGoer;
	}

	public String getTID() {
		return TID;
	}

	public String getMovieGoer() {
		return movieGoer;
	}
}
