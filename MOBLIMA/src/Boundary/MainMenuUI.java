package Boundary;

import Controller.*;
import java.util.Scanner;

public class MainMenuUI {
	static Scanner sc = new Scanner(System.in);
	private MainController mainCtrl;
	
	public MainMenuUI(MainController mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

	public void displayMenu() {
		int choice;
		do {
			//print menu options
            choice = sc.nextInt();
		} while (mainCtrl.chooseAction(choice) == -1); 
	}	
}