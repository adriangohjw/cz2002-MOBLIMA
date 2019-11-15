package Controller;

import java.util.ArrayList;

import Model.*;

public class CinemasController {

    private CineplexesController cineplexesCtrl;
    public String FILENAME;

    public final static int CODE = 0;
    public final static int CINEMA_TYPE = 1;
    public final static int SEATING_PLAN = 2;
    public final static int SESSIONS = 3;

    public CinemasController() {
        this.cineplexesCtrl = new CineplexesController();
        this.FILENAME = cineplexesCtrl.FILENAME;
    }

    public CinemasController(CineplexesController cineplexesCtrl) {
        this.cineplexesCtrl = cineplexesCtrl;
        this.FILENAME = cineplexesCtrl.FILENAME;
    }

    public CineplexesController getCineplexesController(){
        return this.cineplexesCtrl;
    }

    public void setCineplexesController(CineplexesController cineplexesCtrl){
        this.cineplexesCtrl = cineplexesCtrl;
    }

    public void create(
            String cineplexName, String code, CinemaType cinemaType, SeatingPlan seatingPlan 
    ) {
        Cinema cinema = new Cinema(code, cinemaType, seatingPlan);
        ArrayList<Cineplex> allData = this.cineplexesCtrl.read();
        ArrayList<Cineplex> returnData = new ArrayList<Cineplex>();
        for (int i=0; i<allData.size(); i++){
            Cineplex cineplex_i = allData.get(i);
            if (cineplex_i.getName().equals(cineplexName)){
                ArrayList<Cinema> cinemas = cineplex_i.getCinemas();
                cinemas.add(cinema);
                cineplex_i.setCinemas(cinemas);
            }
            returnData.add(cineplex_i);
        }
        this.cineplexesCtrl.replaceExistingFile(FILENAME, returnData);
    } 

    public ArrayList<Cinema> read() {
        ArrayList<Cinema> returnData = new ArrayList<Cinema>();
        ArrayList<Cineplex> cineplexListing = this.cineplexesCtrl.read(); 
        Cineplex cineplex = null;

        for (int i=0; i<cineplexListing.size(); i++){
            cineplex = cineplexListing.get(i);
            cineplex.getCinemas().forEach(n->returnData.add(n));
        }
        return returnData;
    }

    public ArrayList<Cinema> readByCineplexName(String name){
        ArrayList<Cinema> returnData = new ArrayList<Cinema>();
        ArrayList<Cineplex> cineplexListing = this.cineplexesCtrl.read();
        Cineplex cineplex = null;

        for (int i=0; i<cineplexListing.size(); i++){
            cineplex = cineplexListing.get(i);
            if(cineplex.getName().equals(name)){
                cineplex.getCinemas().forEach(n->returnData.add(n));
            }
        }
        return returnData;
    }

    public ArrayList<Cinema> readByAttribute(int col, Object valueToSearch) {
        ArrayList<Cinema> returnData = new ArrayList<Cinema>();
        ArrayList<Cinema> cinemaListing = read();
        Cinema cinema = null;

        for (int j=0; j<cinemaListing.size(); j++){
            cinema = cinemaListing.get(j);
            switch (col) {
                case CODE:
                    if (cinema.getCode().equals((String) valueToSearch))
                        returnData.add(cinema);
                    break;
                case CINEMA_TYPE:
                    if (cinema.getCinemaType().equals((CinemaType) valueToSearch))
                        returnData.add(cinema);
                    break;
                default:
                    break;
            }
        }
        return returnData;
    }

    public void updateByAttribute(int col, String code, Object newValue) {
        ArrayList<Cineplex> allData = this.cineplexesCtrl.read();
        ArrayList<Cineplex> returnData = new ArrayList<Cineplex>();

        for (int i=0; i<allData.size(); i++){
            Cineplex cineplex_i = allData.get(i);
            
            ArrayList<Cinema> cinemas = cineplex_i.getCinemas();
            ArrayList<Cinema> cinemaLiisting = new ArrayList<Cinema>();

            // Check if there's a match for cinema based on cinema's code
            for (int j=0; j<cinemas.size(); j++){
                Cinema cinema_j = cinemas.get(j);
                if (cinema_j.getCode().equals(code)){
                    switch (col) {
                        case CINEMA_TYPE:
                            cinema_j.setCinemaType((CinemaType) newValue);
                            break;
                        case SESSIONS:
                            cinema_j.setSessions((ArrayList<Session>) newValue);
                            break; 
                        default:
                            break;
                    } 
                }
                cinemaLiisting.add(cinema_j);
            }

            cineplex_i.setCinemas(cinemaLiisting);
            returnData.add(cineplex_i);
        }
        this.cineplexesCtrl.replaceExistingFile(FILENAME, returnData);
    }

    public void delete(String code) {
        ArrayList<Cineplex> allData = this.cineplexesCtrl.read();
        ArrayList<Cineplex> returnData = new ArrayList<Cineplex>();

        for (int i=0; i<allData.size(); i++){
            Cineplex cineplex_i = allData.get(i);

            // ensure cineplex has more than 3 cinemas before allowing deletion
                if (cineplex_i.getCinemas().size() > 3){
                    ArrayList<Cinema> cinemas = cineplex_i.getCinemas();
                    ArrayList<Cinema> newCinemas = new ArrayList<Cinema>();

                    // Check if there's a match for cinema based on cinema's code
                    for (int j=0; j<cinemas.size(); j++){
                        Cinema cinema_j = cinemas.get(j);
                        if (!cinema_j.getCode().equals(code))
                            newCinemas.add(cinema_j);
                    }
                    cineplex_i.setCinemas(newCinemas);
                }
            returnData.add(cineplex_i);
        }
        this.cineplexesCtrl.replaceExistingFile(FILENAME, returnData);
    } 
}