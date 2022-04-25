import jp.ac.kobe_u.cs.cream.*;

public class xdsl {
	IntVariable[][] puzzle;
	int puzzle_width;
	Network net = new Network();
	
	private void run() {
		initiatePuzzle("4");
		
		setValue("0","0","2");
		setValue("1","1","3");
		
		lessThan("2","0","3","0");
		greaterThan("2","2","2","3");
		greaterThan("2","3","3","3");
		greaterThan("3","0","3","1");

		solve();
	}

	private void initiatePuzzle(String width) {
		try { // puzzle with width of - set size
			puzzle_width = Integer.parseInt(width);
			puzzle = new IntVariable[Integer.parseInt(width)][Integer.parseInt(width)];
		} catch (Exception e) {
			System.out.println("Puzzle Initiation Failed.");
		}
	}

	private void setValue(String xTxt, String yTxt, String valTxt) {
		try { // cell - set a value
			int x = Integer.parseInt(xTxt), y = Integer.parseInt(yTxt);
			if (x < puzzle_width && y < puzzle_width) {
			    puzzle[x][y] = new IntVariable(net, Integer.parseInt(valTxt));
			} else {
			    System.out.println("Invalid Cell Chosen");
			}
		} catch (Exception e) {
			System.out.println("Error Assigning Value to Puzzle.");
		}
	}

	private void greaterThan(String xTxt, String yTxt, String x2Txt, String y2Txt) {
		try { // cell 0,1 greater than cell 0,2
			int x = Integer.parseInt(xTxt), x2 = Integer.parseInt(x2Txt);
			int y = Integer.parseInt(yTxt), y2 = Integer.parseInt(y2Txt);
			if (x < puzzle_width && y < puzzle_width) {
				if (x2 < puzzle_width && y2 < puzzle_width) {
					if (puzzle[x][y] == null) {
						puzzle[x][y] = new IntVariable(net, 1, puzzle_width);
					}
					if (puzzle[x2][y2] == null) {
						puzzle[x2][y2] = new IntVariable(net, 1, puzzle_width);
					}
					puzzle[x][y].gt(puzzle[x2][y2]);
				} else {
					System.out.println("Invalid Second Cell Chosen");
				}
			} else {
				System.out.println("Invalid First Cell Chosen");
			}
		} catch (Exception e) {
			System.out.println("Error Assigning Relation: " + xTxt+","+yTxt + " is greater than " + x2Txt+","+y2Txt);
		}
	}

	private void lessThan(String xTxt, String yTxt, String x2Txt, String y2Txt) {
		try { // cell 1,1 less than cell 1,2
			int x = Integer.parseInt(xTxt), x2 = Integer.parseInt(x2Txt);
			int y = Integer.parseInt(yTxt), y2 = Integer.parseInt(y2Txt);
			if (x < puzzle_width && y < puzzle_width) {
				if (x2 < puzzle_width && y2 < puzzle_width) {
					if (puzzle[x][y] == null) {
						puzzle[x][y] = new IntVariable(net, 1, puzzle_width);
					}
					if (puzzle[x2][y2] == null) {
						puzzle[x2][y2] = new IntVariable(net, 1, puzzle_width);
					}
					puzzle[x][y].lt(puzzle[x2][y2]);
				} else {
					System.out.println("Invalid Second Cell Chosen");
				}
			} else {
				System.out.println("Invalid First Cell Chosen");
			}
		} catch (Exception e) {
			System.out.println("Error Assigning Relation: " + xTxt+","+yTxt + " is less than " + x2Txt+","+y2Txt);
		}
	}

	private void solve() {
		for (int row = 0; row < puzzle_width; row++) {
			for (int col = 0; col < puzzle_width; col++) {
				if (puzzle[row][col] == null) {
					// this cell has not been set.
					puzzle[row][col] = new IntVariable(net, 1, puzzle_width);
				}
			}
		}
		// Ensures row values are distinct.
		IntVariable[] vs  = new IntVariable[puzzle_width];
		for (int row = 0; row < puzzle_width; row++) {
			for (int cell = 0; cell < puzzle_width; cell++) {
				vs[cell] = puzzle[row][cell];
			}
			new NotEquals(net, vs);
		}
		// Ensures column values are distinct.
		for (int col = 0; col < puzzle_width; col++) {
			for (int cell = 0; cell < puzzle_width; cell++) {
				vs[cell] = puzzle[cell][col];
			}
			new NotEquals(net, vs);
		}
		// solve Puzzle
		System.out.println("\nSOLUTIONS:");
		Solver solver = new DefaultSolver(net);
		for (solver.start(); solver.waitNext(); solver.resume()) {
			Solution solution = solver.getSolution();
			for (int i = 0; i < puzzle_width; i++) {
				for (int j = 0; j < puzzle_width; j++) {
				System.out.print(solution.getIntValue(puzzle[i][j]) + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		solver.stop();
	}
	
	public static void main(String[] args) {
		new xdsl().run();
	}

}