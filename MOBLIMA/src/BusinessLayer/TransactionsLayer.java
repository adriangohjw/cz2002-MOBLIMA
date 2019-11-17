package BusinessLayer;

import Controller.TransactionsController;

import Model.Movie;

import CustomException.TransactionsExceptions.InvalidTransactionException;

public class TransactionsLayer {
    
    static TransactionsController transactionsCtrl = new TransactionsController();

    public static boolean isTransactionValid(String cinemaCode, String name, String email, String mobileNumber, Movie movie){

        boolean isValid = true;

        if(CinemasLayer.isExistingCinema(cinemaCode) == false)
            isValid = false;

        if (!isValid){
            try {
                throw new InvalidTransactionException();
            } catch (InvalidTransactionException e) {
                System.out.println(e);
            }
        }

        return isValid;
    }
}