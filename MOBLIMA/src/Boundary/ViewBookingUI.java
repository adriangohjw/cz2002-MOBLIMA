package Boundary;

import Controller.*;
import Model.*;

import java.io.IOException;
import java.util.*;

public class ViewBookingUI {
    private String movieGoer;
    private TransactionsController transCtrl;
    
    Scanner sc = new Scanner(System.in);

    public ViewBookingUI() {
        this.transCtrl = new TransactionsController();
    }

    public ViewBookingUI(String userName) {
        this.transCtrl = new TransactionsController();
        this.movieGoer = userName;
    }

    
    public void setTransCtrl(TransactionsController transCtrl) {
        this.transCtrl = transCtrl;
    }
    
    public void main(){
        System.out.println("Here is your booking history:");
        display();
    }

    public void display(){
        ArrayList<Transaction> transactionList = transCtrl.readByMovieGoerUsername(movieGoer);
        transactionList.forEach(transaction -> System.out.println(transaction.toString()));
    }
}
