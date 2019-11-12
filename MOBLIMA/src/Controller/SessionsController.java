package Controller;

import java.util.ArrayList;

import Model.*;

public class SessionsController {

    private CinemasController cinemasCtrl;
    final String FILENAME = cinemasCtrl.FILENAME;

    public SessionsController(CinemasController cinemasCtrl){
        this.cinemasCtrl = cinemasCtrl;
    }

}