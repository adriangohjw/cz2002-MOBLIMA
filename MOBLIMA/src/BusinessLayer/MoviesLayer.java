package BusinessLayer;

import java.time.LocalDate;
import java.util.ArrayList;

import CustomException.MoviesExceptions.EmptyCastException;
import CustomException.MoviesExceptions.EmptyStringException;
import CustomException.MoviesExceptions.EndBeforeReleaseException;
import CustomException.MoviesExceptions.NegativeDurationException;

import Model.MovieType;

public class MoviesLayer {

    public static boolean isMovieValid(
        String title, MovieType type, String synopsis, String rating, double duration, LocalDate movieReleaseDate, LocalDate movieEndDate, String director, ArrayList<String> cast
    ) {
        boolean isValid = true;
       
        if (!isDatesValid(movieReleaseDate, movieEndDate)){
            isValid = false;
            try {
                throw new EndBeforeReleaseException();
            } catch (EndBeforeReleaseException e) {
                System.out.println(e);
            }
        }
        
        if (!isDurationPositive(duration)){
            isValid = false;
            try {
                throw new NegativeDurationException();
            } catch (NegativeDurationException e) {
                System.out.println(e);
            } 
        }

        if (isCastEmpty(cast)){
            isValid = false;
            try {
                throw new EmptyCastException();
            } catch (EmptyCastException e) {
                System.out.println(e);
            }
        }

        if (isStringEmpty(title)){
            isValid = false;
            try {
                throw new EmptyStringException("Title");
            } catch (EmptyStringException e) {
                System.out.println(e);  
            }
        }

        if (isStringEmpty(synopsis)){
            isValid = false;
            try {
                throw new EmptyStringException("Synopsis");
            } catch (EmptyStringException e) {
                System.out.println(e);  
            }
        }

        if (isStringEmpty(rating)){
            isValid = false;
            try {
                throw new EmptyStringException("rating");
            } catch (EmptyStringException e) {
                System.out.println(e);  
            }
        }

        if (isStringEmpty(director)){
            isValid = false;
            try {
                throw new EmptyStringException("director");
            } catch (EmptyStringException e) {
                System.out.println(e);  
            }
        }
        
        return isValid;
    }
    
    private static boolean isDatesValid(LocalDate movieReleaseDate, LocalDate movieEndDate){
        return movieReleaseDate.isBefore(movieEndDate);
    }

    private static boolean isDurationPositive(double duration){
        return duration > 0;
    }

    private static boolean isCastEmpty(ArrayList<String> cast){
        return cast.isEmpty();
    }

    private static boolean isStringEmpty(String item) {
        return item.equals("");
    }
}