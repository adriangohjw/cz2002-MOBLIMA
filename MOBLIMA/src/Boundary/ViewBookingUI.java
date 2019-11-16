package Boundary;

import Controller.*;
import Model.*;

import java.util.*;

public class ViewBookingUI {
    private String email;
    private TransactionsController transCtrl;
    

    public ViewBookingUI() {
        this.transCtrl = new TransactionsController();
    }

    public ViewBookingUI(TransactionsController transCtrl) {
        this.transCtrl = transCtrl;
    }
    
    public void setTransCtrl(TransactionsController transCtrl) {
        this.transCtrl = transCtrl;
    }
    
    public void main(){
        System.out.print("Enter your email: ");
        email = InputController.getEmailFromUser();
        System.out.println("Here is booking history of " + email + ":");
        display();
    }

    public void display(){
        int input;
        ArrayList<Transaction> transactionList = transCtrl.readByMovieGoerUsername(email);
        if(transactionList.isEmpty()){
            System.out.println("Your booking history is empty. Book a ticket now!");
        }
        else{
            transactionList.forEach(transaction -> System.out.println(transaction.toString()));
        }
        do{
            System.out.print("Insert 1 to exit: ");
        }while((input = InputController.getIntFromUser())!=1);
    }
}
