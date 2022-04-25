package part6_v2;

import java.util.HashMap;
import jp.ac.kobe_u.cs.cream.*;

public class Futoshiki {
	static Cell[][] puzzle;
    
	public static void main(String[] args) {
		initialisePuzzle();
		int n = puzzle[0].length;
		System.out.println();
		Network net = new Network();
		//System.out.println("grid width = " + puzzle[0].length);
		IntVariable[][] v = new IntVariable[n][n];
		IntVariable[] vs = new IntVariable[n];

		// Create constraint-variables
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (puzzle[i][j].value == 0)
					// A zero in the matrix represents an empty cell
					v[i][j] = new IntVariable(net, 1, n);
				else
					// A non-zero in the matrix represents a fixed value
					v[i][j] = new IntVariable(net, puzzle[i][j].value);
			}
		}

		// Each row must contain distinct values
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				vs[j] = v[i][j];
			}
			new NotEquals(net, vs);
		}
		
		// satisfy greaters and lessers
		for (int i = 0; i < n*n; i++) {
			//vs[i] = v[]
		}

		// Each column must contain distinct values
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				vs[i] = v[i][j];
			}
			new NotEquals(net, vs);
		}
		
		// carry out other constraints V^<>
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (puzzle[i][j].eastCompare == Comparison.GREATER_THAN) {
					System.out.println(vs[j] + " has GREATER_THAN eastCompare");
					v[i][j].gt(v[i][j + 1]);
				} else if (puzzle[i][j].eastCompare == Comparison.LESS_THAN) {
					System.out.println(vs[j] + " has LESS_THAN eastCompare");
					v[i][j].lt(v[i][j + 1]);
				}
				if (puzzle[i][j].westCompare == Comparison.GREATER_THAN) {
					System.out.println(vs[j] + " has GREATER_THAN westCompare");
					v[i][j].gt(v[i][j -1]);
				} else if (puzzle[i][j].westCompare == Comparison.LESS_THAN) {
					System.out.println(vs[j] + " has LESS_THAN westCompare");
					v[i][j].lt(v[i][j -1]);
				}
				if (puzzle[i][j].southCompare == Comparison.GREATER_THAN) {
					System.out.println(vs[i] + " has GREATER_THAN southCompare");
					v[i][j].gt(v[i+1][j]);
				} else if (puzzle[i][j].southCompare == Comparison.LESS_THAN) {
					System.out.println(vs[i] + " has LESS_THAN southCompare");
					v[i][j].lt(v[i+1][j]);
				}
				if (puzzle[i][j].northCompare == Comparison.GREATER_THAN) {
					System.out.println(vs[i] + " has GREATER_THAN northCompare");
					v[i][j].gt(v[i-1][j]);
				} else if (puzzle[i][j].northCompare == Comparison.LESS_THAN) {
					System.out.println(vs[i] + " has LESS_THAN northCompare");
					v[i][j].gt(v[i-1][j]);
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
	
	private static void initialisePuzzle() {
		Cell[][] cells = {
					{new Cell(2),new Cell(),new Cell(),new Cell()},
				 	{new Cell(),new Cell(3),new Cell(),new Cell()},
				 	{new Cell(0,"LTS"),new Cell(),new Cell(0,"GTE"),new Cell(0,"GTS")},
				 	{new Cell(0,"GTE"),new Cell(),new Cell(),new Cell()}
				 	};
		puzzle = cells;
	}

}

class Cell {
	static int id = 0;
	int value = 0;
	Comparison northCompare = null;
	Comparison eastCompare = null;
	Comparison southCompare = null;
	Comparison westCompare = null;
	HashMap<String, Comparison> comparisons;

	public Cell() {
		id++;
	}

	public Cell(int v) {
		this();
		this.value = v;
	}

	public Cell(int v, String compareTxt) {
		this();
		this.value = v;
		initialiseComparisons();
		assignComparison(compareTxt.toUpperCase());
		cellToString();
	}

	private void initialiseComparisons() {
		comparisons = new HashMap<String, Comparison>();
		comparisons.put("LT", Comparison.LESS_THAN);
		comparisons.put("GT", Comparison.GREATER_THAN);
	}

	private void assignComparison(String compareTxt) {
		String lesserOrGreater = compareTxt.substring(0, 2);
		String nesw = compareTxt.substring(2);
		assignComparison(nesw, lesserOrGreater);
	}

	private void assignComparison(String nesw, String LTorGT) {
		if (nesw.equalsIgnoreCase("N")) {
			northCompare = comparisons.get(LTorGT) != null ? comparisons
					.get(LTorGT) : null;
		} else if (nesw.equalsIgnoreCase("E")) {
			eastCompare = comparisons.get(LTorGT) != null ? comparisons
					.get(LTorGT) : null;
		} else if (nesw.equalsIgnoreCase("S")) {
			southCompare = comparisons.get(LTorGT) != null ? comparisons
					.get(LTorGT) : null;
		} else if (nesw.equalsIgnoreCase("W")) {
			westCompare = comparisons.get(LTorGT) != null ? comparisons
					.get(LTorGT) : null;
		}
	}

	private void cellToString() {
		System.out.println("Cell id = " + id + ", value = " + value);
		System.out.println(northCompare);
		System.out.println(eastCompare);
		System.out.println(southCompare);
		System.out.println(westCompare);
	}
}

enum Comparison {
	LESS_THAN, GREATER_THAN
}