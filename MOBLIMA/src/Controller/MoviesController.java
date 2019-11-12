package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.*;

public class MoviesController {

    public final static String FILENAME = "MOBLIMA/database/movies.txt";
    public final static int ID = 0;
    public final static int TITLE = 1;
    public final static int TYPE = 2;
    public final static int SYNOPSIS = 3;
    public final static int RATING = 4;
    public final static int MOVIE_RELEASE_DATE = 5;
    public final static int DIRECTOR = 6;
    public final static int CAST = 7;
    public final static int REVIEWS = 8;

    public void create(String title, String type, String synopsis, String rating, String movieReleaseDate, String director, ArrayList<String> cast) {
        Movie movie = new Movie(getLastId(), title, type, synopsis, rating, movieReleaseDate, director, cast);
        ArrayList<Movie> allData = new ArrayList<Movie>();
        File tempFile = new File(FILENAME);
        if (tempFile.exists()) 
            allData = read();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(movie);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    public ArrayList<Movie> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));   
            ArrayList<Movie> movieListing = (ArrayList<Movie>) ois.readObject();
            ois.close();
            return movieListing;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Movie>();
    }

    public ArrayList<Movie> readByAttribute(int col, Object valueToSearch) 
            throws ClassNotFoundException, IOException {
        ArrayList<Movie> allData = read();
        ArrayList<Movie> returnData = new ArrayList<Movie>();
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);

            switch(col) {
                case ID:
                    if (m.getId() == (int) valueToSearch)
                        returnData.add(m);
                    break;
                case TITLE:
                    if (m.getTitle().equals((String) valueToSearch))
                        returnData.add(m);
                    break;
                case TYPE:
                    if (m.getType().equals((String) valueToSearch))
                        returnData.add(m);
                    break;
                case RATING:
                    if (m.getRating().equals((String) valueToSearch))
                        returnData.add(m);
                    break;
                case MOVIE_RELEASE_DATE:
                    if (m.getMovieReleaseDate().equals((String) valueToSearch))
                        returnData.add(m);
                    break;
                default:   
                    System.out.println(".....readByAttribute NOT ALLOWED");
                    break;
            }
        }
        return returnData;
    }

    public void updateByAttribute(int col, Object oldValue, Object newValue)
            throws ClassNotFoundException, IOException {
        ArrayList<Movie> allData = read();
        ArrayList<Movie> returnData = new ArrayList<Movie>();
                
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);
            switch(col) {
                case ID:
                    if (m.getId() == (int) oldValue)
                        m.setId((int) newValue);
                    returnData.add(m);
                    break;
                case TITLE:
                    if (m.getTitle().equals((String) oldValue))
                        m.setTitle((String) newValue);
                    returnData.add(m);
                    break;
                case TYPE:
                    if (m.getType().equals((String) oldValue))
                        m.setType((String) newValue);
                    returnData.add(m);
                    break;
                case RATING:
                    if (m.getRating().equals((String) oldValue))
                        m.setRating((String) newValue);
                    returnData.add(m);
                    break;
                case MOVIE_RELEASE_DATE:
                    if (m.getMovieReleaseDate().equals((String) oldValue))
                        m.setMovieReleaseDate((String) newValue);
                    returnData.add(m);
                    break;
                case DIRECTOR:
                    if (m.getDirector().equals((String) oldValue))
                        m.setDirector((String) newValue);
                    returnData.add(m);
                    break;
                case CAST:
                    if (m.getCast().equals((ArrayList<String>) oldValue))
                        m.setCast((ArrayList<String>) newValue);
                    returnData.add(m);
                    break;
                case REVIEWS:
                    if (m.getReviews().equals((ArrayList<Review>) oldValue))
                        m.setReviews((ArrayList<Review>) newValue);
                    returnData.add(m);
                    break;
                default:   
                    break;
            }
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void deleteByAttribute(int col, Object valueToSearch)
            throws ClassNotFoundException, IOException {
        ArrayList<Movie> allData = read();
        ArrayList<Movie> returnData = new ArrayList<Movie>();
        
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);
            switch(col) {
                case ID:
                    if (!(m.getId() == (int) valueToSearch))
                        returnData.add(m);
                    break;
                case TITLE:
                    if (!m.getTitle().equals((String) valueToSearch))
                        returnData.add(m);
                    break;
                case TYPE:
                    if (!m.getType().equals((String) valueToSearch))
                        returnData.add(m);
                    break;
                case RATING:
                    if (!m.getRating().equals((String) valueToSearch))
                        returnData.add(m);
                    break;
                case MOVIE_RELEASE_DATE:
                    if (!m.getMovieReleaseDate().equals((String) valueToSearch))
                        returnData.add(m);
                    break;
                case DIRECTOR:
                    if (!m.getDirector().equals((String) valueToSearch))
                        returnData.add(m);
                    break;
                case CAST:
                    if (!m.getCast().equals((ArrayList<String>) valueToSearch))
                        returnData.add(m);
                    break;
                case REVIEWS:
                    if (!m.getReviews().equals((ArrayList<Review>) valueToSearch))
                        returnData.add(m);
                    break;
                default:   
                    break;
            }
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public int getLastId(){
        int lastId = 0;
        int movieID;
        ArrayList<Movie> allData = read();
        for (int i=0; i<allData.size(); i++){
            movieID = allData.get(i).getId();
            if (movieID > lastId)
                lastId = movieID;
        }
        return lastId;
    }

    public void replaceExistingFile(String filename, ArrayList<Movie> data){
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