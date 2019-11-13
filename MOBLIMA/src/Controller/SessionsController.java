package Controller;

import java.util.ArrayList;

import static Controller.CinemasController.SESSIONS;
import static Controller.CinemasController.CODE;

import Model.*;

public class SessionsController {

    private CinemasController cinemasCtrl;
    public static String FILENAME;
    
    public final static int MOVIE = 0;
    public final static int SESSION_DATETIME = 1;
    public final static int SEATS_AVAILABILITY = 2;

    public SessionsController(CinemasController cinemasCtrl){
        this.cinemasCtrl = cinemasCtrl;
        this.FILENAME = cinemasCtrl.FILENAME;
    }

    public CinemasController getCinemasController(){
        return this.cinemasCtrl;
    }

    public void create(String cinemaCode, Movie movie, String sessionDateTime) {
        SeatingPlan seatingPlan = cinemasCtrl.readByAttribute(CODE, cinemaCode).get(0).getSeatingPlan();
        Session session = new Session(movie, sessionDateTime, seatingPlan);
        ArrayList<Cinema> allData  = this.cinemasCtrl.read();
        ArrayList<Session> sessions = new ArrayList<Session>();
        for (int i=0; i<allData.size(); i++){
            Cinema cinema_i = allData.get(i);
            if (cinema_i.getCode().equals(cinemaCode)){
                sessions = cinema_i.getSessions();
                sessions.add(session);
                cinema_i.setSessions(sessions);
                this.cinemasCtrl.updateByAttribute(SESSIONS, cinemaCode, sessions);
                sessions.clear();
                break;
            }
        }
    }

    public ArrayList<Session> read() {
        ArrayList<Cinema> allCinemas  = this.cinemasCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        Cinema c = null;
        for (int i=0; i<allCinemas.size(); i++) {
            c = allCinemas.get(i);
            c.getSessions().forEach(n->allSessions.add(n));
        }
        return allSessions;
    }; 

    public ArrayList<Session> readByAttributes(int col, Object valueToSearch) {
        ArrayList<Session> allData = read();
        ArrayList<Session> returnData = new ArrayList<Session>();
        Session s = null;
        
        for (int i=0; i<allData.size(); i++){
            s = allData.get(i);
            switch(col){
                case MOVIE:  // Movies going to be compared by movie ID
                    if (s.getMovie().getId() == (int) valueToSearch)
                        returnData.add(s);
                        break;
                case SESSION_DATETIME:
                    if (s.getSessionDateTime().equals((String) valueToSearch))
                        returnData.add(s);
                        break;
                default:
                    break;
            }           
        }
        return returnData;
    }; 

    public void updateByAttribute(int col, String cinemaCode, Object oldValue, Object newValue) {
        ArrayList<Cinema> allCinemas  = this.cinemasCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        ArrayList<Session> returnSessions = new ArrayList<Session>();

        // loop through all cinemas to find one with matching cinema code
        for (int i=0; i<allCinemas.size(); i++) {
            Cinema cinema_i = allCinemas.get(i);
            if (cinema_i.getCode().equals(cinemaCode)){
                
                // loop through all sessions of the matched cinema and update if necessary
                allSessions = cinema_i.getSessions();
                returnSessions.clear();  // ensure it started without existing session
                for (int j=0; j<allSessions.size(); j++){
                    Session s = allSessions.get(j);
                    switch(col) {
                        case MOVIE:  // Movies going to be compared by movie ID
                            if (s.getMovie().getId() == (int) oldValue)
                                s.setMovie((Movie) newValue);
                            break;
                        case SESSION_DATETIME:
                            if (s.getSessionDateTime().equals((String) oldValue))
                                s.setSessionDateTime((String) newValue);
                            break;
                        default:
                            break;
                    }
                    returnSessions.add(s);
                }

                // update DB and break (stop searching other cinema if already found one with matching code)
                this.cinemasCtrl.updateByAttribute(col, cinemaCode, returnSessions);
                break; 
            }
        }
    } 

    public void delete(String cinemaCode, String sessionDateTime){
        ArrayList<Cinema> allCinemas  = this.cinemasCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        ArrayList<Session> returnSessions = new ArrayList<Session>();

        // loop through all cinemas to find one with matching cinema code
        for (int i=0; i<allCinemas.size(); i++) {
            Cinema cinema_i = allCinemas.get(i);
            if (cinema_i.getCode().equals(cinemaCode)){

                // loop through all sessions of the matched cinema and update if necessary
                allSessions = cinema_i.getSessions();
                returnSessions.clear();  // ensure it started without existing session
                for (int j=0; j<allSessions.size(); j++){
                    Session s = allSessions.get(j);
                    if (!(s.getSessionDateTime().equals(sessionDateTime)))
                        returnSessions.add(s);
                }

                // update DB and break (stop searching other cinema if already found one with matching code)
                this.cinemasCtrl.updateByAttribute(SESSION_DATETIME, cinemaCode, returnSessions);
                break;
             }
        }
    }
}