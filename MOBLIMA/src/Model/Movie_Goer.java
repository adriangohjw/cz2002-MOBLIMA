package Model;

import java.security.NoSuchAlgorithmException;
public class Movie_Goer extends User {
	
	private String name;
	private String mobileNumber;

	// user can create account without name and mobile number 
	public Movie_Goer(String username, String password) throws NoSuchAlgorithmException {
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
	
	public String toString(){
		String toReturn = "";
		toReturn 	+= "Username: " + getEmail() + "\n"
					+ "Hashed Password: " + getPasswordHashed() + "\n"
					+ "Role: " + getRole() + "\n"
					+ "Name: " + getName() + "\n"
					+ "Mobile number: " + getMobileNumber();
		return toReturn; 
	}
}
