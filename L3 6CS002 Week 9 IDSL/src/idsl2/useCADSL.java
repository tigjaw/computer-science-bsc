package idsl2;

public class useCADSL {
    public static void main(String[] args) {
	
	New.CryptArith()
	    .with.variables("s","e","n","d","m","o","r","y","s2","e2","n2","m2","o2","r2","m3","o3","n3","e3","send","more","money")
	    .distinct("s", "e", "n", "d", "m", "o", "r", "y")
	    .nonzero("s")
	    .nonzero("m")
	    // SEND
	    .variable("s").auxiliary(1000, "s2")
	    .variable("e").auxiliary(100, "e2")
	    .variable("n").auxiliary(10, "n2")
	    .sum("send", "s2", "e2", "n2", "d")
	    // MORE
	    .variable("m").auxiliary(1000, "m2")
	    .variable("o").auxiliary(100, "o2")
	    .variable("r").auxiliary(10, "r2")
	    .sum("more", "m2", "o2", "r2", "e")
	    // MONEY
	    .variable("m").auxiliary(10000, "m3")
	    .variable("o").auxiliary(1000, "o3")
	    .variable("n").auxiliary(100, "n3")
	    .variable("e").auxiliary(10, "e3")
	    .sum("money", "m3", "o3", "n3", "e3", "y")
	    // SEND + MORE = MONEY
	    .topofsum("send")
	    .bottomofsum("more")
	    .result("money")
	    // Solve and print out solution
	    .solve();

    }
}
