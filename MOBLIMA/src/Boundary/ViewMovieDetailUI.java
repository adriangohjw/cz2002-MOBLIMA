/* package Boundary;

import Controller.*;
import Model.*;

import java.util.*;

public class ViewMovieDetailUI {
    private String title;
//  private int another = 1;
    private MoviesController moviesController = new MoviesController();
    Scanner sc = new Scanner(System.in);

    ViewMovieDetailUI(String _title){
        this.title = _title;    
    };

//     public void main(){
//         while(another==1){
//             display();
//         }
//     }


    public void display(){
        ArrayList<Movie> movieList = moviesController.readByAttribute(0, title);
        movieList.forEach(movie -> System.out.println(movie.toString()));
        
//         System.out.println("Another movie? 1 for [y] and 0 for [no]");
//         another = sc.nextInt();

//         if(another==0){
//             System.out.println("Exit!");
//         }
    }
    
}
 */