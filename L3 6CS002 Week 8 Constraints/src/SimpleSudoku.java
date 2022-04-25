import jp.ac.kobe_u.cs.cream.*;

public class SimpleSudoku {
    public static void main(String[] args) {
    	Network net = new Network();
    	int n = 9;
    	IntVariable[][] v = new IntVariable[n][n];
    	IntVariable[] vs = new IntVariable[n];

	// Create constraint-variables
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
		    v[i][j] = new IntVariable(net, 1, n);
    		}
    	}

	// Fixed-value constraints
	v[0][1].equals(9);
	v[0][7].equals(1);
	v[1][0].equals(8);
	v[1][2].equals(4);
	v[1][4].equals(2);
	v[1][6].equals(3);
	v[1][8].equals(7);
	v[2][1].equals(6);
	v[2][3].equals(9);
	v[2][5].equals(7);
	v[2][7].equals(2);
	v[3][2].equals(5);
	v[3][4].equals(3);
	v[3][6].equals(1);
	v[4][1].equals(7);
	v[4][3].equals(5);
	v[4][5].equals(1);
	v[4][7].equals(3);
	v[5][2].equals(3);
	v[5][4].equals(9);
	v[5][6].equals(8);
	v[6][1].equals(2);
	v[6][3].equals(8);
	v[6][5].equals(5);
	v[6][7].equals(6);
	v[7][0].equals(1);
	v[7][2].equals(7);
	v[7][4].equals(6);
	v[7][6].equals(4);
	v[7][8].equals(9);
	v[8][1].equals(3);
	v[8][7].equals(8);

	// Each row must contain distinct values
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			vs[j] = v[i][j];
    		}
    		new NotEquals(net, vs);
    	}

	// Each column must contain distinct values
    	for (int j = 0; j < n; j++) {
    		for (int i = 0; i < n; i++) {
    			vs[i] = v[i][j];
    		}
    		new NotEquals(net, vs);
    	}

	// Each box must contain distinct values
    	for (int i0 = 0; i0 < n; i0 += 3) {
    		for (int j0 = 0; j0 < n; j0 += 3) {
    			int k = 0;
    			for (int i = i0; i < i0+3; i++) {
    				for (int j = j0; j < j0+3; j++) {
    					vs[k++] = v[i][j];
    				}
    			}
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
