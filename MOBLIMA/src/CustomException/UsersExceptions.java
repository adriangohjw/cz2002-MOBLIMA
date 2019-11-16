package CustomException;

public class UsersExceptions {

    @SuppressWarnings("serial")
    public static class ExistingUserException extends Exception {
        public ExistingUserException() {
            super("User already existed");
        }
    }
}