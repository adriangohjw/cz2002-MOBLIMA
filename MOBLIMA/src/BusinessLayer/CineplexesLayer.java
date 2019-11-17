package BusinessLayer;

import java.util.ArrayList;

import Controller.CineplexesController;
import Model.Cinema;
import Model.Cineplex;
import CustomException.CineplexesExceptions.EmptyStringException;
import CustomException.CineplexesExceptions.ExistingCineplexException;
import CustomException.CineplexesExceptions.LessThan3CinemasException;

public class CineplexesLayer {

    static CineplexesController cineplexesCtrl = new CineplexesController();

    public static boolean isCineplexValid(String name, ArrayList<Cinema> cinemas) {

        boolean isValid = true;

        if (isExistingCineplex(name)){
            try {
                throw new ExistingCineplexException();
            } catch (ExistingCineplexException e) {
                System.out.println(e);
            }
            isValid = false;
        }
        
        if (isEmpty_name(name))
            isValid = false;

        if (hasLessThan3Cinemas(cinemas))
            isValid = false;

        return isValid;
    }

    public static boolean isExistingCineplex(String name) {

        ArrayList<Cineplex> allCineplexes = cineplexesCtrl.read();

        for (int i=0; i<allCineplexes.size(); i++) {
            if (allCineplexes.get(i).getName().equals(name))
                return true;
        }
        return false;
    }

    public static boolean hasLessThan3Cinemas(ArrayList<Cinema> cinemas) {
        if (cinemas.size() < 3) {
            try {
                throw new LessThan3CinemasException();
            } catch (LessThan3CinemasException e) {
                System.out.println(e);
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty_name(String name) {
        if (isStringEmpty(name)){
            try {
                throw new EmptyStringException("name");
            } catch (EmptyStringException e) {
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