package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {

	private String code;
	private String cinemaClass;
	protected SeatingPlan seatingPlan;
	private ArrayList<Session> sessions;
	
	public Cinema (String code, String cinemaClass, SeatingPlan seatingPlan) {
		this.code = code;
		this.cinemaClass = cinemaClass;
		this.seatingPlan = seatingPlan;
		this.sessions = new ArrayList<Session>();
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCinemaClass() {
		return cinemaClass;
	}
	
	public void setCinemaClass(String cinemaClass) {
		this.cinemaClass = cinemaClass;
	}

	public SeatingPlan getSeatingPlan() {
		return seatingPlan;
	}

	public void setSeatingPlan(SeatingPlan seatingPlan) {
		this.seatingPlan = seatingPlan;
	}

	public ArrayList<Session> getSessions() {
		return sessions;
	}

	public void setSessions(ArrayList<Session> sessions) {
		this.sessions = sessions;
	}
	
}
