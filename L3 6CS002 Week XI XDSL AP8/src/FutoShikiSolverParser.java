// $ANTLR 3.2 Sep 23, 2009 12:02:23 F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g 2010-12-17 16:13:10

	import jp.ac.kobe_u.cs.cream.*;
	

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class FutoShikiSolverParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NEWLINE", "INT", "'rules {'", "'}'", "'.'", "'puzzle with grid of '", "'cell '", "','", "' = '", "' is greater than cell '", "' is less than cell '", "'solve'"
    };
    public static final int T__15=15;
    public static final int NEWLINE=4;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__10=10;
    public static final int INT=5;
    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int T__8=8;
    public static final int T__7=7;
    public static final int T__6=6;

    // delegates
    // delegators


        public FutoShikiSolverParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public FutoShikiSolverParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return FutoShikiSolverParser.tokenNames; }
    public String getGrammarFileName() { return "F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g"; }


    	IntVariable[][] puzzle;
    	int puzzle_width;
    	Network net = new Network();
    	


    // $ANTLR start "prog"
    // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:13:1: prog : puzzle NEWLINE ( cell NEWLINE )+ 'rules {' NEWLINE ( ( greaterThan | lessThan ) NEWLINE )+ '}' NEWLINE solve '.' ;
    public final void prog() throws RecognitionException {
        try {
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:13:7: ( puzzle NEWLINE ( cell NEWLINE )+ 'rules {' NEWLINE ( ( greaterThan | lessThan ) NEWLINE )+ '}' NEWLINE solve '.' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:13:9: puzzle NEWLINE ( cell NEWLINE )+ 'rules {' NEWLINE ( ( greaterThan | lessThan ) NEWLINE )+ '}' NEWLINE solve '.'
            {
            pushFollow(FOLLOW_puzzle_in_prog24);
            puzzle();

            state._fsp--;

            match(input,NEWLINE,FOLLOW_NEWLINE_in_prog26); 
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:14:4: ( cell NEWLINE )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==10) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:14:5: cell NEWLINE
            	    {
            	    pushFollow(FOLLOW_cell_in_prog32);
            	    cell();

            	    state._fsp--;

            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_prog34); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            match(input,6,FOLLOW_6_in_prog41); 
            match(input,NEWLINE,FOLLOW_NEWLINE_in_prog43); 
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:15:22: ( ( greaterThan | lessThan ) NEWLINE )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==10) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:15:23: ( greaterThan | lessThan ) NEWLINE
            	    {
            	    // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:15:23: ( greaterThan | lessThan )
            	    int alt2=2;
            	    int LA2_0 = input.LA(1);

            	    if ( (LA2_0==10) ) {
            	        int LA2_1 = input.LA(2);

            	        if ( (LA2_1==INT) ) {
            	            int LA2_2 = input.LA(3);

            	            if ( (LA2_2==11) ) {
            	                int LA2_3 = input.LA(4);

            	                if ( (LA2_3==INT) ) {
            	                    int LA2_4 = input.LA(5);

            	                    if ( (LA2_4==13) ) {
            	                        alt2=1;
            	                    }
            	                    else if ( (LA2_4==14) ) {
            	                        alt2=2;
            	                    }
            	                    else {
            	                        NoViableAltException nvae =
            	                            new NoViableAltException("", 2, 4, input);

            	                        throw nvae;
            	                    }
            	                }
            	                else {
            	                    NoViableAltException nvae =
            	                        new NoViableAltException("", 2, 3, input);

            	                    throw nvae;
            	                }
            	            }
            	            else {
            	                NoViableAltException nvae =
            	                    new NoViableAltException("", 2, 2, input);

            	                throw nvae;
            	            }
            	        }
            	        else {
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 2, 1, input);

            	            throw nvae;
            	        }
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 2, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt2) {
            	        case 1 :
            	            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:15:24: greaterThan
            	            {
            	            pushFollow(FOLLOW_greaterThan_in_prog47);
            	            greaterThan();

            	            state._fsp--;


            	            }
            	            break;
            	        case 2 :
            	            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:15:38: lessThan
            	            {
            	            pushFollow(FOLLOW_lessThan_in_prog51);
            	            lessThan();

            	            state._fsp--;


            	            }
            	            break;

            	    }

            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_prog54); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            match(input,7,FOLLOW_7_in_prog58); 
            match(input,NEWLINE,FOLLOW_NEWLINE_in_prog60); 
            pushFollow(FOLLOW_solve_in_prog65);
            solve();

            state._fsp--;

            match(input,8,FOLLOW_8_in_prog67); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "prog"


    // $ANTLR start "puzzle"
    // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:18:1: puzzle : 'puzzle with grid of ' INT ;
    public final void puzzle() throws RecognitionException {
        Token INT1=null;

        try {
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:18:9: ( 'puzzle with grid of ' INT )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:18:11: 'puzzle with grid of ' INT
            {
            match(input,9,FOLLOW_9_in_puzzle76); 
            INT1=(Token)match(input,INT,FOLLOW_INT_in_puzzle78); 

            					try {
            						puzzle_width = Integer.parseInt((INT1!=null?INT1.getText():null));
            						puzzle = new IntVariable[Integer.parseInt((INT1!=null?INT1.getText():null))][Integer.parseInt((INT1!=null?INT1.getText():null))];
            					} catch (Exception e) {
            						System.out.println("Puzzle Initiation Failed.");
            					}

            				

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "puzzle"


    // $ANTLR start "cell"
    // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:29:1: cell : 'cell ' xGreater= INT ',' yGreater= INT ' = ' val= INT ;
    public final void cell() throws RecognitionException {
        Token xGreater=null;
        Token yGreater=null;
        Token val=null;

        try {
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:29:7: ( 'cell ' xGreater= INT ',' yGreater= INT ' = ' val= INT )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:29:9: 'cell ' xGreater= INT ',' yGreater= INT ' = ' val= INT
            {
            match(input,10,FOLLOW_10_in_cell93); 
            xGreater=(Token)match(input,INT,FOLLOW_INT_in_cell97); 
            match(input,11,FOLLOW_11_in_cell98); 
            yGreater=(Token)match(input,INT,FOLLOW_INT_in_cell101); 
            match(input,12,FOLLOW_12_in_cell103); 
            val=(Token)match(input,INT,FOLLOW_INT_in_cell107); 

            					try {
            						int x = Integer.parseInt((xGreater!=null?xGreater.getText():null)), y = Integer.parseInt((yGreater!=null?yGreater.getText():null));
            						if (x < puzzle_width && y < puzzle_width) {
            						    puzzle[x][y] = new IntVariable(net, Integer.parseInt((val!=null?val.getText():null)));
            						} else {
            						    System.out.println("Invalid Cell Chosen");
            						}
            					} catch (Exception e) {
            						System.out.println("Error Assigning Value to Puzzle.");
            					}
            				

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "cell"


    // $ANTLR start "greaterThan"
    // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:43:1: greaterThan : 'cell ' xGreater= INT ',' yGreater= INT ' is greater than cell ' xLesser= INT ',' yLesser= INT ;
    public final void greaterThan() throws RecognitionException {
        Token xGreater=null;
        Token yGreater=null;
        Token xLesser=null;
        Token yLesser=null;

        try {
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:43:13: ( 'cell ' xGreater= INT ',' yGreater= INT ' is greater than cell ' xLesser= INT ',' yLesser= INT )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:43:15: 'cell ' xGreater= INT ',' yGreater= INT ' is greater than cell ' xLesser= INT ',' yLesser= INT
            {
            match(input,10,FOLLOW_10_in_greaterThan121); 
            xGreater=(Token)match(input,INT,FOLLOW_INT_in_greaterThan125); 
            match(input,11,FOLLOW_11_in_greaterThan126); 
            yGreater=(Token)match(input,INT,FOLLOW_INT_in_greaterThan129); 
            match(input,13,FOLLOW_13_in_greaterThan131); 
            xLesser=(Token)match(input,INT,FOLLOW_INT_in_greaterThan135); 
            match(input,11,FOLLOW_11_in_greaterThan136); 
            yLesser=(Token)match(input,INT,FOLLOW_INT_in_greaterThan139); 

            					try { // cell 0,1 greater than cell 0,2
            						int x = Integer.parseInt((xGreater!=null?xGreater.getText():null)), x2 = Integer.parseInt((xLesser!=null?xLesser.getText():null));
            						int y = Integer.parseInt((yGreater!=null?yGreater.getText():null)), y2 = Integer.parseInt((yLesser!=null?yLesser.getText():null));
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
            						System.out.println("Error Assigning Relation: " + (xGreater!=null?xGreater.getText():null)+","+(yGreater!=null?yGreater.getText():null) + " is greater than " + (xLesser!=null?xLesser.getText():null)+","+(yLesser!=null?yLesser.getText():null));
            					}
            				

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "greaterThan"


    // $ANTLR start "lessThan"
    // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:68:1: lessThan : 'cell ' xGreater= INT ',' yGreater= INT ' is less than cell ' xLesser= INT ',' yLesser= INT ;
    public final void lessThan() throws RecognitionException {
        Token xGreater=null;
        Token yGreater=null;
        Token xLesser=null;
        Token yLesser=null;

        try {
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:68:10: ( 'cell ' xGreater= INT ',' yGreater= INT ' is less than cell ' xLesser= INT ',' yLesser= INT )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:68:12: 'cell ' xGreater= INT ',' yGreater= INT ' is less than cell ' xLesser= INT ',' yLesser= INT
            {
            match(input,10,FOLLOW_10_in_lessThan153); 
            xGreater=(Token)match(input,INT,FOLLOW_INT_in_lessThan157); 
            match(input,11,FOLLOW_11_in_lessThan158); 
            yGreater=(Token)match(input,INT,FOLLOW_INT_in_lessThan161); 
            match(input,14,FOLLOW_14_in_lessThan163); 
            xLesser=(Token)match(input,INT,FOLLOW_INT_in_lessThan167); 
            match(input,11,FOLLOW_11_in_lessThan168); 
            yLesser=(Token)match(input,INT,FOLLOW_INT_in_lessThan171); 

            					try { // cell 1,1 less than cell 1,2
            						int x = Integer.parseInt((xGreater!=null?xGreater.getText():null)), x2 = Integer.parseInt((xLesser!=null?xLesser.getText():null));
            						int y = Integer.parseInt((yGreater!=null?yGreater.getText():null)), y2 = Integer.parseInt((yLesser!=null?yLesser.getText():null));
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
            						System.out.println("Error Assigning Relation: " + (xGreater!=null?xGreater.getText():null)+","+(yGreater!=null?yGreater.getText():null) + " is greater than " + (xLesser!=null?xLesser.getText():null)+","+(yLesser!=null?yLesser.getText():null));
            					}
            				

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "lessThan"


    // $ANTLR start "solve"
    // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:93:1: solve : 'solve' ;
    public final void solve() throws RecognitionException {
        try {
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:93:8: ( 'solve' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:93:10: 'solve'
            {
            match(input,15,FOLLOW_15_in_solve186); 

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
            				

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "solve"

    // Delegated rules


 

    public static final BitSet FOLLOW_puzzle_in_prog24 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_prog26 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_cell_in_prog32 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_prog34 = new BitSet(new long[]{0x0000000000000440L});
    public static final BitSet FOLLOW_6_in_prog41 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_prog43 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_greaterThan_in_prog47 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_lessThan_in_prog51 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_prog54 = new BitSet(new long[]{0x0000000000000480L});
    public static final BitSet FOLLOW_7_in_prog58 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_prog60 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_solve_in_prog65 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_prog67 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_puzzle76 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_puzzle78 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_cell93 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_cell97 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_cell98 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_cell101 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_cell103 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_cell107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_greaterThan121 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_greaterThan125 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_greaterThan126 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_greaterThan129 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_greaterThan131 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_greaterThan135 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_greaterThan136 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_greaterThan139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_lessThan153 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_lessThan157 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_lessThan158 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_lessThan161 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_lessThan163 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_lessThan167 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_lessThan168 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_INT_in_lessThan171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_solve186 = new BitSet(new long[]{0x0000000000000002L});

}