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

	public static final RegexToken CONSTANT = new RegexToken("Num", "[-+]?\\d+(\\.\\d+)?\\b");
	public static final RegexToken IDENTIFIER = new RegexToken("Var", "\\.?\\w+\\b");

	public static final ImmutableSet<Token> TOKENS;

	static {
		ImmutableSet.Builder<Token> builder = ImmutableSet.builder();

		builder.add(AND, OR, XOR, EQ, NEQ, NOT, LTE, GTE, LT, GT, POW, ROOT, MULT, DIV, PLUS, MINUS,
				PI, PHI, E, TRUE, FALSE, CONSTANT, IDENTIFIER);

		TOKENS = builder.build();
	}

	public static abstract class Token {
		abstract String getPattern();

		abstract boolean isFungible();

		@Override
		public String toString() {
			return getClass().getSimpleName() + "[" + getPattern() + "]";
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
		String getPattern() {
			return "\"";
		}

		@Override
		boolean isFungible() {
			return false;
		}

		@Override
		public String toString() {
			return "\"" + value + "\"";
		}
	}

	//a token for an operator
	private static class OpToken extends Token {
		private final Operator op;

		private OpToken(Operator op) {
			this.op = op;
		}

		@Override
		String getPattern() {
			return op.getShortName();
		}

		@Override
		boolean isFungible() {
			return true;
		}

		@Override
		public String toString() {
			return op.getShortName();
		}
	}

	private static class StringToken extends Token {
		private final String value;

		private StringToken(String value) {
			this.value = value;
		}

		@Override
		String getPattern() {
			return "^" + value + "\\b.*";
		}

		@Override
		boolean isFungible() {
			return true;
		}

		@Override
		public String toString() {
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
		String getPattern() {
			return String.valueOf(l);
		}

		@Override
		boolean isFungible() {
			return true;
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
			String getPattern() {
				return String.valueOf(chr);
			}

			@Override
			boolean isFungible() {
				return true;
			}

			@Override
			public String toString() {
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

			public PairToken parent() {
				return PairToken.this;
			}

			public ImmutableList<Token> getTokens() {
				return subtokens;
			}

			@Override
			String getPattern() {
				return null;
			}

			@Override
			boolean isFungible() {
				return false;
			}

			@Override
			public String toString() {
				return String.format(" %c %s %c ", l, subtokens, r);
			}
		}
	}

	//used to match a wide range of token types (identifiers/constants)
	public static class RegexToken extends Token {
		private final String name;
		private final String regex;

		private RegexToken(String name, String regex) {
			this.name = name;
			this.regex = regex;
		}

		public SubToken create(String value) {
			return new SubToken(value);
		}

		@Override
		String getPattern() {
			return regex;
		}

		@Override
		boolean isFungible() {
			return false;
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
			String getPattern() {
				return value;
			}

			@Override
			boolean isFungible() {
				return false;
			}

			@Override
			public String toString() {
				return String.format(" %s[%s] ", name, value);
			}
		}
	}

	public static ImmutableList<Token> tokenize(String content) {
		ArrayList<Token> output = tokenizeFirstPass(content);
		output = tokenizeSecondPass(output);
		return ImmutableList.copyOf(output);
	}

	public static ArrayList<Token> tokenizeFirstPass(final String input) {
		boolean quoteOpen = false, escapeOpen = false;
		int quoteOpenPos = 0;
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
						output.add(PairToken.of(c));
					}
					break;
				case '"':
					if (escapeOpen) {
						escapeOpen = false;
					} else {
						if (quoteOpen) {
							output.add(new QuoteToken(input.substring(quoteOpenPos + 1, i)));
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
					if (!quoteOpen && !Character.isWhitespace(c)) {
						String remain = input.substring(i);

						boolean matched = false;
						for (Token token : TOKENS) {
							if (token instanceof RegexToken) {
								String regex = token.getPattern();
								String temp = remain.replaceFirst(regex, "");
								int dif = remain.length() - temp.length();
								if (dif > 0) {
									output.add(((RegexToken) token).create(remain.substring(0, dif)));
									i += dif - 1;
									matched = true;
								}
							} else if (token instanceof StringToken) {
								String regex = token.getPattern();
								String temp = remain.replaceFirst(regex, "");
								int dif = remain.length() - temp.length();
								if (dif > 0) {
									output.add(token);
									i += dif - 1;
									matched = true;
								}
							} else if (token instanceof OpToken) {
								if (remain.startsWith(token.getPattern())) {
									output.add(token);
									i += token.getPattern().length() - 1;
									matched = true;
								}
							}
							if (matched) break;
						}
						if (!matched) {
							throw new IllegalArgumentException(String.format("Unable to tokenize character %c at position %d", c, i));
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

	public static ArrayList<Token> tokenizeSecondPass(List<Token> input) {
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

								output.add(raw.parent().create(tokenizeSecondPass(input.subList(i + 1, j))));
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
}
