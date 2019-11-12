package Boundary;

import Controller.*;
import Model.*;

import java.util.*;

public class ViewBookingUI {
    private String movieGoer; 
    private TransactionsController transController = new TransactionsController();
    
    Scanner sc = new Scanner(System.in);
    
    public ViewBookingUI(String userName){
        this.movieGoer = userName;    
    }
    
    public void main(){
        System.out.println("Here is your booking history:");
        display();
    }

    public void display(){
        ArrayList<Transaction> transactionList = transController.readByMovieGoerUsername(movieGoer);
        transactionList.forEach(transaction -> System.out.println(transaction.toString()));
        
    }
}
