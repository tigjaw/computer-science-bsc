package idsl1;

public class useCADSL {
    public static void main(String[] args) {
	
	New.CryptArith()
	    .with.variables("s","e","n","d","m","o","r","y","s2","e2","n2","m2","o2","r2","m3","o3","n3","e3","send","more","money")
	    .distinct("s", "e", "n", "d", "m", "o", "r", "y")
	    .nonzero("s")
	    .nonzero("m")
	    // SEND
	    .auxiliary("s", 1000, "s2")
	    .auxiliary("e", 100, "e2")
	    .auxiliary("n", 10, "n2")
	    .sum("send", "s2", "e2", "n2", "d")
	    // MORE
	    .auxiliary("m", 1000, "m2")
	    .auxiliary("o", 100, "o2")
	    .auxiliary("r", 10, "r2")
	    .sum("more", "m2", "o2", "r2", "e")
	    // MONEY
	    .auxiliary("m", 10000, "m3")
	    .auxiliary("o", 1000, "o3")
	    .auxiliary("n", 100, "n3")
	    .auxiliary("e", 10, "e3")
	    .sum("money", "m3", "o3", "n3", "e3", "y")
	    // SEND + MORE = MONEY
	    .topofsum("send")
	    .bottomofsum("more")
	    .result("money")
	    // Solve and print out solution
	    .solve();

    }
}
