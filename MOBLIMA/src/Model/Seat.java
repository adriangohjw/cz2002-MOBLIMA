package cineplex;

public class Seat {
	private int seatID;
	private boolean occupied = false;
	
	public Seat(int id) {
		seatID = id;
	}
	
	public Seat(int id, boolean state) {
		seatID = id;
		occupied = state;
	}
	
	public int getSeatID() { return seatID;}
	public boolean isOccupied() { return occupied;}
	
	public void assign() {
		occupied = true;
	}
	public void unassign() {
		occupied = false;
	}
}
