/* package Boundary;

import Controller.*;
import Model.*;
import Model.Movie;
import Model.SeatingPlan;

import java.util.*;

public class CheckSeatUI {
    private String cinemaCode; //the unique code for each session
    Scanner sc = new Scanner(System.in);

    public CheckSeatUI(String _cinemaCode){
        this.cinemaCode = _cinemaCode;
    }

    public void main(){
        System.out.println("Seating layout");
        printLayout(cinemaCode);
        selectSeats();
    }

    public void printLayout(String cinemaCode){
        Session session = sessionsController.readByCinemaCode(cinemaCode); //only 1 session but not array of session
        SeatingPlan seatsAvailability = session.getseatsAvailability(); //have one more function
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
 */