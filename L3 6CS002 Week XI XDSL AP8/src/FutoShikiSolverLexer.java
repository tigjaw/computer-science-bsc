// $ANTLR 3.2 Sep 23, 2009 12:02:23 F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g 2010-12-17 16:13:10

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class FutoShikiSolverLexer extends Lexer {
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

    public FutoShikiSolverLexer() {;} 
    public FutoShikiSolverLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public FutoShikiSolverLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g"; }

    // $ANTLR start "T__6"
    public final void mT__6() throws RecognitionException {
        try {
            int _type = T__6;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:3:6: ( 'rules {' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:3:8: 'rules {'
            {
            match("rules {"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__6"

    // $ANTLR start "T__7"
    public final void mT__7() throws RecognitionException {
        try {
            int _type = T__7;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:4:6: ( '}' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:4:8: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__7"

    // $ANTLR start "T__8"
    public final void mT__8() throws RecognitionException {
        try {
            int _type = T__8;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:5:6: ( '.' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:5:8: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__8"

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:6:6: ( 'puzzle with grid of ' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:6:8: 'puzzle with grid of '
            {
            match("puzzle with grid of "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:7:7: ( 'cell ' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:7:9: 'cell '
            {
            match("cell "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:8:7: ( ',' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:8:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:9:7: ( ' = ' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:9:9: ' = '
            {
            match(" = "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:10:7: ( ' is greater than cell ' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:10:9: ' is greater than cell '
            {
            match(" is greater than cell "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:11:7: ( ' is less than cell ' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:11:9: ' is less than cell '
            {
            match(" is less than cell "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:12:7: ( 'solve' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:12:9: 'solve'
            {
            match("solve"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:132:5: ( ( '0' .. '9' )+ )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:132:7: ( '0' .. '9' )+
            {
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:132:7: ( '0' .. '9' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:132:7: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:133:9: ( ( '\\r' )? '\\n' )
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:133:11: ( '\\r' )? '\\n'
            {
            // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:133:11: ( '\\r' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='\r') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:133:11: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEWLINE"

    public void mTokens() throws RecognitionException {
        // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:8: ( T__6 | T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | INT | NEWLINE )
        int alt3=12;
        alt3 = dfa3.predict(input);
        switch (alt3) {
            case 1 :
                // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:10: T__6
                {
                mT__6(); 

                }
                break;
            case 2 :
                // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:15: T__7
                {
                mT__7(); 

                }
                break;
            case 3 :
                // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:20: T__8
                {
                mT__8(); 

                }
                break;
            case 4 :
                // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:25: T__9
                {
                mT__9(); 

                }
                break;
            case 5 :
                // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:30: T__10
                {
                mT__10(); 

                }
                break;
            case 6 :
                // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:36: T__11
                {
                mT__11(); 

                }
                break;
            case 7 :
                // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:42: T__12
                {
                mT__12(); 

                }
                break;
            case 8 :
                // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:48: T__13
                {
                mT__13(); 

                }
                break;
            case 9 :
                // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:54: T__14
                {
                mT__14(); 

                }
                break;
            case 10 :
                // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:60: T__15
                {
                mT__15(); 

                }
                break;
            case 11 :
                // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:66: INT
                {
                mINT(); 

                }
                break;
            case 12 :
                // F:\\Users\\Woody\\Documents\\Work\\Uni\\Uni Work\\Adv Software Eng\\Week11_External DSL\\FutoshikiXDSL\\FutoShikiSolver.g:1:70: NEWLINE
                {
                mNEWLINE(); 

                }
                break;

        }

    }


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\21\uffff";
    static final String DFA3_eofS =
        "\21\uffff";
    static final String DFA3_minS =
        "\1\12\6\uffff\1\75\4\uffff\1\163\1\40\1\147\2\uffff";
    static final String DFA3_maxS =
        "\1\175\6\uffff\1\151\4\uffff\1\163\1\40\1\154\2\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\12\1\13\1\14\1\7\3\uffff"+
        "\1\10\1\11";
    static final String DFA3_specialS =
        "\21\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\12\2\uffff\1\12\22\uffff\1\7\13\uffff\1\6\1\uffff\1\3\1"+
            "\uffff\12\11\51\uffff\1\5\14\uffff\1\4\1\uffff\1\1\1\10\11\uffff"+
            "\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\13\53\uffff\1\14",
            "",
            "",
            "",
            "",
            "\1\15",
            "\1\16",
            "\1\17\4\uffff\1\20",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__6 | T__7 | T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | INT | NEWLINE );";
        }
    }
 

}