package part6_v3Final;

import java.io.*;
import java.util.HashMap;
import jp.ac.kobe_u.cs.cream.*;

public class Futoshiki {
	static Cell[][] puzzle;
    
	public static void main(String[] args) {
		initialisePuzzle("puzzle3.txt");
		int n = puzzle.length;
		System.out.println();
		Network net = new Network();
		//System.out.println("row width = " + puzzle.length);
		IntVariable[][] v = new IntVariable[n][n];
		IntVariable[] vs = new IntVariable[n];

		// Create constraint-variables
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (puzzle[row][col].value == 0)
					// A zero in the matrix represents an empty cell
					v[row][col] = new IntVariable(net, 1, n);
				else
					// A non-zero in the matrix represents a fixed value
					v[row][col] = new IntVariable(net, puzzle[row][col].value);
			}
		}

		// Each row must contain distinct values
		for (int row = 0; row < n; row++) {
			for (int cell = 0; cell < n; cell++) {
				vs[cell] = v[row][cell];
			}
			new NotEquals(net, vs);
		}

		// Each column must contain distinct values
		for (int col = 0; col < n; col++) {
			for (int cell = 0; cell < n; cell++) {
				vs[cell] = v[cell][col];
			}
			new NotEquals(net, vs);
		}
		
		// Carry out Less Than and Greater Than constraints
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (puzzle[row][col].eastCompare == Comparison.GREATER_THAN) {
					v[row][col].gt(v[row][col + 1]);
				} else if (puzzle[row][col].eastCompare == Comparison.LESS_THAN) {
					v[row][col].lt(v[row][col + 1]);
				}
				if (puzzle[row][col].westCompare == Comparison.GREATER_THAN) {
					v[row][col].gt(v[row][col -1]);
				} else if (puzzle[row][col].westCompare == Comparison.LESS_THAN) {
					v[row][col].lt(v[row][col -1]);
				}
				if (puzzle[row][col].southCompare == Comparison.GREATER_THAN) {
					v[row][col].gt(v[row+1][col]);
				} else if (puzzle[row][col].southCompare == Comparison.LESS_THAN) {
					v[row][col].lt(v[row+1][col]);
				}
				if (puzzle[row][col].northCompare == Comparison.GREATER_THAN) {
					v[row][col].gt(v[row-1][col]);
				} else if (puzzle[row][col].northCompare == Comparison.LESS_THAN) {
					v[row][col].gt(v[row-1][col]);
				}
			}
		}

		System.out.println("\nSOLUTIONS:");
		Solver solver = new DefaultSolver(net);
		for (solver.start(); solver.waitNext(); solver.resume()) {
			Solution solution = solver.getSolution();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					System.out.print(solution.getIntValue(v[i][j]) + " ");
				System.out.println();
			}
			System.out.println();
		}
		solver.stop();
	}

	private static void initialisePuzzle(String filePath) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String str;
			int x = 0;
			while ((str = br.readLine()) != null) {
				String line[] = str.split(",");
				//System.out.println("line.length = " + line.length);
				if (x == 0) { // reset puzzle with data
					puzzle = new Cell[line.length][line.length];
				}
				for (int i = 0; i < line.length; i++) {
					puzzle[x][i] = new Cell(line[i]);
					System.out.println("cell [" + x + "][" + i + "] = " + line[i]);
				}
				x++;
				System.out.println();
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error Creating Puzzle, now using Default Puzzle.");
			//e.printStackTrace();
			useDefaultPuzzle();
		}
	}
	
	private static void useDefaultPuzzle() {
		Cell[][] defaultCells = {
				{new Cell(2),new Cell(),new Cell(),new Cell()},
			 	{new Cell(),new Cell(3),new Cell(),new Cell()},
			 	{new Cell(0,"LTS"),new Cell(),new Cell(0,"GTE"),new Cell(0,"GTS")},
			 	{new Cell(0,"GTE"),new Cell(),new Cell(),new Cell()}
			 	};
		puzzle = defaultCells;
	}
}

class Cell {
	private static int id = 0;
	protected int value = 0;
	protected Comparison northCompare, eastCompare, southCompare,
			westCompare = null;
	protected HashMap<String, Comparison> comparisons;

	public Cell() {
		id++;
		initialiseComparisons();
	}
	public Cell(int v) {
		this();
		this.value = v;
	}
	public Cell(int v, String compareTxt) {
		this();
		this.value = v;
		assignComparison(compareTxt.toUpperCase());
	}
	
	public Cell(String cellString) {
		this();
		String[] cell = cellString.split("-");
		try {
			this.value = Integer.parseInt(cell[0]);
		} catch (Exception e) {
			this.value = 0;
		}
		for (int i = 1; i < cell.length; i++) {
			assignComparison(cell[i]);
		}
	}

	private void initialiseComparisons() {
		comparisons = new HashMap<String, Comparison>();
		comparisons.put("LT", Comparison.LESS_THAN);
		comparisons.put("GT", Comparison.GREATER_THAN);
	}

	private void assignComparison(String compareTxt) {
		try {
			String lesserOrGreater = compareTxt.substring(0, 2);
			String nesw = compareTxt.substring(2);
			assignComparison(nesw, lesserOrGreater);
		} catch (Exception e) {
			System.out.println("Couldn't Assign Relations");
		}
	}

	private void assignComparison(String nesw, String LTorGT) {
		if (nesw.equalsIgnoreCase("N")) {
			/*northCompare = comparisons.get(LTorGT) != null ? comparisons
					.get(LTorGT) : null;*/
			northCompare = comparisons.get(LTorGT);
		} else if (nesw.equalsIgnoreCase("E")) {
			eastCompare = comparisons.get(LTorGT);
		} else if (nesw.equalsIgnoreCase("S")) {
			southCompare = comparisons.get(LTorGT);
		} else if (nesw.equalsIgnoreCase("W")) {
			westCompare = comparisons.get(LTorGT);
		}
	}

	protected void cellToString() {
		System.out.println("Cell id = " + id + ", value = " + value);
		System.out.println("n = " + northCompare);
		System.out.println("e = " + eastCompare);
		System.out.println("s = " + southCompare);
		System.out.println("w = " + westCompare);
	}
}

enum Comparison {
	LESS_THAN, GREATER_THAN
}