// Generated from C:/Users/Zamora/IdeaProjects/Proyecto1Compiladores/src\LexerMiniJava.g4 by ANTLR 4.5.3
package generated;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LexerMiniJava extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IGUAL=1, PARIZQ=2, PARDER=3, CORIZQ=4, CORDER=5, LLAVEIZQ=6, LLAVEDER=7, 
		PUNTO=8, COMA=9, AND=10, MENORQUE=11, MAYORQUE=12, MAS=13, MENOS=14, MULT=15, 
		NOT=16, PUNTOYCOMA=17, CLASS=18, EXTENDS=19, PUBLIC=20, STATIC=21, VOID=22, 
		MAIN=23, IF=24, ELSE=25, PRINT=26, READ=27, LENGTH=28, TRUE=29, FALSE=30, 
		THIS=31, NEW=32, WHILE=33, BREAK=34, FINAL=35, RETURN=36, INT=37, BOOLEAN=38, 
		CHAR=39, INTEGERLITERAL=40, CHARLITERAL=41, STRINGLITERAL=42, ID=43, LINE_COMMENT=44, 
		SPECIAL_COMMENT=45, WS=46;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"IGUAL", "PARIZQ", "PARDER", "CORIZQ", "CORDER", "LLAVEIZQ", "LLAVEDER", 
		"PUNTO", "COMA", "AND", "MENORQUE", "MAYORQUE", "MAS", "MENOS", "MULT", 
		"NOT", "PUNTOYCOMA", "CLASS", "EXTENDS", "PUBLIC", "STATIC", "VOID", "MAIN", 
		"IF", "ELSE", "PRINT", "READ", "LENGTH", "TRUE", "FALSE", "THIS", "NEW", 
		"WHILE", "BREAK", "FINAL", "RETURN", "INT", "BOOLEAN", "CHAR", "INTEGERLITERAL", 
		"CHARLITERAL", "STRINGLITERAL", "SINGLECHAR", "STRINGCHARS", "STRINGCHAR", 
		"ESCAPESEQ", "ID", "JAVALETTER", "JAVALETTERORDIGIT", "LINE_COMMENT", 
		"SPECIAL_COMMENT", "DELIMITED_COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'('", "')'", "'['", "']'", "'{'", "'}'", "'.'", "','", "'&&'", 
		"'<'", "'>'", "'+'", "'-'", "'*'", "'!'", "';'", "'class'", "'extends'", 
		"'public'", "'static'", "'void'", "'main'", "'if'", "'else'", "'Print'", 
		"'Read'", "'length'", "'true'", "'false'", "'this'", "'new'", "'while'", 
		"'break'", "'const'", "'return'", "'int'", "'boolean'", "'char'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "IGUAL", "PARIZQ", "PARDER", "CORIZQ", "CORDER", "LLAVEIZQ", "LLAVEDER", 
		"PUNTO", "COMA", "AND", "MENORQUE", "MAYORQUE", "MAS", "MENOS", "MULT", 
		"NOT", "PUNTOYCOMA", "CLASS", "EXTENDS", "PUBLIC", "STATIC", "VOID", "MAIN", 
		"IF", "ELSE", "PRINT", "READ", "LENGTH", "TRUE", "FALSE", "THIS", "NEW", 
		"WHILE", "BREAK", "FINAL", "RETURN", "INT", "BOOLEAN", "CHAR", "INTEGERLITERAL", 
		"CHARLITERAL", "STRINGLITERAL", "ID", "LINE_COMMENT", "SPECIAL_COMMENT", 
		"WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public LexerMiniJava(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LexerMiniJava.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 47:
			return JAVALETTER_sempred((RuleContext)_localctx, predIndex);
		case 48:
			return JAVALETTERORDIGIT_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean JAVALETTER_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return Character.isJavaIdentifierStart(_input.LA(-1));
		case 1:
			return Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)));
		}
		return true;
	}
	private boolean JAVALETTERORDIGIT_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return Character.isJavaIdentifierPart(_input.LA(-1));
		case 3:
			return Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)));
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\60\u0180\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3"+
		" \3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3"+
		"$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'"+
		"\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\7)\u0112\n)\f)\16)\u0115\13)\5)\u0117"+
		"\n)\3*\3*\3*\3*\3*\3*\3*\3*\5*\u0121\n*\3+\3+\5+\u0125\n+\3+\3+\3,\3,"+
		"\3-\6-\u012c\n-\r-\16-\u012d\3.\3.\5.\u0132\n.\3/\3/\3/\3\60\3\60\7\60"+
		"\u0139\n\60\f\60\16\60\u013c\13\60\3\61\3\61\3\61\3\61\3\61\3\61\5\61"+
		"\u0144\n\61\3\62\3\62\3\62\3\62\3\62\3\62\5\62\u014c\n\62\3\63\3\63\3"+
		"\63\3\63\7\63\u0152\n\63\f\63\16\63\u0155\13\63\3\63\5\63\u0158\n\63\3"+
		"\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\7\64\u0164\n\64\f\64"+
		"\16\64\u0167\13\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\7\65\u0172"+
		"\n\65\f\65\16\65\u0175\13\65\3\65\3\65\3\65\3\66\6\66\u017b\n\66\r\66"+
		"\16\66\u017c\3\66\3\66\5\u0153\u0165\u0173\2\67\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O"+
		")Q*S+U,W\2Y\2[\2]\2_-a\2c\2e.g/i\2k\60\3\2\13\4\2))^^\4\2$$^^\n\2$$))"+
		"^^ddhhppttvv\6\2&&C\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2"+
		"\udc02\ue001\7\2&&\62;C\\aac|\5\2\13\f\17\17\"\"\u018a\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2_\3\2"+
		"\2\2\2e\3\2\2\2\2g\3\2\2\2\2k\3\2\2\2\3m\3\2\2\2\5o\3\2\2\2\7q\3\2\2\2"+
		"\ts\3\2\2\2\13u\3\2\2\2\rw\3\2\2\2\17y\3\2\2\2\21{\3\2\2\2\23}\3\2\2\2"+
		"\25\177\3\2\2\2\27\u0082\3\2\2\2\31\u0084\3\2\2\2\33\u0086\3\2\2\2\35"+
		"\u0088\3\2\2\2\37\u008a\3\2\2\2!\u008c\3\2\2\2#\u008e\3\2\2\2%\u0090\3"+
		"\2\2\2\'\u0096\3\2\2\2)\u009e\3\2\2\2+\u00a5\3\2\2\2-\u00ac\3\2\2\2/\u00b1"+
		"\3\2\2\2\61\u00b6\3\2\2\2\63\u00b9\3\2\2\2\65\u00be\3\2\2\2\67\u00c4\3"+
		"\2\2\29\u00c9\3\2\2\2;\u00d0\3\2\2\2=\u00d5\3\2\2\2?\u00db\3\2\2\2A\u00e0"+
		"\3\2\2\2C\u00e4\3\2\2\2E\u00ea\3\2\2\2G\u00f0\3\2\2\2I\u00f6\3\2\2\2K"+
		"\u00fd\3\2\2\2M\u0101\3\2\2\2O\u0109\3\2\2\2Q\u0116\3\2\2\2S\u0120\3\2"+
		"\2\2U\u0122\3\2\2\2W\u0128\3\2\2\2Y\u012b\3\2\2\2[\u0131\3\2\2\2]\u0133"+
		"\3\2\2\2_\u0136\3\2\2\2a\u0143\3\2\2\2c\u014b\3\2\2\2e\u014d\3\2\2\2g"+
		"\u015d\3\2\2\2i\u016d\3\2\2\2k\u017a\3\2\2\2mn\7?\2\2n\4\3\2\2\2op\7*"+
		"\2\2p\6\3\2\2\2qr\7+\2\2r\b\3\2\2\2st\7]\2\2t\n\3\2\2\2uv\7_\2\2v\f\3"+
		"\2\2\2wx\7}\2\2x\16\3\2\2\2yz\7\177\2\2z\20\3\2\2\2{|\7\60\2\2|\22\3\2"+
		"\2\2}~\7.\2\2~\24\3\2\2\2\177\u0080\7(\2\2\u0080\u0081\7(\2\2\u0081\26"+
		"\3\2\2\2\u0082\u0083\7>\2\2\u0083\30\3\2\2\2\u0084\u0085\7@\2\2\u0085"+
		"\32\3\2\2\2\u0086\u0087\7-\2\2\u0087\34\3\2\2\2\u0088\u0089\7/\2\2\u0089"+
		"\36\3\2\2\2\u008a\u008b\7,\2\2\u008b \3\2\2\2\u008c\u008d\7#\2\2\u008d"+
		"\"\3\2\2\2\u008e\u008f\7=\2\2\u008f$\3\2\2\2\u0090\u0091\7e\2\2\u0091"+
		"\u0092\7n\2\2\u0092\u0093\7c\2\2\u0093\u0094\7u\2\2\u0094\u0095\7u\2\2"+
		"\u0095&\3\2\2\2\u0096\u0097\7g\2\2\u0097\u0098\7z\2\2\u0098\u0099\7v\2"+
		"\2\u0099\u009a\7g\2\2\u009a\u009b\7p\2\2\u009b\u009c\7f\2\2\u009c\u009d"+
		"\7u\2\2\u009d(\3\2\2\2\u009e\u009f\7r\2\2\u009f\u00a0\7w\2\2\u00a0\u00a1"+
		"\7d\2\2\u00a1\u00a2\7n\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4\7e\2\2\u00a4"+
		"*\3\2\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7\7v\2\2\u00a7\u00a8\7c\2\2\u00a8"+
		"\u00a9\7v\2\2\u00a9\u00aa\7k\2\2\u00aa\u00ab\7e\2\2\u00ab,\3\2\2\2\u00ac"+
		"\u00ad\7x\2\2\u00ad\u00ae\7q\2\2\u00ae\u00af\7k\2\2\u00af\u00b0\7f\2\2"+
		"\u00b0.\3\2\2\2\u00b1\u00b2\7o\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7k\2"+
		"\2\u00b4\u00b5\7p\2\2\u00b5\60\3\2\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8"+
		"\7h\2\2\u00b8\62\3\2\2\2\u00b9\u00ba\7g\2\2\u00ba\u00bb\7n\2\2\u00bb\u00bc"+
		"\7u\2\2\u00bc\u00bd\7g\2\2\u00bd\64\3\2\2\2\u00be\u00bf\7R\2\2\u00bf\u00c0"+
		"\7t\2\2\u00c0\u00c1\7k\2\2\u00c1\u00c2\7p\2\2\u00c2\u00c3\7v\2\2\u00c3"+
		"\66\3\2\2\2\u00c4\u00c5\7T\2\2\u00c5\u00c6\7g\2\2\u00c6\u00c7\7c\2\2\u00c7"+
		"\u00c8\7f\2\2\u00c88\3\2\2\2\u00c9\u00ca\7n\2\2\u00ca\u00cb\7g\2\2\u00cb"+
		"\u00cc\7p\2\2\u00cc\u00cd\7i\2\2\u00cd\u00ce\7v\2\2\u00ce\u00cf\7j\2\2"+
		"\u00cf:\3\2\2\2\u00d0\u00d1\7v\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7w\2"+
		"\2\u00d3\u00d4\7g\2\2\u00d4<\3\2\2\2\u00d5\u00d6\7h\2\2\u00d6\u00d7\7"+
		"c\2\2\u00d7\u00d8\7n\2\2\u00d8\u00d9\7u\2\2\u00d9\u00da\7g\2\2\u00da>"+
		"\3\2\2\2\u00db\u00dc\7v\2\2\u00dc\u00dd\7j\2\2\u00dd\u00de\7k\2\2\u00de"+
		"\u00df\7u\2\2\u00df@\3\2\2\2\u00e0\u00e1\7p\2\2\u00e1\u00e2\7g\2\2\u00e2"+
		"\u00e3\7y\2\2\u00e3B\3\2\2\2\u00e4\u00e5\7y\2\2\u00e5\u00e6\7j\2\2\u00e6"+
		"\u00e7\7k\2\2\u00e7\u00e8\7n\2\2\u00e8\u00e9\7g\2\2\u00e9D\3\2\2\2\u00ea"+
		"\u00eb\7d\2\2\u00eb\u00ec\7t\2\2\u00ec\u00ed\7g\2\2\u00ed\u00ee\7c\2\2"+
		"\u00ee\u00ef\7m\2\2\u00efF\3\2\2\2\u00f0\u00f1\7e\2\2\u00f1\u00f2\7q\2"+
		"\2\u00f2\u00f3\7p\2\2\u00f3\u00f4\7u\2\2\u00f4\u00f5\7v\2\2\u00f5H\3\2"+
		"\2\2\u00f6\u00f7\7t\2\2\u00f7\u00f8\7g\2\2\u00f8\u00f9\7v\2\2\u00f9\u00fa"+
		"\7w\2\2\u00fa\u00fb\7t\2\2\u00fb\u00fc\7p\2\2\u00fcJ\3\2\2\2\u00fd\u00fe"+
		"\7k\2\2\u00fe\u00ff\7p\2\2\u00ff\u0100\7v\2\2\u0100L\3\2\2\2\u0101\u0102"+
		"\7d\2\2\u0102\u0103\7q\2\2\u0103\u0104\7q\2\2\u0104\u0105\7n\2\2\u0105"+
		"\u0106\7g\2\2\u0106\u0107\7c\2\2\u0107\u0108\7p\2\2\u0108N\3\2\2\2\u0109"+
		"\u010a\7e\2\2\u010a\u010b\7j\2\2\u010b\u010c\7c\2\2\u010c\u010d\7t\2\2"+
		"\u010dP\3\2\2\2\u010e\u0117\7\62\2\2\u010f\u0113\4\63;\2\u0110\u0112\4"+
		"\62;\2\u0111\u0110\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0113"+
		"\u0114\3\2\2\2\u0114\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u010e\3\2"+
		"\2\2\u0116\u010f\3\2\2\2\u0117R\3\2\2\2\u0118\u0119\7)\2\2\u0119\u011a"+
		"\5W,\2\u011a\u011b\7)\2\2\u011b\u0121\3\2\2\2\u011c\u011d\7)\2\2\u011d"+
		"\u011e\5]/\2\u011e\u011f\7)\2\2\u011f\u0121\3\2\2\2\u0120\u0118\3\2\2"+
		"\2\u0120\u011c\3\2\2\2\u0121T\3\2\2\2\u0122\u0124\7$\2\2\u0123\u0125\5"+
		"Y-\2\u0124\u0123\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\3\2\2\2\u0126"+
		"\u0127\7$\2\2\u0127V\3\2\2\2\u0128\u0129\n\2\2\2\u0129X\3\2\2\2\u012a"+
		"\u012c\5[.\2\u012b\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012b\3\2\2"+
		"\2\u012d\u012e\3\2\2\2\u012eZ\3\2\2\2\u012f\u0132\n\3\2\2\u0130\u0132"+
		"\5]/\2\u0131\u012f\3\2\2\2\u0131\u0130\3\2\2\2\u0132\\\3\2\2\2\u0133\u0134"+
		"\7^\2\2\u0134\u0135\t\4\2\2\u0135^\3\2\2\2\u0136\u013a\5a\61\2\u0137\u0139"+
		"\5c\62\2\u0138\u0137\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2\2\2\u013a"+
		"\u013b\3\2\2\2\u013b`\3\2\2\2\u013c\u013a\3\2\2\2\u013d\u0144\t\5\2\2"+
		"\u013e\u013f\n\6\2\2\u013f\u0144\6\61\2\2\u0140\u0141\t\7\2\2\u0141\u0142"+
		"\t\b\2\2\u0142\u0144\6\61\3\2\u0143\u013d\3\2\2\2\u0143\u013e\3\2\2\2"+
		"\u0143\u0140\3\2\2\2\u0144b\3\2\2\2\u0145\u014c\t\t\2\2\u0146\u0147\n"+
		"\6\2\2\u0147\u014c\6\62\4\2\u0148\u0149\t\7\2\2\u0149\u014a\t\b\2\2\u014a"+
		"\u014c\6\62\5\2\u014b\u0145\3\2\2\2\u014b\u0146\3\2\2\2\u014b\u0148\3"+
		"\2\2\2\u014cd\3\2\2\2\u014d\u014e\7\61\2\2\u014e\u014f\7\61\2\2\u014f"+
		"\u0153\3\2\2\2\u0150\u0152\13\2\2\2\u0151\u0150\3\2\2\2\u0152\u0155\3"+
		"\2\2\2\u0153\u0154\3\2\2\2\u0153\u0151\3\2\2\2\u0154\u0157\3\2\2\2\u0155"+
		"\u0153\3\2\2\2\u0156\u0158\7\17\2\2\u0157\u0156\3\2\2\2\u0157\u0158\3"+
		"\2\2\2\u0158\u0159\3\2\2\2\u0159\u015a\7\f\2\2\u015a\u015b\3\2\2\2\u015b"+
		"\u015c\b\63\2\2\u015cf\3\2\2\2\u015d\u015e\7\61\2\2\u015e\u015f\7,\2\2"+
		"\u015f\u0165\3\2\2\2\u0160\u0164\5g\64\2\u0161\u0164\5i\65\2\u0162\u0164"+
		"\13\2\2\2\u0163\u0160\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0162\3\2\2\2"+
		"\u0164\u0167\3\2\2\2\u0165\u0166\3\2\2\2\u0165\u0163\3\2\2\2\u0166\u0168"+
		"\3\2\2\2\u0167\u0165\3\2\2\2\u0168\u0169\7,\2\2\u0169\u016a\7\61\2\2\u016a"+
		"\u016b\3\2\2\2\u016b\u016c\b\64\2\2\u016ch\3\2\2\2\u016d\u016e\7\61\2"+
		"\2\u016e\u016f\7,\2\2\u016f\u0173\3\2\2\2\u0170\u0172\13\2\2\2\u0171\u0170"+
		"\3\2\2\2\u0172\u0175\3\2\2\2\u0173\u0174\3\2\2\2\u0173\u0171\3\2\2\2\u0174"+
		"\u0176\3\2\2\2\u0175\u0173\3\2\2\2\u0176\u0177\7,\2\2\u0177\u0178\7\61"+
		"\2\2\u0178j\3\2\2\2\u0179\u017b\t\n\2\2\u017a\u0179\3\2\2\2\u017b\u017c"+
		"\3\2\2\2\u017c\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017e\3\2\2\2\u017e"+
		"\u017f\b\66\2\2\u017fl\3\2\2\2\22\2\u0113\u0116\u0120\u0124\u012d\u0131"+
		"\u013a\u0143\u014b\u0153\u0157\u0163\u0165\u0173\u017c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}