package Test;

import java.io.File;
import java.util.ArrayList;

import Controller.*;
import static Controller.UsersController.FILENAME;
import static Controller.UsersController.EMAIL;
import static Controller.UsersController.PASSWORDHASHED;
import static Controller.UsersController.ROLE;

import Model.*;

public class UsersControllerTest {
    public static void main(String[] args) throws Exception {

        new File(FILENAME).delete();
        
        UsersController usersController = new UsersController();
        ArrayList<User> userListing = new ArrayList<User>();
        User u;

        // creating test values
        User user1 = new User("adrian1@gmail.com", "myUnhashedPassword", 1);
        User user2 = new User("adrian2@gmail.com", "myUnhashedPassword", 2);
        userListing.add(user1);
        userListing.add(user2);

        // Testing UsersController.create()
        System.out.println(".....Testing UsersController.create()");
        userListing.forEach(user->usersController.create(user));
        readAllAndPrint(usersController.read());

        // testing UsersController.read()
        System.out.println(".....Testing UsersController.read()");
        readAllAndPrint(usersController.read());

        // testing UsersController.readByEmail()
        System.out.println(".....Testing UsersController.readByEmail()");
        u = usersController.readByEmail("adrian1@gmail.com");
        userListing.clear();
        userListing.add(u);
        readAllAndPrint(userListing);

        // testing UsersController.updatePasswordHashed()
        System.out.println(".....Testing UsersController.updatePasswordHashed()");
        usersController.updatePasswordHashed("adrian1@gmail.com", "myUnhashedPassword", "myUnhashedPassword2");
        readAllAndPrint(usersController.read());

        // testing UsersController.deleteByEmail()
        System.out.println(".....Testing UsersController.deleteByEmail()");
        usersController.deleteByEmail("adrian2@gmail.com");
        readAllAndPrint(usersController.read());
    }

    public static void readAllAndPrint(ArrayList<User> userListing){     
        userListing.forEach(n->System.out.println(n));
    }
}