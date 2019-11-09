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

public class AdminsController {

    public final static String FILENAME = "MOBLIMA/database/admins.txt";
    public final static int EMAIL = 0;
    public final static int PASSWORDHASHED = 1;
    public final static int ROLE = 2;

    public void create(Admin admin) {
        ArrayList<Admin> allData = new ArrayList<Admin>();
        File tempFile = new File(FILENAME);
        if (tempFile.exists())
            allData = read();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(admin);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Admin> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Admin> adminListing = (ArrayList<Admin>) ois.readObject();
            ois.close();
            return adminListing;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Admin>();
    }

    public Admin readByEmail(String valueToSearch) throws ClassNotFoundException, IOException {
        ArrayList<Admin> allData = read();
        for (int i=0; i<allData.size(); i++){
            Admin u = allData.get(i);
            if (u.getEmail().equals(valueToSearch))  
                return u;
        }
        return null;
    }

    public void updatePasswordHashed(String email, String currentPassword, String newPassword)
            throws ClassNotFoundException, IOException, NoSuchAlgorithmException {
        ArrayList<Admin> allData = read();
        ArrayList<Admin> returnData = new ArrayList<Admin>();
        
        for (int i=0; i<allData.size(); i++){
            Admin u = allData.get(i);
            if (u.getEmail().equals(email))
                u.updatePassword(currentPassword, newPassword);
            returnData.add(u);
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void deleteByEmail(String email) throws ClassNotFoundException, IOException {
        ArrayList<Admin> allData = read();
        ArrayList<Admin> returnData = new ArrayList<Admin>();
        
        for (int i=0; i<allData.size(); i++){
            Admin u = allData.get(i);
            if (!u.getEmail().equals(email))
                returnData.add(u);        
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public void replaceExistingFile(String filename, ArrayList<Admin> returnData) {
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