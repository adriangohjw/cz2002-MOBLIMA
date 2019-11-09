package Model;

import java.security.NoSuchAlgorithmException;

/*
 * CRUD movie listing
 * CRUD cinema, showtimes and movies (details)
 * CRUD top viewing movies by ticket sales OR ratings
 */

public class Admin extends User {
	
	public Admin(String username, String password) throws NoSuchAlgorithmException {
		super(username, password, ADMIN);
	}
	
}
