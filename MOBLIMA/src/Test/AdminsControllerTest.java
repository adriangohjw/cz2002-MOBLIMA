package Test;

import java.io.File;
import java.util.ArrayList;

import Controller.*;
import static Controller.AdminsController.FILENAME;

import Model.*;

public class AdminsControllerTest {
    public static void main(String[] args) throws Exception {

        new File(FILENAME).delete();
        
        AdminsController adminsController = new AdminsController();
        ArrayList<Admin> userListing = new ArrayList<Admin>();
        Admin admin = null;

        // creating test values
        Admin admin1 = new Admin("adrian1@gmail.com", "myUnhashedPassword", 2);
        Admin admin2 = new Admin("adrian2@gmail.com", "myUnhashedPassword", 2);
        userListing.add(admin1);
        userListing.add(admin2);

        // Testing AdminsController.create()
        System.out.println(".....Testing AdminsController.create()");
        userListing.forEach(a->adminsController.create(a));
        readAllAndPrint(adminsController.read());

        // testing AdminsController.read()
        System.out.println(".....Testing AdminsController.read()");
        readAllAndPrint(adminsController.read());

        // testing AdminsController.readByEmail()
        System.out.println(".....Testing AdminsController.readByEmail()");
        admin = adminsController.readByEmail("adrian1@gmail.com");
        userListing.clear();
        userListing.add(admin);
        readAllAndPrint(userListing);

        // testing AdminsController.updatePasswordHashed()
        System.out.println(".....Testing AdminsController.updatePasswordHashed()");
        adminsController.updatePasswordHashed("adrian1@gmail.com", "myUnhashedPassword", "myUnhashedPassword2");
        readAllAndPrint(adminsController.read());

        // testing AdminsController.deleteByEmail()
        System.out.println(".....Testing AdminsController.deleteByEmail()");
        adminsController.deleteByEmail("adrian2@gmail.com");
        readAllAndPrint(adminsController.read());
    }

    public static void readAllAndPrint(ArrayList<Admin> userListing){     
        userListing.forEach(n->System.out.println(n));
    }
}