package app;

import java.security.NoSuchAlgorithmException;

/*
 * Movie_Goer is able to do the following:
 * - See seat availability
 * - See ratings
 * - see movie details
 * - See list of movies 
 * - Search for movies
 * - CR transactions
 * - R top movies (by ticket sales/listing)
 * - C Rating
 */

public class Movie_Goer extends User {
	
	private String name;
	private String mobileNumber;

	// user can create account without name and mobile number 
	public Movie_Goer(String username, String password, int role) throws NoSuchAlgorithmException {
		super(username, password, MOVIE_GOER);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
}
