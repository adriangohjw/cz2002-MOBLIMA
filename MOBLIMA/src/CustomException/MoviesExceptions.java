package CustomException;

public class MoviesExceptions {

    @SuppressWarnings("serial")
    public static class EndBeforeReleaseException extends Exception {
        public EndBeforeReleaseException() {
            super("End Date is before Release Date");
        }
    }

    @SuppressWarnings("serial")
    public static class NegativeDurationException extends Exception {
        public NegativeDurationException() {
            super("Duration is negative");
        }
    }
}