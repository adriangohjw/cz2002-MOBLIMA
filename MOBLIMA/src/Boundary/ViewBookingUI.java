package Boundary;

import Controller.*;
import Model.*;

import java.io.IOException;
import java.util.*;

public class ViewBookingUI {
    private String movieGoer;
    private TransactionsController transCtrl;

    public ViewBookingUI() {
        this.transCtrl = new TransactionsController();
    }

    public ViewBookingUI(TransactionsController transCtrl) {
        this.transCtrl = transCtrl;
    }

    Scanner sc = new Scanner(System.in);

    public ViewBookingUI(String userName) {
        this.movieGoer = userName;
    }

    public void main() throws ClassNotFoundException, IOException {
        System.out.println("Here is your booking history:");
        display();
    }

    public void display() throws ClassNotFoundException, IOException {
        ArrayList<Transaction> transactionList = transCtrl.readByMovieGoerUsername(movieGoer);
        transactionList.forEach(transaction -> System.out.println(transaction.toString()));
    }
}