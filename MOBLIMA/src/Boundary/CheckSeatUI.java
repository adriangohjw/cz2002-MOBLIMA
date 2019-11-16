package Boundary;

import Controller.*;
import Model.*;
import Model.Movie;
import Model.SeatingPlan;

import java.time.LocalDateTime;
import java.util.*;

public class CheckSeatUI {
    private String cinemaCode;
    private String sessionDateTime;
    private SessionsController sessionsCtrl;
    private SeatingPlan seatsAvailability;
    private CineplexesController cineplexesCtrl;
    private CinemasController cinemasCtrl;

    public CheckSeatUI(){
        this.sessionsCtrl = new SessionsController();
        this.cineplexesCtrl = new CineplexesController();
        this.cinemasCtrl = new CinemasController();
    }

    public CheckSeatUI(SessionsController sessionsCtrl){
        this.sessionsCtrl = sessionsCtrl;
    }


    public void main(){
        if(showAvailableSessions()){
            printLayout();
        }
        else{
            return;
        }
    }

    public void printLayout(){
        int choice = 0;
        System.out.println("Select session: (Cinema code, Date)");
        System.out.print("Enter cinema code: ");
        String cinemaCode = InputController.getStringFromUser();
        System.out.print("Enter date: ");
        LocalDateTime sessionDateTime = InputController.getDateTimeFromUser();

        Session session = sessionsCtrl.readBySession(cinemaCode, sessionDateTime);
        if(session == null){
            System.out.println("Wrong input!");
            printLayout();
        }
        else{
            System.out.println("Seating layout for this session: ");
            seatsAvailability = session.getSeatsAvailability();
            seatsAvailability.printLayout();

            do{
                System.out.println("Insert 1 to exit");
            }while((choice = InputController.getIntFromUser())!=1);
        }
    }

    private boolean showAvailableSessions(){
        ArrayList<Cineplex> cineplexes = cineplexesCtrl.read();
        boolean validInput = false;
        int choice = 0;

        if(cineplexes.isEmpty()){
            System.out.println("No cineplexes in the system!");
            System.out.println("Returning to menu...");
            return false;
        }
        else{
            System.out.println("Available cineplexes: ");
            for (int i = 0; i<cineplexes.size();i++) {
                System.out.println("\t" + (i+1) + ". " + cineplexes.get(i).getName());
            }
        }

        while(!validInput){
            System.out.print("Choose cineplex (ID): ");
            choice = InputController.getIntFromUser();
            if(choice < 1 || choice > cineplexes.size()){
                System.out.println("Wrong input!");
            }
            else{
                validInput = true;
            }
        }

        ArrayList<Cinema> cinemas = cineplexes.get(choice-1).getCinemas();
        int counter = 0;

        System.out.println("Here are available sessions in cineplex " + cineplexes.get(choice-1).getName() + ": ");
        for(int i =0;i<cinemas.size();i++){
            Cinema cinema = cinemas.get(i);
            ArrayList<Session> sessions = cinema.getSessions();
            for(int j=0;j<sessions.size();j++){
                System.out.println("\t" + (counter+1) + ". Cinema: " + cinema.getCode() + "\n\t   Movie: " + sessions.get(j).getMovie().getTitle()
                + "\n\t   Date: " + sessions.get(j).getSessionDateTimeToString());
                counter++;
            }
        }
        if(counter == 0){
            System.out.println("There are no available sessions in this cineplex!");
            System.out.println("Returning to menu....");
            return false;
        }
        return true;
    }

}
