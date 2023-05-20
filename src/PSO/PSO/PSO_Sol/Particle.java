package PSO.PSO.PSO_Sol;

public class Particle implements Comparable<Particle> {
	private int chessBoardLength;
	private int queenPosition[];
	private double velocity; // fitness
	private int conflicts; // pBest

	public Particle(int n) {
		chessBoardLength = n;
		queenPosition = new int[chessBoardLength];
		this.velocity = 0.0;
		this.conflicts = 0;
		initData();
	}

	public int compareTo(Particle p) {
		return this.conflicts - p.getConflicts();
	}

	public void computeConflicts() { // compute the number of conflicts to
										// calculate fitness
		String board[][] = new String[chessBoardLength][chessBoardLength]; // initialize
																// board
		int x = 0; // row
		int y = 0; // column
		int tempx = 0; // temprow
		int tempy = 0; // temcolumn

		int dx[] = new int[] { -1, 1, -1, 1 }; // to check for diagonal
		int dy[] = new int[] { -1, 1, 1, -1 }; // paired with dx to check for
												// diagonal

		boolean done = false; // used to check is checking fo diagonal is out of
								// bounds
		int conflicts = 0; // number of conflicts found

		clearBoard(board); // clears the board into empty strings
		plotQueens(board); // plots the Q in the board

		// Walk through each of the Queens and compute the number of conflicts.
		for (int i = 0; i < chessBoardLength; i++) {
			x = i;
			y = queenPosition[i];

			// Check diagonals.
			for (int j = 0; j < 4; j++) { // because of dx and dy where there
											// are 4 directions for diagonal
											// searching for conflicts
				tempx = x;
				tempy = y; // store coordinate in temp
				done = false;

				while (!done) {// traverse the diagonals
					tempx += dx[j];
					tempy += dy[j];

					if ((tempx < 0 || tempx >= chessBoardLength) || (tempy < 0 || tempy >= chessBoardLength)) { // if
																									// exceeds
																									// board
						done = true;
					} else {
						if (board[tempx][tempy].equals("Q")) {
							conflicts++;
						}
					}
				}
			}
		}

		this.conflicts = conflicts;

	}

	public void plotQueens(String[][] board) {
		for (int i = 0; i < chessBoardLength; i++) {
			board[i][queenPosition[i]] = "Q";
		}
	}

	public void clearBoard(String[][] board) {
		for (int i = 0; i < chessBoardLength; i++) {
			for (int j = 0; j < chessBoardLength; j++) {
				board[i][j] = "";
			}
		}
	}

	public void initData() {
		for (int i = 0; i < chessBoardLength; i++) {
			queenPosition[i] = i;
		}
	}

	public int getData(int index) {
		return this.queenPosition[index];
	}

	public void setData(int index, int value) {
		this.queenPosition[index] = value;
	}

	public int getConflicts() {
		return this.conflicts;
	}

	public void setConflicts(int conflicts) {
		this.conflicts = conflicts;
	}

	public double getVelocity() {
		return this.velocity;
	}

	public void setVelocity(double velocityScore) {
		this.velocity = velocityScore;
	}

	public int getMaxLength() {
		return chessBoardLength;
	}
}
