package app;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    private String email;  // username 
    private String passwordHashed;
    
    public User (String email, String password) throws NoSuchAlgorithmException{
        this.email = email;
        this.passwordHashed = PasswordSHA256(password, email);  // using SHA-256 to hash password to ensure security
    }
    
    public boolean validatePassword(String passwordToCompare) throws NoSuchAlgorithmException {
    	return this.passwordHashed.equals(PasswordSHA256(passwordToCompare, this.email));
    }
    	
	public String PasswordSHA256(String passwordToHash, String salt) throws NoSuchAlgorithmException {
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
    
}