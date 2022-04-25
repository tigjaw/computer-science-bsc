package part6_v1;

import java.util.HashMap;
import jp.ac.kobe_u.cs.cream.*;

public class Futoshiki {
	static Cell[][] puzzle;
    
	public static void main(String[] args) {
		initialisePuzzle();
		System.out.println();
		Network net = new Network();
		int n = puzzle[0].length;
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
				if (puzzle[i][j].eastRelation == Relation.GREATER_THAN) {
					System.out.println(vs[j] + " has GREATER_THAN eastCompare");
					new IntComparison(net, 3, vs[j], vs[j+1]);
				} else if (puzzle[i][j].eastRelation == Relation.LESS_THAN) {
					System.out.println(vs[j] + " has LESS_THAN eastCompare");
					new IntComparison(net, 1, vs[j], vs[j+1]);
				}
				if (puzzle[i][j].westRelation == Relation.GREATER_THAN) {
					System.out.println(vs[j] + " has GREATER_THAN westCompare");
					new IntComparison(net, 3, vs[j], vs[j-1]);
				} else if (puzzle[i][j].westRelation == Relation.LESS_THAN) {
					System.out.println(vs[j] + " has LESS_THAN westCompare");
					new IntComparison(net, 1, vs[j], vs[j-1]);
				}
			}
			new NotEquals(net, vs);
		}

		// Each column must contain distinct values
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				vs[i] = v[i][j];
				if (puzzle[i][j].southRelation == Relation.GREATER_THAN) {
					System.out.println(vs[i] + " has GREATER_THAN southCompare");
					new IntComparison(net, 3, vs[i], vs[i-1]);
				} else if (puzzle[i][j].southRelation == Relation.LESS_THAN) {
					System.out.println(vs[i] + " has LESS_THAN southCompare");
					new IntComparison(net, 1, vs[i], vs[i-1]);
				}
				if (puzzle[i][j].northRelation == Relation.GREATER_THAN) {
					System.out.println(vs[i] + " has GREATER_THAN northCompare");
					new IntComparison(net, 3, vs[i], vs[i+1]);
				} else if (puzzle[i][j].northRelation == Relation.LESS_THAN) {
					System.out.println(vs[i] + " has LESS_THAN northCompare");
					new IntComparison(net, 1, vs[i], vs[i+1]);
				}
			}
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
			//System.out.println(x + " is right!");
		} catch (Exception e) {
			//System.out.println(x + " isn't right!");
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
	Relation northRelation = null;
	Relation eastRelation = null;
	Relation southRelation = null;
	Relation westRelation = null;
	HashMap <String, Relation> relationInput = new HashMap<String, Relation>();
	
	public Cell() {
		id++;
	}
	
	public Cell(int v) {
		this();
		this.value = v;
	}
	
	public Cell(int v, String relations) {
		this();
		this.value = v;
		initialiseRelations();
		System.out.println("Cell id = " + id + ", value = " + value);
		assignRelationships(relations.toUpperCase());
		printRelations();
	}
	
	private void initialiseRelations() {
		relationInput.put("LT", Relation.LESS_THAN);
		relationInput.put("GT", Relation.GREATER_THAN);
	}
	
	private void assignRelationships(String relations) {
		String lesserOrGreater = relations.substring(0, 2);
		String nesw = relations.substring(2);
		assignRelation(nesw, lesserOrGreater);
	}
	
	private void assignRelation(String nesw, String lesserOrGreater) {
		if (nesw.equalsIgnoreCase("N")) {
			northRelation = relationInput.get(lesserOrGreater) != null ? relationInput.get(lesserOrGreater) : null;
		} else if (nesw.equalsIgnoreCase("E")) {
			eastRelation = relationInput.get(lesserOrGreater) != null ? relationInput.get(lesserOrGreater) : null;
		} else if (nesw.equalsIgnoreCase("S")) {
			southRelation = relationInput.get(lesserOrGreater) != null ? relationInput.get(lesserOrGreater) : null;
		} else if (nesw.equalsIgnoreCase("W")) {
			westRelation = relationInput.get(lesserOrGreater) != null ? relationInput.get(lesserOrGreater) : null;
		}
	}

	private void printRelations() {
		//System.out.println("Cell id = " + id + ", value = " + value);
		System.out.println(northRelation);
		System.out.println(eastRelation);
		System.out.println(southRelation);
		System.out.println(westRelation);
	}
}

enum Relation {
	LESS_THAN, GREATER_THAN
}