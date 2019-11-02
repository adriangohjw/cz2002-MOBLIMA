package Model;

public class Rating {

	private String username;
    private double numOfStars;
    private String additionalComment;

    public Rating(String username, double numOfStars, String additionalComment){
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
