package Model;

import java.security.NoSuchAlgorithmException;

public class Movie_Goer extends User {
	
	private String name;
	private String mobileNumber;
	private String email;

	// user can create account without name and mobile number 
	public Movie_Goer(String username, String password) 
			throws NoSuchAlgorithmException {
		super(username, password, MOVIE_GOER);
	}

	public Movie_Goer(String username, String password, String name, String mobileNumber) 
			throws NoSuchAlgorithmException {
		super(username, password, MOVIE_GOER);
		this.name = name;
		this.mobileNumber = mobileNumber;
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
	
	public String toString(){
		String toReturn = "";
		toReturn 	+= "Username: " + getName() + "\n"
					+ "E-mail: " + getEmail() + "\n"
					+ "Mobile number: " + getMobileNumber();
		return toReturn; 
	}
}
