package BusinessLayer;

import java.time.LocalDate;

public class MoviesLayer {
    
    public static boolean isDatesValid(LocalDate movieReleaseDate, LocalDate movieEndDate){
        return movieReleaseDate.isBefore(movieEndDate);
    }
}