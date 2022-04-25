grammar FutoShikiSolver;

@header {
	import jp.ac.kobe_u.cs.cream.*;
	}
	
@members {
	IntVariable[][] puzzle;
	int puzzle_width;
	Network net = new Network();
	}

prog		:	puzzle NEWLINE
			(cell NEWLINE)+
			'rules {' ((greaterThan) | (lessThan))+ '}' NEWLINE
			solve '.';

puzzle		:	'puzzle with grid of ' INT
				{
					try {
						puzzle_width = Integer.parseInt($INT.text);
						puzzle = new IntVariable[Integer.parseInt($INT.text)][Integer.parseInt($INT.text)];
					} catch (Exception e) {
						System.out.println("Puzzle Initiation Failed.");
					}

				};

cell		:	'cell ' xGreater=INT','yGreater=INT '=' val=INT
				{
					try {
						int x = Integer.parseInt($xGreater.text), y = Integer.parseInt($yGreater.text);
						if (x < puzzle_width && y < puzzle_width) {
						    puzzle[x][y] = new IntVariable(net, Integer.parseInt($val.text));
						} else {
						    System.out.println("Invalid Cell Chosen");
						}
					} catch (Exception e) {
						System.out.println("Error Assigning Value to Puzzle.");
					}
				};

greaterThan	:	'cell ' xGreater=INT','yGreater=INT ' is greater than cell ' xLesser=INT','yLesser=INT
				{
					try { // cell 0,1 greater than cell 0,2
						int x = Integer.parseInt($xGreater.text), x2 = Integer.parseInt($xLesser.text);
						int y = Integer.parseInt($yGreater.text), y2 = Integer.parseInt($yLesser.text);
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
						System.out.println("Error Assigning Relation: " + $xGreater.text+","+$yGreater.text + " is greater than " + $xLesser.text+","+$yLesser.text);
					}
				};

lessThan	:	'cell ' xGreater=INT','yGreater=INT ' is less than cell ' xLesser=INT','yLesser=INT
				{
					try { // cell 1,1 less than cell 1,2
						int x = Integer.parseInt($xGreater.text), x2 = Integer.parseInt($xLesser.text);
						int y = Integer.parseInt($yGreater.text), y2 = Integer.parseInt($yLesser.text);
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
						System.out.println("Error Assigning Relation: " + $xGreater.text+","+$yGreater.text + " is greater than " + $xLesser.text+","+$yLesser.text);
					}
				};

solve		:	'solve' {
					for (int row = 0; row < puzzle_width; row++) {
						for (int col = 0; col < puzzle_width; col++) {
							if (puzzle[row][col] == null) { // this cell has not been set.
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
				};

INT	:	'0'..'9'+;
NEWLINE	:	'\r'? '\n';