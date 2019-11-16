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

    @SuppressWarnings("serial")
    public static class EmptyCastException extends Exception {
        public EmptyCastException() {
            super("Cast list is empty");
        }
    }

    @SuppressWarnings("serial")
    public static class EmptyStringException extends Exception {
        public EmptyStringException(String errorMessage){
            super(errorMessage);
        }
    }
}