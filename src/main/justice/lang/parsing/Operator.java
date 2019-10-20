package justice.lang.parsing;

public enum Operator {

	//boolean
	AND("&&", Type.BOOL),
	OR("||", Type.BOOL),
	NOT("!", Type.BOOL),
	XOR("^^", Type.BOOL),

	//bitwise
	BIT_AND("&", Type.BITWISE),
	BIT_OR("|", Type.BITWISE),
	BIT_XOR("^", Type.BITWISE),

	//COMPARISON
	GT(">", Type.COMP),
	LT("<", Type.COMP),
	GTE(">=", Type.COMP),
	LTE("<=", Type.COMP),
	EQ("==", Type.COMP),
	NEQ("!=", Type.COMP),

	//arithmetic
	PLUS("+", Type.MATH),
	MINUS("-", Type.MATH),
	MULT("*", Type.MATH),
	DIV("/", Type.MATH),
	POW("**", Type.MATH),
	ROOT("//", Type.MATH),
	MOD("%", Type.MATH),

	//assignment
	SET("=", Type.ASSIGN),
	INC("++", Type.ASSIGN),
	DEC("--", Type.ASSIGN),
	SET_PLUS("+=", Type.ASSIGN),
	SET_MINUS("-=", Type.ASSIGN),
	SET_MULT("*=", Type.ASSIGN),
	SET_DIV("/=", Type.ASSIGN),
	SET_POW("**=", Type.ASSIGN),
	SET_ROOT("//=", Type.ASSIGN),
	SET_MOD("%=", Type.ASSIGN);

	private final String shortName;
	private final Type type;

	Operator(String shortName, Type type) {
		this.shortName = shortName;
		this.type = type;
	}

	public String getShortName() {
		return shortName;
	}

	public enum Type {
		BOOL, BITWISE, COMP, MATH, ASSIGN
	}
}
