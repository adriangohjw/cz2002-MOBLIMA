package BusinessLayer;

import java.time.LocalDate;
import java.util.ArrayList;

import Controller.MoviesController;
import CustomException.MoviesExceptions.ExistingMovieException;
import CustomException.MoviesExceptions.EmptyCastException;
import CustomException.MoviesExceptions.EmptyStringException;
import CustomException.MoviesExceptions.EndBeforeReleaseException;
import CustomException.MoviesExceptions.NegativeDurationException;
import Model.Movie;
import Model.MovieType;

public class MoviesLayer {

    static MoviesController moviesCtrl = new MoviesController();

    public static boolean isMovieValid(
        String title, MovieType type, String synopsis, String rating, double duration, LocalDate movieReleaseDate, LocalDate movieEndDate, String director, ArrayList<String> cast
    ) {
        boolean isValid = true;

        if (isExistingMovie(title))
            isValid = false;
        
        if (isEmpty_title(title))
            isValid = false;

        if (isEmpty_synopsis(synopsis))
            isValid = false;

        if (isEmpty_rating(rating))
            isValid = false;

        if (isDurationNegative(duration))
            isValid = false;

        if (areDatesValid(movieReleaseDate, movieEndDate) == false) 
            isValid = false;

        if (isEmpty_director(director))
            isValid = false;

        if (isEmpty_cast(cast))
            isValid = false;
        
        return isValid;
    }

    public static boolean isExistingMovie(String title) {
        ArrayList<Movie> allMovies = moviesCtrl.read();
        for (Movie movie : allMovies) {
            if (movie.getTitle().equals(title)){
                try {
                    throw new ExistingMovieException();
                } catch (ExistingMovieException e) {
                    System.out.println(e);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isEmpty_title(String title) {
        if (isStringEmpty(title)){
            try {
                throw new EmptyStringException("title");
            } catch (EmptyStringException e) {
                System.out.println(e);
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty_synopsis(String synopsis) {
        if (isStringEmpty(synopsis)){
            try {
                throw new EmptyStringException("synopsis");
            } catch (EmptyStringException e) {
                System.out.println(e);
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty_rating(String rating) {
        if (isStringEmpty(rating)){
            try {
                throw new EmptyStringException("rating");
            } catch (EmptyStringException e) {
                System.out.println(e);
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDurationNegative(double duration){
        if (duration < 0){
            try {
                throw new NegativeDurationException();
            } catch (NegativeDurationException e) {
                System.out.println(e);
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean areDatesValid(LocalDate movieReleaseDate, LocalDate movieEndDate){
        if (movieReleaseDate.isBefore(movieEndDate)){
            return true;
        } else {
            try {
                throw new EndBeforeReleaseException();
            } catch (EndBeforeReleaseException e) {
                System.out.println(e);
            }
            return false;
        }
    }

    public static boolean isEmpty_director(String director) {
        if (isStringEmpty(director)){
            try {
                throw new EmptyStringException("director");
            } catch (EmptyStringException e) {
                System.out.println(e);
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty_cast(ArrayList<String> cast) {
        if (cast.isEmpty()){
            try {
                throw new EmptyCastException();
            } catch (EmptyCastException e) {
                System.out.println(e);
            }
            return true;
        } else {
            return false;
        }
    }

    private static boolean isStringEmpty(String item) {
        return item.equals("");
    }
}