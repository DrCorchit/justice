package justice.lang.code.routines;

//describes traits of functions
public enum Tag {
	INSTANCE,		//the field or routine requires an instance
	TERMINATING,	//the field routine is guaranteed to terminate
	DETERMINISTIC,	//the routine's output is purely a function of its input
	INDEPENDENT,	//the routine has no side effects on any external data
	COMPILED		//Arguments to the routine must be compile-time instances
}