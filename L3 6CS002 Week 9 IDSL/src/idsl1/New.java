package idsl1;

import jp.ac.kobe_u.cs.cream.*;
import java.util.ArrayList;

public class New {
    public New with = this;

	private String[] vars;
    private ArrayList<IntVariable> iVars;
    private Network net;
    private String topOfSum;
    private String bottomOfSum;
    private String result;

    public New() {
    	iVars = new ArrayList<IntVariable>();
    	net = new Network();
    }

    public static New CryptArith() {
    	return new New();
    }

    public New variables(String... varList) {
    	vars = varList;
    	for(String c : vars) {
    		iVars.add(new IntVariable(net, 0, 9));
    	}
    	return this;
    }

    public New nonzero(String c) {
    	findVar(c).notEquals(0);
    	return this;
    }

    public New auxiliary(String c, int i, String d) {
    	iVars.set(iVars.indexOf(findVar(d)), findVar(c).multiply(i));
    	return this;
    }

    public New sum(String auxName, String... varsToSum) {
		IntVariable auxVar = findVar(auxName);
		IntVariable sum;
	
		sum = findVar(varsToSum[0]);
		for(int i = 1; i < varsToSum.length; i++) {
		    sum = sum.add(findVar(varsToSum[i]));
		}
		iVars.set(iVars.indexOf(auxVar), sum);
	
		return this;
    }

    public New topofsum(String var) {
		topOfSum = var;
		return this;
    }

    public New bottomofsum(String var) {
		bottomOfSum = var;
		return this;
    }

    public New result(String var) {
		result = var;
		return this;
    }

    public New distinct(String... varList) {
		IntVariable[] vs = new IntVariable[varList.length];
	
		for(int i = 0; i < varList.length; i++) {
		    vs[i] = findVar(varList[i]);
		}
		new NotEquals(net, vs);
	
		return this;
    }

    public void solve() {
		IntVariable topVar = findVar(topOfSum);
		IntVariable bottomVar = findVar(bottomOfSum);
		IntVariable res = findVar(result);
	
		res.equals(topVar.add(bottomVar));
	
		Solver solver = new DefaultSolver(net);
	
		Solution solution = solver.findFirst();
	
		if (solution != null) {
		    for(int i = 0; i < vars.length; i++) {
			System.out.println("Var " + vars[i] + " = " + solution.getIntValue(iVars.get(i)));
		    }
		} else {
		    System.out.println("No solution found");
		}
    }

    private IntVariable findVar(String c) {
		for(int i = 0; i < vars.length; i++) {
		    if (vars[i].equals(c)) {
			return iVars.get(i);
		    }
		}
		return null;
	}
}
