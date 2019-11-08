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

public class UsersController {

    public final static String FILENAME = "MOBLIMA/database/users.txt";
    public final static int EMAIL = 0;
    public final static int PASSWORDHASHED = 1;
    public final static int ROLE = 2;

    public void create(User user) {
        ArrayList<User> allData = new ArrayList<User>();
        File tempFile = new File(FILENAME);
        if (tempFile.exists())
            allData = read();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(user);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<User> userListing = (ArrayList<User>) ois.readObject();
            ois.close();
            return userListing;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<User>();
    }

    public User readByEmail(String valueToSearch) throws ClassNotFoundException, IOException {
        ArrayList<User> allData = read();
        for (int i=0; i<allData.size(); i++){
            User u = allData.get(i);
            if (u.getEmail().equals(valueToSearch))  
                return u;
        }
        return null;
    }

    public void updatePasswordHashed(String email, String currentPassword, String newPassword)
            throws ClassNotFoundException, IOException, NoSuchAlgorithmException {
        ArrayList<User> allData = read();
        ArrayList<User> returnData = new ArrayList<User>();
        
        for (int i=0; i<allData.size(); i++){
            User u = allData.get(i);
            if (u.getEmail().equals(email))
                u.updatePassword(currentPassword, newPassword);
            returnData.add(u);
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void deleteByEmail(String email) throws ClassNotFoundException, IOException {
        ArrayList<User> allData = read();
        ArrayList<User> returnData = new ArrayList<User>();
        
        for (int i=0; i<allData.size(); i++){
            User u = allData.get(i);
            if (!u.getEmail().equals(email))
                returnData.add(u);        
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void replaceExistingFile(String filename, ArrayList<User> returnData) {
        File tempFile = new File(filename);
        if (tempFile.exists())
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            out.writeObject(returnData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}