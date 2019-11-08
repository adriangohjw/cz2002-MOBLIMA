package Controller;

import Boundary.*;

public class AppController {

    private boolean quit;
    private MainMenuUI mm;
	private MainController mainCtrl;
	
	public AppController() {
        quit = false;
        mainCtrl = new MainController();
		mm = new MainMenuUI(mainCtrl);
	}
	
	public void run() {
		while(!quit)
			mm.displayMenu();
	}
		
	protected void stop() {
		quit = true;
	}
}