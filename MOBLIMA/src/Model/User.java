package Model;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements Serializable {

    private String email;  // username 
    private String passwordHashed;
    private int role; 
    public static final int MOVIE_GOER = 1, ADMIN = 2;  // declaring constant
    
    public User (String email, String password, int role) {
        this.email = email;
        this.passwordHashed = PasswordSHA256(password, email);  // using SHA-256 to hash password to ensure security
        this.role = role;
    }
    
    public boolean validatePassword(String passwordToCompare){
    	return this.passwordHashed.equals(PasswordSHA256(passwordToCompare, this.email));
    }
    
    public String getEmail() {
    	return this.email;
	}
	
	public String getPasswordHashed() {
		return this.passwordHashed;
	}
    
    public int getRole() {
    	return this.role;
	}
	
	public void updatePassword(String currentPassword, String newPassword) {
		if (this.validatePassword(currentPassword))
			this.passwordHashed = PasswordSHA256(newPassword, this.getEmail());		
	}
    	
	public String PasswordSHA256(String passwordToHash, String salt){
		String generatedPassword = null;
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-512");
	        md.update(salt.getBytes(StandardCharsets.UTF_8));
	        byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++){
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        generatedPassword = sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return generatedPassword;
	}

	public String toString(){
		String toReturn = "";
		toReturn 	+= "Username: " + getEmail() + "\n"
					+ "Hashed Password: " + getPasswordHashed() + "\n"
					+ "Role: " + getRole();
		return toReturn; 
	}
    
}