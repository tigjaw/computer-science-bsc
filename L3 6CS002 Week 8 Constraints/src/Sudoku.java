import jp.ac.kobe_u.cs.cream.*;

public class Sudoku {
    static int[][] puzzle =
	{{0,9,0,0,0,0,0,1,0},
	 {8,0,4,0,2,0,3,0,7},
	 {0,6,0,9,0,7,0,2,0},
	 {0,0,5,0,3,0,1,0,0},
	 {0,7,0,5,0,1,0,3,0},
	 {0,0,3,0,9,0,8,0,0},
	 {0,2,0,8,0,5,0,6,0},
	 {1,0,7,0,6,0,4,0,9},
	 {0,3,0,0,0,0,0,8,0}};

    public static void main(String[] args) {
    	Network net = new Network();
    	int n = 9;
    	IntVariable[][] v = new IntVariable[n][n];
    	IntVariable[] vs = new IntVariable[n];

	// Create constraint-variables
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			if (puzzle[i][j] == 0)
			    // A zero in the matrix represents and empty cell
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

	// Each box must contain distinct values
    	for (int i0 = 0; i0 < n; i0 += 3) {
    		for (int j0 = 0; j0 < n; j0 += 3) {
    			int k = 0;
    			for (int i = i0; i < i0+3; i++)
    				for (int j = j0; j < j0+3; j++)
    					vs[k++] = v[i][j];
    			new NotEquals(net, vs);
    		}
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
}
