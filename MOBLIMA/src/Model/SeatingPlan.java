package cineplex;

public class SeatingPlan {
	private Seat [][] layout;
	private int row;
	private int column;
	
	public SeatingPlan(int row, int column) {
		this.row = row;
		this.column = column;
		for (int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++)
				layout[i][j] = new Seat(i*this.row+j);
		}
	}
	
	public void printLayout( ) {
		System.out.print("\nx means occupied, o means not occupied.\n");
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if(layout[i][j].isOccupied()) System.out.print("x ");
				else System.out.print("o ");
			}
			System.out.print("\n");
		}
	}

	public int getRow() { return row;}
	public int getColumn() { return column;}
	public int getNumSeats() { return row*column;}
	
	public void assignSeats(int id) {
		int i = id/row;
		layout[i][id - row*i].assign();
	}
	public void unassignSeats(int id) {
		int i = id/row;
		layout[i][id - row*i].unassign();
	}
}
