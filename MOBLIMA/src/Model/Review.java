package Model;

import java.io.Serializable;

public class Review implements Serializable {

	private String username;
    private double numOfStars;
    private String additionalComment;

    public Review(String username, double numOfStars, String additionalComment){
    	this.username = username;
        this.numOfStars = numOfStars;
        this.additionalComment = additionalComment;
    }
    
    public String getUsername() {
    	return this.username;
    }

    public double getNumOfStars(){
        return this.numOfStars;
    }

    public String getAdditionalComment(){
        return this.additionalComment;
    }

    public void setNumOfStars(double numOfStars){
        this.numOfStars = numOfStars;
    }

    public void setAdditionalComent(String additionalComment){
        this.additionalComment = additionalComment;
    }

    public String toString(){
        String details = "";
        details += "username: " + getUsername() + "\n"
                + "numOfStars: " + String.valueOf(getNumOfStars()) + "\n"
                + "additionalComment: " + getAdditionalComment();     
        return details;
    }

    @Override
    public boolean equals(Object review) {
        if (!(review instanceof Review)) {
            return false;
        }
        Review other = (Review) review;        
        return 
            this.username.equals(other.getUsername()) 
            && this.numOfStars == other.getNumOfStars()
            && this.additionalComment.equals(other.getAdditionalComment());
    }
}
