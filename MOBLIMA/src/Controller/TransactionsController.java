package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.*;

public class TransactionsController {

    public final static String FILENAME = "MOBLIMA/database/transactions.txt";

    public void create(String cinemaCode, String name, String email, String mobileNumber, Movie movie) {
        ArrayList<Transaction> allData = new ArrayList<Transaction>();
        File tempFile = new File(FILENAME);
        if (tempFile.exists())
            allData = read();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(new Transaction(cinemaCode, name, email, mobileNumber, movie));
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }

    public ArrayList<Transaction> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Transaction> allData = (ArrayList<Transaction>) ois.readObject();
            ois.close();
            return allData;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Transaction>();
    }

    public ArrayList<Transaction> readByTID(String TID) {
        ArrayList<Transaction> allData = read();
        Transaction transaction = null;
        ArrayList<Transaction> returnData = new ArrayList<Transaction>();

        for (int i=0; i<allData.size(); i++) {
            transaction = allData.get(i);
            if (transaction.getTID().equals(TID))
                returnData.add(transaction);
        }
        return returnData;
    }

    public ArrayList<Transaction> readByMovieGoerUsername(String movieGoerUsername) {
        ArrayList<Transaction> allData = read();
        Transaction transaction = null;
        String dbUsername = null;
        ArrayList<Transaction> returnData = new ArrayList<Transaction>();

        for (int i=0; i<allData.size(); i++){
            transaction = allData.get(i);
            dbUsername = transaction.getEmail();
            if (dbUsername.equals(movieGoerUsername))
                returnData.add(transaction); 
        }
        return returnData;
    }

    public void delete(String TID, String username) {
        ArrayList<Transaction> allData = read();
        Transaction transaction = null;
        ArrayList<Transaction> returnData = new ArrayList<Transaction>();

        for (int i=0; i<allData.size(); i++){
            transaction = allData.get(i);
            if (transaction.getTID().equals(TID) 
                    && transaction.getEmail().equals(username))
                continue;
            returnData.add(transaction);                
        }
        replaceExistingFile(FILENAME, returnData);
    }

    public void replaceExistingFile(String filename, ArrayList<Transaction> returnData) {
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