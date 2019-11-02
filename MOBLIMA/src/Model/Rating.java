package app;

public class Rating {

    private double numOfStars;
    private String additionalComment;

    public Rating(double numOfStars, String additionalComment){
        this.numOfStars = numOfStars;
        this.additionalComment = additionalComment;
    }

    public double getNumOfStars(){
        return numOfStars;
    }

    public String getAdditionalComment(){
        return additionalComment;
    }
}
