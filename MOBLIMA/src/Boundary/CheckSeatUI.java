package Boundary;

import Controller.*;
import Model.*;
import Model.Movie;
import Model.SeatingPlan;

import java.util.*;

public class CheckSeatUI {
    private String cinemaCode;
    private String sessionDateTime;
    private SessionsController sessionsCtrl;

    public CheckSeatUI(String _cinemaCode, String _sessionDateTime){
        this.cinemaCode = _cinemaCode;
        this.sessionDateTime = _sessionDateTime;
        this.sessionsCtrl = new SessionsController();
    }

    public CheckSeatUI(String _cinemaCode, String _sessionDateTime, SessionsController sessionsCtrl){
        this.cinemaCode = _cinemaCode;
        this.sessionDateTime = _sessionDateTime;
        this.sessionsCtrl = sessionsCtrl;
    }

    Scanner sc = new Scanner(System.in);

    public void main(){
        System.out.println("Seating layout");
        printLayout();
        selectSeats();
    }

    public void printLayout(){
        Session session = sessionsCtrl.readBySession(cinemaCode, sessionDateTime);
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
