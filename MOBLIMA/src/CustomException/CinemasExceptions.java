package CustomException;

public class CinemasExceptions {

    @SuppressWarnings("serial")
    public static class ExistingCinemaException extends Exception {
        public ExistingCinemaException() {
            super("Cinema already existed");
        }
    }
}