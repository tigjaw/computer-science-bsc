import java.io.*;
import org.antlr.runtime.*;
import org.antlr.runtime.debug.DebugEventSocketProxy;

public class FutoShikiXDSL {
	public static void main(String args[]) throws Exception {
		FutoShikiSolverLexer lex = new FutoShikiSolverLexer(
				new ANTLRFileStream("UsingPuzzleXDSL.txt", "UTF8"));
		CommonTokenStream tokens = new CommonTokenStream(lex);
		FutoShikiSolverParser g = new FutoShikiSolverParser(tokens);
		try {
			g.prog();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
	}
}