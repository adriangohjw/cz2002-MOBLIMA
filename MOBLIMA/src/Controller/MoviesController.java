package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import Model.*;

public class MoviesController {

    public final static String FILENAME = "MOBLIMA/database/movies.txt";
    public final static int ID = 0;
    public final static int TITLE = 1;
    public final static int TYPE = 2;
    public final static int SYNOPSIS = 3;
    public final static int RATING = 4;
    public final static int DURATION = 5;
    public final static int MOVIE_RELEASE_DATE = 6;
    public final static int MOVIE_END_DATE = 7;
    public final static int DIRECTOR = 8;
    public final static int CAST = 9;
    public final static int REVIEWS = 10;

    public void create(
            String title, MovieType type, String synopsis, String rating, double duration, Date movieReleaseDate, Date movieEndDate, String director, ArrayList<String> cast
    ) {
        Movie movie = new Movie(getLastId()+1, title, type, synopsis, rating, duration, movieReleaseDate, movieEndDate, director, cast);
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
            //
        }
    } 

    public ArrayList<Movie> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));   
            ArrayList<Movie> movieListing = (ArrayList<Movie>) ois.readObject();
            ois.close();
            return movieListing;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        } 
        return new ArrayList<Movie>();
    }

    public Movie readByID(int valueToSearch) {
        ArrayList<Movie> allData = read();
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);
            if (m.getId() == valueToSearch)
                return m;
        }
        return null;
    }

    public ArrayList<Movie> readByAttribute(int col, Object valueToSearch) {
        ArrayList<Movie> allData = read();
        ArrayList<Movie> returnData = new ArrayList<Movie>();
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);

            switch(col) {
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
                    if (m.getMovieReleaseDate().equals((Date) valueToSearch))
                        returnData.add(m);
                    break;
                case MOVIE_END_DATE:
                    if (m.getMovieEndDate().equals((Date) valueToSearch))
                        returnData.add(m);
                    break;
                default:   
                    System.out.println(".....readByAttribute NOT ALLOWED");
                    break;
            }
        }
        return returnData;
    }

    public void updateById(int col, int id, Object newValue) {
        ArrayList<Movie> allData = read();
        ArrayList<Movie> returnData = new ArrayList<Movie>();
                
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);
            if (m.getId() == id){
                switch(col) {
                    case ID:
                        m.setId((int) newValue);
                        break;
                    case TITLE:
                        m.setTitle((String) newValue);
                        break;
                    case TYPE:
                        m.setType((MovieType) newValue);
                        break;
                    case SYNOPSIS:
                        m.setSynopsis((String) newValue);
                        break;
                    case RATING:
                        m.setRating((String) newValue);
                        break;
                    case DURATION:
                        m.setDuration((double) newValue);
                        break;
                    case MOVIE_RELEASE_DATE:
                        m.setMovieReleaseDate((Date) newValue);
                        break;
                    case MOVIE_END_DATE:
                        m.setMovieEndDate((Date) newValue);
                        break;
                    case DIRECTOR:
                        m.setDirector((String) newValue);
                        break;
                    case CAST:
                        m.setCast((ArrayList<String>) newValue);
                        break;
                    case REVIEWS:
                        m.setReviews((ArrayList<Review>) newValue);
                        break;
                    default:   
                        break;
                }
            }
            returnData.add(m);
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void deleteById(int id) {
        ArrayList<Movie> allData = read();
        ArrayList<Movie> returnData = new ArrayList<Movie>();
        
        for (int i=0; i<allData.size(); i++){
            Movie m = allData.get(i);
            if (!(m.getId() == id))
                returnData.add(m);
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public int getLastId(){
        int lastId = -1;
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
            //
        }
    }
}