package Controller;

import java.util.ArrayList;

import static Controller.CinemasController.SESSIONS;
import static Controller.CinemasController.CODE;

import Model.*;

public class SessionsController {

    private static CinemasController cinemasCtrl = new CinemasController();
    public static String FILENAME = cinemasCtrl.FILENAME;
    
    public final static int MOVIE = 0;
    public final static int SESSION_DATETIME = 1;
    public final static int SEATS_AVAILABILITY = 2;
    public final static int ID = 3;

    public CinemasController getCinemasController(){
        return this.cinemasCtrl;
    }

    public void create(String cinemaCode, Movie movie, String sessionDateTime) {
        SeatingPlan seatingPlan = cinemasCtrl.readByAttribute(CODE, cinemaCode).get(0).getSeatingPlan();
        Session session = new Session(movie, sessionDateTime, seatingPlan, getLastId()+1);
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

    public Session readBySession(String cinemaCode, String sessionDateTime) {
        Cinema c = this.cinemasCtrl.readByAttribute(cinemasCtrl.CODE, cinemaCode).get(0);
        ArrayList<Session> allData = c.getSessions();
        Session s = null;

        for (int i=0; i<allData.size(); i++){
            s = allData.get(i);
            if (s.getSessionDateTime().equals(sessionDateTime))
                return s;
        }
        return null;
    }; 

    public Session readById(int id) {
        ArrayList<Session> allData = read();
        Session s = null;

        for (int i=0; i<allData.size(); i++){
            s = allData.get(i);
            if (s.getId() == id)
                return s;
        }
        return s;
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
                        case ID:
                            if (s.getId() == (int) oldValue)
                                s.setI((String) newValue);
                            break;
                        default:
                            break;
                    }
                    returnSessions.add(s);
                }

                // update DB and break (stop searching other cinema if already found one with matching code)
                this.cinemasCtrl.updateByAttribute(cinemasCtrl.SESSIONS, cinemaCode, returnSessions);
                break; 
            }
        }
    } 

    public void updateById(int col, int id, Object newValue) {
        ArrayList<Cinema> allCinemas  = this.cinemasCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        ArrayList<Session> returnSessions = new ArrayList<Session>();
        Session s = null;

        for (int i=0; i<allCinemas.size(); i++) {
            Cinema cinema_i = allCinemas.get(i);
            allSessions = cinema_i.getSessions();
            returnSessions.clear();  // ensure it started without existing session
            for (int j=0; j<allSessions.size(); j++){
                s = allSessions.get(j);
                if (s.getId == id)
                    switch (col){
                        case MOVIE: 
                            s.setMovie((Movie) newValue);
                            break;
                        case SESSION_DATETIME:
                            s.setSessionDateTime((String) newValue);
                            break;
                        case SEATS_AVAILABILITY:
                            s.setSeatsAvailability((SeatingPlan) newValue);
                            break;
                        default:
                            break;
                    }
                returnSessions.add(s);
            }

            // update DB and break (stop searching other cinema if already found one with matching code)
            this.cinemasCtrl.updateByAttribute(cinemasCtrl.SESSIONS, cinema_i.getCode(), returnSessions);
            break; 
        }
    }

    public void updateSeatsAvailability(int id, SeatingPlan newSeatsAvailabiity) {
        ArrayList<Cinema> allCinemas  = this.cinemasCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        ArrayList<Session> returnSessions = new ArrayList<Session>();
        Session s = null;

        for (int i=0; i<allCinemas.size(); i++) {
            Cinema cinema_i = allCinemas.get(i);
            allSessions = cinema_i.getSessions();
            returnSessions.clear();  // ensure it started without existing session
            for (int j=0; j<allSessions.size(); j++){
                s = allSessions.get(j);
                if (s.getId() == id)
                    s.setSeatsAvailability(newSeatsAvailabiity);
                returnSessions.add(s);
            }

            // update DB and break (stop searching other cinema if already found one with matching code)
            this.cinemasCtrl.updateByAttribute(cinemasCtrl.SESSIONS, cinema_i.getCode(), returnSessions);
            break; 
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
                this.cinemasCtrl.updateByAttribute(cinemasCtrl.SESSIONS, cinemaCode, returnSessions);
                break;
             }
        }
    }

    public void delete(int id){
        ArrayList<Cinema> allCinemas  = this.cinemasCtrl.read();
        ArrayList<Session> allSessions = new ArrayList<Session>();
        ArrayList<Session> returnSessions = new ArrayList<Session>();

        for (int i=0; i<allCinemas.size(); i++) {
            Cinema cinema_i = allCinemas.get(i);
            allSessions = cinema_i.getSessions();
            returnSessions.clear();  // ensure it started without existing session
            for (int j=0; j<allSessions.size(); j++){
                Session s = allSessions.get(j);
                if (!(s.getId() == id))
                    returnSessions.add(s);
            }
            this.cinemasCtrl.updateByAttribute(cinemasCtrl.SESSIONS, cinema_i.getCode(), returnSessions);
            break;
        }
    }

    public int getLastId(){
        int lastId = -1;
        int sessionId;
        ArrayList<Session> allData = read();
        for (int i=0; i<allData.size(); i++){
            sessionId = allData.get(i).getId();
            if (sessionId > lastId)
                lastId = sessionId;
        }
        return lastId;
    }
}
