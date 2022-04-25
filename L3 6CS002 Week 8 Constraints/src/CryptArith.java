import jp.ac.kobe_u.cs.cream.*;

public class CryptArith {
    public static void main(String[] args) {
	Network net = new Network();

	IntVariable d = new IntVariable(net, 0, 9);
	IntVariable e = new IntVariable(net, 0, 9);
	IntVariable m = new IntVariable(net, 1, 9);
	IntVariable n = new IntVariable(net, 0, 9);
	IntVariable o = new IntVariable(net, 0, 9);
	IntVariable r = new IntVariable(net, 0, 9);
	IntVariable s = new IntVariable(net, 1, 9);
	IntVariable y = new IntVariable(net, 0, 9);

	// Variables must have distinct values (no variable can have the same value as another
	IntVariable[] vs = new IntVariable[8];
	vs[0] = d;
	vs[1] = e;
	vs[2] = m;
	vs[3] = n;
	vs[4] = o;
	vs[5] = r;
	vs[6] = s;
	vs[7] = y;

	new NotEquals(net, vs);

	// SEND
	IntVariable s2 = s.multiply(1000);
	//IntArith temp = new IntArith(net, IntArith.MULTIPLY, s2, s, 1000);
	IntVariable e2 = e.multiply(100);
	IntVariable n2 = n.multiply(10);
	IntVariable send = s2.add(e2).add(n2).add(d);

	// MORE
	IntVariable m2 = m.multiply(1000);
	IntVariable o2 = o.multiply(100);
	IntVariable r2 = r.multiply(10);
	IntVariable more = m2.add(o2).add(r2).add(e);

	// MONEY
	IntVariable m3 = m.multiply(10000);
	IntVariable o3 = o.multiply(1000);
	IntVariable n3 = n.multiply(100);
	IntVariable e3 = e.multiply(10);
	IntVariable money = m3.add(o3).add(n3).add(e3).add(y);

	// SEND + MORE = MONEY
	money.equals(send.add(more));

	Solver solver = new DefaultSolver(net);

	Solution solution = solver.findFirst();

	if (solution != null) {
	    System.out.println(solution.getIntValue(s));
	    System.out.println(solution.getIntValue(e));
	    System.out.println(solution.getIntValue(n));
	    System.out.println(solution.getIntValue(d));
	    System.out.println(solution.getIntValue(m));
	    System.out.println(solution.getIntValue(o));
	    System.out.println(solution.getIntValue(r));
	    System.out.println(solution.getIntValue(y));
	} else {
	    System.out.println("No solution found");
	}
   }
}
