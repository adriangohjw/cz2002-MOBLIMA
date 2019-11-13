package Boundary;

import Controller.*;
import Model.*;
import Model.Movie;
import Model.SeatingPlan;

import java.util.*;

public class CheckSeatUI {
    private String cinemaCode; //the unique code for each session
    private SessionsController sessionsCtrl;

    public CheckSeatUI(){
        this.sessionsCtrl = new SessionsController();
    }

    public CheckSeatUI(SessionsController sessionsCtrl){
        this.sessionsCtrl = sessionsCtrl;
    }

    public void setSessionsController(SessionsController sessionsCtrl){
        this.sessionsCtrl = sessionsCtrl;
    }

    Scanner sc = new Scanner(System.in);

    public CheckSeatUI(String _cinemaCode){
        this.cinemaCode = _cinemaCode;
    }

    public void main(){
        System.out.println("Seating layout");
        printLayout(cinemaCode);
        selectSeats();
    }

    public void printLayout(String cinemaCode, String sessionDateTime){
        Session session = sessionsCtrl.readBySession(cinemaCode, sessionDateTime); //only 1 session but not array of session
        SeatingPlan seatsAvailability = session.getSeatsAvailability(); // have one more function
        seatsAvailability.printLayout();
    }

    public void selectSeats(){
        System.out.println("Please choose your seat (terminate by -1)");
        int temp;

        do{
            temp = sc.nextInt();
            seatsAvailability.assignSeats(temp);
        }
        while(temp!=-1);
    }
}