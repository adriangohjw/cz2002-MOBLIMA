package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.*;

public class CineplexesController {

    public final static String FILENAME = "MOBLIMA/database/cineplexes.txt";
    public final static int NAME = 0;
    public final static int CINEMAS = 1;

    public void create(Cineplex cineplex) {
        ArrayList<Cineplex> allData = new ArrayList<Cineplex>();
        File tempFile = new File(FILENAME);
        if (tempFile.exists()) 
            allData = read();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(cineplex);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Cineplex> read(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));   
            ArrayList<Cineplex> cineplexListing = (ArrayList<Cineplex>) ois.readObject();
            ois.close();
            return cineplexListing;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Cineplex>();
    }; 

    public Cineplex readByName(String name){
        ArrayList<Cineplex> allData = read();
        for (int i=0; i<allData.size(); i++){
            Cineplex c = allData.get(i);
            if (c.getName().equals(name))
                return c;
        }
        return null;
    };

    public void updateByName(String oldName, String newName){
        ArrayList<Cineplex> allData = read();
        ArrayList<Cineplex> returnData = new ArrayList<Cineplex>();
                
        for (int i=0; i<allData.size(); i++){
            Cineplex c = allData.get(i);
            if (c.getName().equals(oldName))
                c.setName(newName);
            returnData.add(c);
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void deleteByName(String name){
        ArrayList<Cineplex> allData = read();
        ArrayList<Cineplex> returnData = new ArrayList<Cineplex>();
        
        for (int i=0; i<allData.size(); i++){
            Cineplex c = allData.get(i);
            if (!c.getName().equals(name))
                returnData.add(c);
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void replaceExistingFile(String filename, ArrayList<Cineplex> data){
        File tempFile = new File(filename);
        if (tempFile.exists()) 
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}