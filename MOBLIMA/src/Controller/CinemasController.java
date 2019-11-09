package Controller;

import java.util.ArrayList;

import Model.*;

public class CinemasController {

    private CineplexesController cineplexesCtrl;
    final String FILENAME = cineplexesCtrl.FILENAME;

    public CinemasController(CineplexesController cineplexesCtrl){
        this.cineplexesCtrl = cineplexesCtrl;
    }

    public void create(Cineplex cineplex, Cinema cinema) {
        ArrayList<Cineplex> allData = this.cineplexesCtrl.read();
        ArrayList<Cineplex> returnData = new ArrayList<Cineplex>();
        for (int i=0; i<allData.size(); i++){
            Cineplex cineplex_i = allData.get(i);
            if (cineplex_i.getName().equals(cineplex.getName())){
                ArrayList<Cinema> cinemas = cineplex_i.getCinemas();
                cinemas.add(cinema);
                cineplex_i.setCinemas(cinemas);
            }
            returnData.add(cineplex_i);
        }
        this.cineplexesCtrl.replaceExistingFile(FILENAME, returnData);
    } 

    public void delete(Cineplex cineplex, String code) {
        ArrayList<Cineplex> allData = this.cineplexesCtrl.read();
        ArrayList<Cineplex> returnData = new ArrayList<Cineplex>();

        // Check if there's a match for cineplex based on cineplex's name
        for (int i=0; i<allData.size(); i++){
            Cineplex cineplex_i = allData.get(i);

            // if there's a cineplex with the same name, ensure cineplex has more than 3 cinemas
            if (cineplex_i.getName().equals(cineplex.getName())){
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
            }
            returnData.add(cineplex_i);
        }
        this.cineplexesCtrl.replaceExistingFile(FILENAME, returnData);
    } 
}