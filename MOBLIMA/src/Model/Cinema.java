package Model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Cinema implements Serializable {

	private String code;
	private CinemaType cinemaType;
	protected SeatingPlan seatingPlan;
	private ArrayList<Session> sessions;
	
	public Cinema (String code, CinemaType cinemaType, SeatingPlan seatingPlan) {
		this.code = code;
		this.cinemaType = cinemaType;
		this.seatingPlan = seatingPlan;
		this.sessions = new ArrayList<Session>();
	}
	
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	
	public CinemaType getCinemaType() {return cinemaType;}
	public void setCinemaType(CinemaType cinemaClass) {this.cinemaType = cinemaClass;}

	public SeatingPlan getSeatingPlan() {return seatingPlan;}
	public void setSeatingPlan(SeatingPlan seatingPlan) {this.seatingPlan = seatingPlan;}

	public ArrayList<Session> getSessions() {return sessions;}
	public void setSessions(ArrayList<Session> sessions) {this.sessions = sessions;}

	public String toString(){
		String sessionsString = "";
		for (int i=0; i<sessions.size(); i++)
			sessionsString = sessionsString.concat("-----" + sessions.get(i) + "\n");
		sessionsString = sessionsString.substring(0, sessionsString.length());
		
		String details = "";
		details += "Cinema code: " + getCode() + "\n"
				+ "Cinema type: " + getCinemaType() + "\n"
				+ "Sessions: " + getSessions().size() +"\n" 
				+ sessionsString;
		return details;
	}	
}
