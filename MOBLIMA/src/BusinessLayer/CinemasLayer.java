package BusinessLayer;

import java.util.ArrayList;

import CustomException.CinemasExceptions.ExistingCinemaException;

import Model.Cinema;
import Controller.CinemasController;

public class CinemasLayer {

    static CinemasController cinemasCtrl = new CinemasController();

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
}