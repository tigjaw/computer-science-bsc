package futoshikiIDSL;

import jp.ac.kobe_u.cs.cream.*;

/**
 * public class Futoshiki.
 * Futoshiki Solver using Internal-DSL.
 * @author 0622803 - Joshua Woodyatt
 */
public class Futoshiki {
	private static Cell[][] puzzle;
	private static String[][] cellIDs;
	private int gridSize;
    private Network net;
    
    /**
     * Makeshift Constructor.
     * Called by with_grid_of method.
     */
    private void construct() {
    	net = new Network();
    	gridSize = puzzle.length;
    	puzzle  = new Cell[gridSize][gridSize];
    	cellIDs = new String[gridSize][gridSize];
    }
	
	public Futoshiki puzzle() {
		return new Futoshiki();
	}
	
	/**
	 * Constructs the puzzle grid.
	 * Must be called before values, IDs and
	 * Constraints are set.
	 * @param i (int) : gridSize
	 * @return Futoshiki : this
	 */
	public Futoshiki with_grid_of(int i) {
		puzzle = new Cell[i][i];
		construct();
		return this;
	}
	
	/** Counts how many times row_values method has been called. */
	private int rowIncrement = 0;
	
	/**
	 * Call this method for every row of values required.
	 * Values should be <= gridSize.
	 * @param r (int...) : values of single row
	 * @return Futoshiki : this
	 */
	public Futoshiki row_values(int... r) {
		checkPuzzleInitiatedCorrectly(r.length, gridSize*2);
		if(rowIncrement == 0) {
			System.out.println("\nRow Values:");
		}
		if (rowIncrement < gridSize) {
			int[] row = r;
			for (int col = 0; col < gridSize; col++) {
				System.out.print(row[col] + ",");
				if (row[col] == 0 || row[col] > gridSize)
					// A zero in the matrix represents an empty cell
					puzzle[rowIncrement][col] = new Cell(net, 1, gridSize);
				else
					// A non-zero in the matrix represents a fixed value
					puzzle[rowIncrement][col] = new Cell(net, row[col]);
			}
			System.out.println();
			rowIncrement++;
		}
		return this;
	}

	/** Counts how many times cell_IDs method has been called. */
	private int rowIncrementIds = 0;
	
	/**
	 * Call this method for every row of IDs required.
	 * IDs should be unique for best results.
	 * @param rowIDs (String...) : IDs of a single row
	 * @return Futoshiki : this
	 */
	public Futoshiki cell_IDs(String... rowIDs) {
		if(rowIncrementIds == 0) {
			System.out.println("\nRow IDs:");
		}
		checkPuzzleInitiatedCorrectly(rowIDs.length, gridSize*2);
		for (int cell = 0; cell < rowIDs.length; cell++) {
			String id = rowIDs[cell];
			cellIDs[rowIncrementIds][cell] = id;
			System.out.print(cellIDs[rowIncrementIds][cell] + ",");
		}
		System.out.println();
		rowIncrementIds++;
		return this;
	}
	
	/**
	 * Finds a Cell with specified ID. If the ID
	 * can't be found the user is informed and the
	 * program exits.
	 * @param id (String) : ID of cell to get
	 * @return Cell : the cell found
	 */
	public Cell cell(String id) {
		checkPuzzleInitiatedCorrectly(gridSize, rowIncrement + rowIncrementIds);
		for (int row = 0; row < cellIDs.length; row++) {
			for (int cell = 0; cell < cellIDs.length; cell++) {
				if (cellIDs[row][cell].equalsIgnoreCase(id)) {
					return puzzle[row][cell];
				}
			}
		}
		System.out.println("Cell with ID " + id + " not found.");
		System.exit(0);
		return null;
	}

	/**
	 * Ensures row values are distinct.
	 * ID's and Values MUST be set first.
	 * @return Futoshiki : this
	 */
	public Futoshiki distinct_rows() {
		checkPuzzleInitiatedCorrectly(gridSize, rowIncrement + rowIncrementIds);
		IntVariable[] vs  = new IntVariable[gridSize];
		for (int row = 0; row < gridSize; row++) {
			for (int cell = 0; cell < gridSize; cell++) {
				vs[cell] = puzzle[row][cell];
			}
			new NotEquals(net, vs);
		}
		return this;
	}

	/**
	 * Ensures column values are distinct.
	 * ID's and Values MUST be set first.
	 * @return Futoshiki : this
	 */
	public Futoshiki distinct_columns() {
		checkPuzzleInitiatedCorrectly(gridSize, rowIncrement + rowIncrementIds);
		IntVariable[] vs  = new IntVariable[gridSize];
		for (int col = 0; col < gridSize; col++) {
			for (int cell = 0; cell < gridSize; cell++) {
				vs[cell] = puzzle[cell][col];
			}
			new NotEquals(net, vs);
		}
		return this;		
	}
	
	/**
	 * Solves the Puzzle hopefully!
	 * No,seriously now, it does.
	 */
	public void solve() {
		checkPuzzleInitiatedCorrectly(gridSize, rowIncrement + rowIncrementIds);
		System.out.println("\nSOLUTIONS:");
		Solver solver = new DefaultSolver(net);
		for (solver.start(); solver.waitNext(); solver.resume()) {
			Solution solution = solver.getSolution();
			for (int i = 0; i < gridSize; i++) {
				for (int j = 0; j < gridSize; j++)
					System.out.print(solution.getIntValue(puzzle[i][j]) + " ");
				System.out.println();
			}
			System.out.println();
		}
		solver.stop();
	}

	/**
	 * Checks for problems with the Solver and produces output
	 * depending on the problems it encounters.
	 * @param rowLength (int)
	 * 		: Ensures all IDS and Values of a single row have been set
	 * @param rowsImplemented (int)
	 * 		: Ensures all rows have been implemented with values and IDs.
	 */
	private void checkPuzzleInitiatedCorrectly(int rowLength, int rowsImplemented) {
		boolean error = false;
		if (net == null) {
			error = true;
			System.out.println("Network Not Initiated");
		}
		if (puzzle == null) {
			error = true;
			System.out.println("Puzzle Not Initiated");
		}
		if (puzzle == null) {
			error = true;
			System.out.println("Variable Arrays Not Initiated");
		}
		if (cellIDs == null) {
			error = true;
			System.out.println("Cell_IDs Array Not Initiated");
		}
		if (gridSize == 0) {
			error = true;
			System.out.println("Puzzle Not Initiated Correctly.");
		}
		if (rowLength != gridSize) {
			error = true;
			System.out.println("Must Supply " + gridSize + " Values/IDs per row.");
		}
		if(rowsImplemented != gridSize * 2) {
			error = true;
			System.out.println("Must Supply " + gridSize
					+ " rows of " + gridSize + " values & IDs as specified by grid size.");
		}
		if (error == true)
			System.exit(0);
	}
	
	public class Cell extends IntVariable {

		public Cell(Network net, int min, int gridSize) {
			super(net, min, gridSize);
		}
		
		public Cell(Network net, int value) {
			super(net, value);
		}

		/**
		 * Calls gt() method of super-type (IntVariable) with
		 * Cell found by Futoshiki.this.cell(id) as a
		 * parameter. See Futoshiki's cell(String) method.
		 * @param id (String) : ID of Cell to find
		 * @return Futoshiki : Futoshiki.this
		 */
		public Futoshiki is_greater_than_cell(String id) {
			gt(Futoshiki.this.cell(id));
			return Futoshiki.this;
		}
		
		/**
		 * Calls lt() method of super-type (IntVariable) with
		 * Cell found by Futoshiki.this.cell(id) as a
		 * parameter. See Futoshiki's cell(String) method.
		 * @param id (String) : ID of Cell to find
		 * @return Futoshiki : Futoshiki.this
		 */
		public Futoshiki is_less_than_cell(String id) {
			lt(Futoshiki.this.cell(id));
			return Futoshiki.this;
		}
	}

}