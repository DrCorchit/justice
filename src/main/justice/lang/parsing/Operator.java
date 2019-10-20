package justice.lang.parsing;

public enum Operator {

	//boolean
	AND("&&"), OR("||"), NOT("!"), XOR("^^"),

	//COMPARISON
	GT(">"), LT("<"), GTE(">="), LTE("<="), EQ("=="), NEQ("!="),

	//arithmetic
	PLUS("+"), MINUS("-"), MULT("*"), DIV("/"), POW("**"), ROOT("//"), MODULUS("%");

	//missing assignment operators: = += *= -= /= **= //=
	//missing bitwise operators: & | ^

	private final String shortName;

	Operator(String shortName) {
		this.shortName = shortName;
	}

	public String getShortName() {
		return shortName;
	}
}
