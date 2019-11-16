package CustomException.Movies;

@SuppressWarnings("serial")
public class EndBeforeReleaseException extends Exception {
    public EndBeforeReleaseException() {
        super("End Date is before Release Date");
    }
}