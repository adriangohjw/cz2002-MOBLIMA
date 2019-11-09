package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {

	private String name;
	private ArrayList<Cinema> cinemas;  // at least 3 cinemas
	
	public Cineplex(String name, ArrayList<Cinema> cinemas) {
		this.name = name;
		this.cinemas = cinemas;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}
	
	public void setCinemas(ArrayList<Cinema> cinemas) {
		this.cinemas = cinemas;
	}

	public String toString(){
        String cinemaString = "";
        for (int i=0; i<getCinemas().size(); i++)
			cinemaString = cinemaString.concat(getCinemas().get(i) + ",");
		cinemaString = cinemaString.substring(0, cinemaString.length()-1);

        String details = "";
        details += "Name: " + getName() + "\n"
				+ "List of Cinemas (by code): " + cinemaString; 
        return details;
    }
}