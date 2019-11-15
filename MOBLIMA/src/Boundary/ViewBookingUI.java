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

    public ViewBookingUI(String email) {
        this.transCtrl = new TransactionsController();
        this.email = email;
    }

    
    public void setTransCtrl(TransactionsController transCtrl) {
        this.transCtrl = transCtrl;
    }
    
    public void main(){
        System.out.println("Here is your booking history:");
        display();
    }

    public void display(){
        ArrayList<Transaction> transactionList = transCtrl.readByMovieGoerUsername(email);
        transactionList.forEach(transaction -> System.out.println(transaction.toString()));
    }
}
