package CustomException;

public class CinemasExceptions {

    @SuppressWarnings("serial")
    public static class ExistingCinemaException extends Exception {
        public ExistingCinemaException() {
            super("Cinema already existed");
        }
    }

    @SuppressWarnings("serial")
    public static class EmptyStringException extends Exception {
        public EmptyStringException(String errorMessage){
            super(errorMessage);
        }
    }
}