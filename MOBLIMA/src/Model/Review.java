package Model;

public class Review {

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
}
