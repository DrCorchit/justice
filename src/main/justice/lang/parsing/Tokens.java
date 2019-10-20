package justice.lang.parsing;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.List;

public class Tokens {

	//boolean
	public static final Token AND = new OpToken(Operator.AND);
	public static final Token OR = new OpToken(Operator.OR);
	public static final Token NOT = new OpToken(Operator.NOT);
	public static final Token XOR = new OpToken(Operator.XOR);

	//comparison
	public static final Token GT = new OpToken(Operator.GT);
	public static final Token GTE = new OpToken(Operator.GTE);
	public static final Token LT = new OpToken(Operator.LT);
	public static final Token LTE = new OpToken(Operator.LTE);
	public static final Token EQ = new OpToken(Operator.EQ);
	public static final Token NEQ = new OpToken(Operator.NEQ);

	//arithmetic
	public static final Token PLUS = new OpToken(Operator.PLUS);
	public static final Token MINUS = new OpToken(Operator.MINUS);
	public static final Token MULT = new OpToken(Operator.MULT);
	public static final Token DIV = new OpToken(Operator.DIV);
	public static final Token POW = new OpToken(Operator.POW);
	public static final Token ROOT = new OpToken(Operator.ROOT);
	public static final Token MODULUS = new OpToken(Operator.MODULUS);

	//bitwise & | ^

	//assignment
	//++ -- = += -= *= /= **= //=

	//constants
	public static final Token PI = new StringToken("pi");
	public static final Token PHI = new StringToken("phi");
	public static final Token E = new StringToken("e");
	public static final Token TRUE = new StringToken("true");
	public static final Token FALSE = new StringToken("false");
	public static final Token INTERFACE = new StringToken("interface");
	public static final Token IMPLEMENTATION = new StringToken("implementation");
	public static final Token EVALUATOR = new StringToken("evaluator");
	public static final Token MANAGER = new StringToken("manager");
	public static final Token ENUM = new StringToken("enum");
	//field routine function api project internal private

	//pairs
	public static final PairToken PAREN = new PairToken('(', ')');
	//public static final Token ANGLE = new PairToken('<', '>');
	public static final PairToken BRACKET = new PairToken('[', ']');
	public static final PairToken BRACE = new PairToken('{', '}');

	public static final RegexToken CONSTANT = new RegexToken("[-+]?\\d*\\.?\\d+");
	public static final RegexToken IDENTIFIER = new RegexToken("\\.?\\w+(\\.\\w+)*");

	public static final ImmutableSet<Token> TOKENS;

	static {
		ImmutableSet.Builder<Token> builder = ImmutableSet.builder();

		builder.add(AND, OR, XOR, EQ, NEQ, NOT, LTE, GTE, LT, GT, POW, ROOT, MULT, DIV, PLUS, MINUS,
				PI, PHI, E, TRUE, FALSE, CONSTANT, IDENTIFIER);

		TOKENS = builder.build();
	}

	public static abstract class Token {
		abstract String getRegex();

		@Override
		public String toString() {
			return getClass().getSimpleName()+"[" + getRegex() + "]";
		}
	}

	//used to store partially tokenized text
	public static class TodoToken extends Token {
		private final String value;
		private final Context context;

		public TodoToken(String value, Context context) {
			this.value = value;
			this.context = context;
		}

		@Override
		String getRegex() {
			return null;
		}
	}

	//a quote token referencing a string literal
	//TODO add escaping logic
	public static class QuoteToken extends Token {
		private final String value;

		public QuoteToken(String value) {
			this.value = value;
		}

		@Override
		String getRegex() {
			return "\"";
		}
	}

	//a token for an operator
	private static class OpToken extends Token {
		private final Operator op;

		private OpToken(Operator op) {
			this.op = op;
		}


		@Override
		String getRegex() {
			return op.getShortName();
		}
	}

	private static class StringToken extends Token {
		private final String value;

		private StringToken(String value) {
			this.value = value;
		}

		@Override
		String getRegex() {
			return value;
		}
	}

	//used for paired tokens ()[]{}
	public static class PairToken extends Token {
		private final char l, r;

		private PairToken(char l, char r) {
			this.l = l;
			this.r = r;
		}

		public RawPairToken of(boolean left) {
			return new RawPairToken(left);
		}

		public static RawPairToken of(char c) {
			switch (c) {
				case '{':
					return BRACE.of(true);
				case '(':
					return PAREN.of(true);
				case '[':
					return BRACKET.of(true);
				case '}':
					return BRACE.of(false);
				case ')':
					return PAREN.of(false);
				case ']':
					return BRACKET.of(false);
				default:
					throw new IllegalArgumentException();
			}
		}

		@Override
		String getRegex() {
			return String.valueOf(l);
		}

		public class RawPairToken extends Token {
			private final boolean left;
			private final char chr;

			public RawPairToken(boolean left) {
				this.left = left;
				chr = left ? l : r;
			}

			public PairToken parent() {
				return PairToken.this;
			}

			@Override
			String getRegex() {
				return String.valueOf(chr);
			}
		}

		public SubTokens create(List<Token> input) {
			return new SubTokens(input);
		}

		public class SubTokens extends Token {
			private final ImmutableList<Token> subtokens;

			private SubTokens(List<Token> tokens) {
				subtokens = ImmutableList.copyOf(tokens);
			}

			public ImmutableList<Token> getTokens() {
				return subtokens;
			}

			@Override
			String getRegex() {
				return null;
			}
		}
	}

	//used to match a wide range of token types (identifiers/constants)
	public static class RegexToken extends Token {
		private final String regex;

		private RegexToken(String regex) {
			this.regex = regex;
		}

		public SubToken create(String value) {
			return new SubToken(value);
		}

		@Override
		String getRegex() {
			return regex;
		}

		public class SubToken extends Token {
			private final String value;

			private SubToken(String value) {
				this.value = value;
			}

			public String getValue() {
				return value;
			}

			public RegexToken getType() {
				return RegexToken.this;
			}

			@Override
			String getRegex() {
				return value;
			}
		}
	}

	public static ImmutableList<Token> tokenize(String content) {
		ArrayList<Token> output = tokenizeFirstPass(content);
		//output = tokenizePairs(output);

		return ImmutableList.copyOf(output);
	}

	public static ArrayList<Token> tokenizeFirstPass(String input) {
		boolean quoteOpen = false, escapeOpen = false;
		int quoteClosePos = 0, quoteOpenPos = 0;
		ArrayList<Token> output = new ArrayList<>();

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			switch (c) {
				case '(':
				case '[':
				case '{':
				case '}':
				case ')':
				case ']':
					if (!quoteOpen) {
						output.add(new TodoToken(input.substring(quoteClosePos, i), Context.TOP_LEVEL));
						output.add(PairToken.of(c));
						quoteClosePos = i + 1;
					}
					break;
				case '"':
					if (escapeOpen) {
						escapeOpen = false;
					} else {
						if (quoteOpen) {
							output.add(new TodoToken(input.substring(quoteClosePos, quoteOpenPos), Context.TOP_LEVEL));
							output.add(new QuoteToken(input.substring(quoteOpenPos + 1, i)));
							quoteClosePos = i + 1;
						} else {
							quoteOpenPos = i;
						}
						quoteOpen = !quoteOpen;
					}
					break;
				case '\\':
					escapeOpen = !escapeOpen;
					break;
				default:
					if (!quoteOpen) {
						String remain = input.substring(i);

						for (Token token : TOKENS) {
							if (token instanceof RegexToken) {
								String regex = "^" + token.getRegex();
								String temp = remain.replaceFirst(regex, "");
								int dif = remain.length() - temp.length();
								if (dif > 0) {
									output.add(((RegexToken) token).create(remain.substring(0, dif)));
									i += dif-1;
									break;
								}
							} else if (remain.startsWith(token.getRegex())) {
								output.add(token);
								i += token.getRegex().length()-1;
								break;
							}
						}
					}
					escapeOpen = false;
			}
		}

		if (quoteOpen) {
			throw new IllegalStateException("Unclosed quote detected.");
		}
		return output;
	}

	public static ArrayList<Token> tokenizePairs(List<Token> input) {
		//handles (){}[]
		ArrayList<Token> output = new ArrayList<>();

		int j = 0;
		for (int i = 0; i < input.size(); i++) {
			Token token = input.get(i);
			if (token instanceof PairToken.RawPairToken) {
				PairToken.RawPairToken raw = (PairToken.RawPairToken) token;
				if (raw.left) {
					char right = raw.parent().r;
					//find matching right token
					j = i + 1;
					int level = 0;
					while (true) {
						Token token2 = input.get(j);
						if (token2 instanceof PairToken.RawPairToken) {
							PairToken.RawPairToken raw2 = (PairToken.RawPairToken) token2;

							if (raw2.chr == right && level == 0) {

								output.add(raw.parent().create(tokenizePairs(input.subList(i + 1, j))));
								i = j;
								break;
							} else if (raw2.left) level++;
							else {
								level--;
								if (level < 0) throw new IllegalArgumentException("Mismatched parens detected");
							}
						}

						if (++j > input.size()) throw new IllegalArgumentException("Mismatched parens detected");
					}
				}
			} else {
				//add the token unchanged
				output.add(token);
			}

		}
		return output;
	}

	/*
	public static ArrayList<Token> tokenizeSecondPass(String remain) {
		ArrayList<Token> tokens = new ArrayList<>();

		//Tokenize, but do not build AST. Parentheses do get a recursive structure, though
		boolean match;
		ImmutableList.Builder<Token> output = ImmutableList.builder();

		while (!remain.isEmpty()) {
			remain = remain.trim();
			match = false;
			for (Token tok : TOKENS) {
				if (tok instanceof OpToken) {
					OpToken op = (OpToken) tok;


				} else if (tok instanceof StringToken) {
					StringToken str = (StringToken) tok;


				} else if (tok instanceof PairToken) {
					PairToken pair = (PairToken) tok;


				} else if (tok instanceof RegexToken) {
					RegexToken regex = (RegexToken) tok;
					if (remain.matches(regex.regex)) {
						output.add(regex.create(remain));
					}

				} else {
					throw new IllegalStateException("Unknown token type: " + tok.getClass().getName());
				}


				switch (tok) {
					case IDENTIFIER:
					case CONSTANT:
						RegexToken

						String regex = "^" + op.regex();
						String temp = remain;
						remain = remain.replaceFirst(regex, "");
						int dif = temp.length() - remain.length();
						if (dif > 0) {
							output.add(new Token(op, temp.substring(0, dif)));
							match = true;
						}
						break;
					case PAREN:
						if (remain.startsWith("(")) {
							int pos = rightParenPos(remain);
							if (pos == -1) throw new IllegalArgumentException("No matching right paren in expression");
							output.add(new Token(remain.substring(1, pos)));
							remain = remain.substring(pos + 1);
							match = true;
						}
						break;
					default:
						if (remain.startsWith(op.regex())) {
							output.add(getToken(op));
							remain = remain.substring(op.regex().length());
							match = true;
						}
				}
				if (match) break;
			}
			if (!match) {
				throw new IllegalArgumentException("Could not tokenize string: <" + remain + ">");
			}
		}

		return output.build();


		return tokens;
	}
	*/

}
