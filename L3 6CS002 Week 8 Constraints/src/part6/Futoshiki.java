package part6;

import java.util.HashMap;

import jp.ac.kobe_u.cs.cream.*;

public class Futoshiki {
	static Cell[][] puzzle2;
    static int[][] puzzle =
	{{2,0,0,0},
	 {0,3,0,0},
	 {0,0,0,0},
	 {0,0,0,0}};
    
	public static void main(String[] args) {
		initialisePuzzle();
		Network net = new Network();
		int n = puzzle[0].length;
		System.out.println("grid width = " + puzzle[0].length);
		IntVariable[][] v = new IntVariable[n][n];
		IntVariable[] vs = new IntVariable[n];

		// Create constraint-variables
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (puzzle[i][j] == 0)
					// A zero in the matrix represents an empty cell
					v[i][j] = new IntVariable(net, 1, n);
				else
					// A non-zero in the matrix represents a fixed value
					v[i][j] = new IntVariable(net, puzzle[i][j]);
			}
		}

		// Each row must contain distinct values
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				vs[j] = v[i][j];
			new NotEquals(net, vs);
		}

		// Each column must contain distinct values
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++)
				vs[i] = v[i][j];
			new NotEquals(net, vs);
		}

		int x = -1;

		try {
			x = 2;
			// Each box must contain distinct values
			for (int i0 = 0; i0 < n; i0 += x) {
				for (int j0 = 0; j0 < n; j0 += x) {
					int k = 0;
					for (int i = i0; i < i0 + x; i++)
						for (int j = j0; j < j0 + x; j++)
							vs[k++] = v[i][j];
					new NotEquals(net, vs);
				}
			}
			System.out.println(x + " is right!");
		} catch (Exception e) {
			System.out.println(x + " isn't right!");
		}

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
/*		
		HashMap <String, Comparison> comparisons = new HashMap<String, Comparison>();
		comparisons.put("LT", Comparison.LESS_THAN);
		comparisons.put("GT", Comparison.GREATER_THAN);
*/
		Cell[][] cells = {
					{new Cell(2),new Cell(),new Cell(),new Cell()},
				 	{new Cell(),new Cell(3),new Cell(),new Cell()},
				 	{new Cell(0,"LTS"),new Cell(),new Cell(0,"GTE"),new Cell(0,"GTS")},
				 	{new Cell(0,"GTE"),new Cell(),new Cell(),new Cell()}
				 	};
		puzzle2 = cells;
	}

}

class Cell {
	int value = 0;
	Relation northRelation = Relation.NA;
	Relation eastRelation = Relation.NA;
	Relation southRelation = Relation.NA;
	Relation westRelation = Relation.NA;
	HashMap <String, Relation> relationships = new HashMap<String, Relation>();
	
	public Cell() {
	}
	
	public Cell(int v) {
		this.value = v;
	}
	
	public Cell(int v, String relations) {
		this.value = v;
		relationships.put("LTN", northRelation = Relation.LESS_THAN);
		relationships.put("GTN", northRelation = Relation.GREATER_THAN);
		relationships.put("LTE", eastRelation = Relation.LESS_THAN);
		relationships.put("GTE", eastRelation = Relation.GREATER_THAN);
		relationships.put("LTS", southRelation = Relation.LESS_THAN);
		relationships.put("GTS", southRelation = Relation.GREATER_THAN);
		relationships.put("LTW", westRelation = Relation.LESS_THAN);
		relationships.put("GTW", westRelation = Relation.GREATER_THAN);
	}
}

enum Relation {
	LESS_THAN, GREATER_THAN, NA
}