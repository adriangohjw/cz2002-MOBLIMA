package Test;

import java.io.File;
import java.util.ArrayList;

import Controller.*;
import static Controller.CineplexesController.FILENAME;

import Model.*;

public class CineplexesControllerTest {
    public static void main(String[] args) throws Exception {

        new File(FILENAME).delete();
        
        CineplexesController cineplexesController = new CineplexesController();
        Cineplex cineplex = null;

        // creating test values

        // Testing CineplexesController.create()
        System.out.println(".....Testing CineplexesController.create()");


        // testing CineplexesController.read()
        System.out.println(".....Testing CineplexesController.read()");
        readAllAndPrint(cineplexesController.read());

        // testing CineplexesController.readByName()
        System.out.println(".....Testing CineplexesController.readByName()");
        cineplex = cineplexesController.readByName("CineplexName_Old");
        System.out.println(cineplex);

        // testing CineplexesController.updateByName()
        System.out.println(".....Testing CineplexesController.updateByName()");
        cineplexesController.updateByName("CineplexName_Old", "CineplexName_New");
        readAllAndPrint(cineplexesController.read());

        // testing MoviesController.deleteByName()
        System.out.println(".....Testing CineplexesController.deleteByName()");
        cineplexesController.deleteByName("CineplexName_New");
        readAllAndPrint(cineplexesController.read());
    }

    public static void readAllAndPrint(ArrayList<Cineplex> cineplexListing){     
        cineplexListing.forEach(n->System.out.println(n));
    }
}