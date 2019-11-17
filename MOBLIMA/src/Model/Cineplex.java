package Model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
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
			cinemaString = cinemaString.concat(getCinemas().get(i) + ",\n");
		cinemaString = cinemaString.substring(0, cinemaString.length()-2);

        String details = "";
        details += "Name: " + getName() + "\n"
				+ cinemaString; 
        return details;
    }
}