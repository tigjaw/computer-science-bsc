package idsl2;

import jp.ac.kobe_u.cs.cream.*;
import java.util.ArrayList;

public class New {
	public New with = this;

	// private String[] vars;
	private ArrayList<IVar> iVars;
	private Network net;
	private String topOfSum;
	private String bottomOfSum;
	private String result;

	public New() {
		iVars = new ArrayList<IVar>();
		net = new Network();
	}

	public static New CryptArith() {
		return new New();
	}

	public New variables(String... varList) {
		// vars = varList;
		for (String c : varList) {
			iVars.add(new IVar(net, 0, 9, c));
		}
		return this;
	}

	public New nonzero(String c) {
		findVar(c).notEquals(0);
		return this;
	}

	public New sum(String auxName, String... varsToSum) {
		IVar auxVar = findVar(auxName);
		IVar sum;

		sum = findVar(varsToSum[0]);
		for (int i = 1; i < varsToSum.length; i++) {
			sum = sum.add(findVar(varsToSum[i]));
		}
		sum.setName(auxName);
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
		IVar[] vs = new IVar[varList.length];

		for (int i = 0; i < varList.length; i++) {
			vs[i] = findVar(varList[i]);
		}
		new NotEquals(net, vs);

		return this;
	}

	public void solve() {
		IVar topVar = findVar(topOfSum);
		IVar bottomVar = findVar(bottomOfSum);
		IVar res = findVar(result);

		res.equals(topVar.add(bottomVar));

		Solver solver = new DefaultSolver(net);

		Solution solution = solver.findFirst();

		if (solution != null) {
			for (int i = 0; i < iVars.size(); i++) {
				System.out.println("Var " + iVars.get(i).getName() + " = "
						+ solution.getIntValue(iVars.get(i)));
			}
		} else {
			System.out.println("No solution found");
		}
	}

	private IVar findVar(String c) {
		for (int i = 0; i < iVars.size(); i++) {
			if (iVars.get(i).getName().equals(c)) {
				return iVars.get(i);
			}
		}
		System.out.println("Unable to find " + c);
		return null;
	}

	public IVar variable(String name) {
		return findVar(name);
	}

	public class IVar extends IntVariable {
		private String name;

		public IVar(Network net, int min, int max, String name) {
			super(net, min, max);
			this.name = name;
		}

		public IVar(Network net, String name) {
			super(net);
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public New auxiliary(int x, String name) {
			IVar temp = new IVar(getNetwork(), name);
			new IntArith(getNetwork(), IntArith.MULTIPLY, temp, x, this);
			New.this.iVars.set(New.this.iVars.indexOf(New.this.findVar(name)),
					temp);
			return New.this;
		}

		public IVar add(IVar x) {
			IVar temp = new IVar(getNetwork(), "");
			new IntArith(getNetwork(), IntArith.ADD, temp, this, x);
			return temp;
		}

		public void equals(IVar x) {
			super.equals(x);
		}
	}
}
