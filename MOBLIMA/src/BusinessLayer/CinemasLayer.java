package BusinessLayer;

import java.util.ArrayList;

import CustomException.CinemasExceptions.ExistingCinemaException;
import CustomException.CinemasExceptions.EmptyStringException;

import Model.Cinema;
import Model.CinemaType;
import Model.SeatingPlan;
import Controller.CinemasController;

public class CinemasLayer {

    static CinemasController cinemasCtrl = new CinemasController();

    public static boolean isCinemaValid(String cineplexName, String code, CinemaType cinemaType, SeatingPlan seatingPlan){
        
        boolean isValid = true;

        if (isExistingCinema(code))
            isValid = false;

        if (isEmpty_code(code))
            isValid = false;

        return isValid;
    }

    public static boolean isExistingCinema(String code) {

        ArrayList<Cinema> allCinemas = cinemasCtrl.read();

        for (int i=0; i<allCinemas.size(); i++) {
            if (allCinemas.get(i).getCode().equals(code)){
                try {
                    throw new ExistingCinemaException();
                } catch (ExistingCinemaException e) {
                    System.out.println(e);
                }
                return true;
            }
        }

        return false;
    }

    public static boolean isEmpty_code(String code) {
        if (isStringEmpty(code)){
            try {
                throw new EmptyStringException("code");
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