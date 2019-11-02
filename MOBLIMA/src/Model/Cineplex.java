package Model;

public class Cineplex {

	private String name;
	private Cinema[] cinemas;
	
	public Cineplex(String name, Cinema[] cinemas) {
		this.name = name;
		this.cinemas = cinemas;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Cinema[] getCinemas() {
		return cinemas;
	}
	
	public void setCinemas(Cinema[] cinemas) {
		this.cinemas = cinemas;
	}
	
}
