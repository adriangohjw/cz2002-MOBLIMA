package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import Model.*;

public class MovieGoersController {

    public final static String FILENAME = "MOBLIMA/database/movieGoers.txt";
    public final static int EMAIL = 0;
    public final static int PASSWORDHASHED = 1;
    public final static int ROLE = 2;
    public final static int NAME = 3;
    public final static int MOBILE_NUMBER = 4;

    public void create(Movie_Goer movieGoer) {
        ArrayList<Movie_Goer> allData = new ArrayList<Movie_Goer>();
        File tempFile = new File(FILENAME);
        if (tempFile.exists())
            allData = read();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(movieGoer);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Movie_Goer> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Movie_Goer> movieGoerListing = (ArrayList<Movie_Goer>) ois.readObject();
            ois.close();
            return movieGoerListing;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Movie_Goer>();
    }

    public Movie_Goer readByEmail(String valueToSearch) throws ClassNotFoundException, IOException {
        ArrayList<Movie_Goer> allData = read();
        for (int i=0; i<allData.size(); i++){
            Movie_Goer u = allData.get(i);
            if (u.getEmail().equals(valueToSearch))  
                return u;
        }
        return null;
    }

    public void updatePasswordHashed(String email, String currentPassword, String newPassword)
            throws ClassNotFoundException, IOException, NoSuchAlgorithmException {
        ArrayList<Movie_Goer> allData = read();
        ArrayList<Movie_Goer> returnData = new ArrayList<Movie_Goer>();
        
        for (int i=0; i<allData.size(); i++){
            Movie_Goer u = allData.get(i);
            if (u.getEmail().equals(email))
                u.updatePassword(currentPassword, newPassword);
            returnData.add(u);
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void updateByAttribute(int col, Object oldValue, Object newValue)
            throws ClassNotFoundException, IOException {
        ArrayList<Movie_Goer> allData = read();
        ArrayList<Movie_Goer> returnData = new ArrayList<Movie_Goer>();
                
        for (int i=0; i<allData.size(); i++){
            Movie_Goer m = allData.get(i);
            switch(col) {
                case NAME:
                    if (m.getName().equals((String) oldValue))
                        m.setName((String) newValue);
                    returnData.add(m);
                    break;
                case MOBILE_NUMBER:
                    if (m.getMobileNumber().equals((String) oldValue))
                        m.setMobileNumber((String) newValue);
                    returnData.add(m);
                    break;
                default:   
                    break;
            }
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void deleteByEmail(String email) throws ClassNotFoundException, IOException {
        ArrayList<Movie_Goer> allData = read();
        ArrayList<Movie_Goer> returnData = new ArrayList<Movie_Goer>();
        
        for (int i=0; i<allData.size(); i++){
            Movie_Goer u = allData.get(i);
            if (!u.getEmail().equals(email))
                returnData.add(u);        
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void replaceExistingFile(String filename, ArrayList<Movie_Goer> returnData) {
        File tempFile = new File(filename);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(returnData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}