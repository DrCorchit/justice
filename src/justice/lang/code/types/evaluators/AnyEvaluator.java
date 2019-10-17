package justice.lang.code.types.evaluators;

import com.google.common.collect.ImmutableList;
import justice.lang.code.routines.Field;
import justice.lang.code.routines.Routine;
import justice.lang.code.types.DataType;
import justice.lang.data.Data;
import justice.lang.namespaces.Namespace;

import javax.annotation.Nullable;

public class AnyEvaluator implements Evaluator<Data> {
	public static final AnyEvaluator INSTANCE = new AnyEvaluator();

	private AnyEvaluator() {
		if (INSTANCE != null) throw new IllegalStateException();
	}

	@Nullable
	@Override
	public Field<Data, ?> getField(String name) {
		return null;
	}

	@Nullable
	@Override
	public Routine<Data, ?, ?> getRoutine(String name) {
		return null;
	}

	@Override
	public ImmutableList<Evaluator<Data>> parentEvaluators() {
		return null;
	}

	@Override
	public Routine get(String name) {
		return null;
	}

	@Override
	public DataType getType(String name) {
		return INSTANCE;
	}

	@Override
	public Namespace parent() {
		return null;
	}

	@Override
	public String name() {
		return "Root";
	}

	@Override
	public Class<Data> dataClass() {
		return Data.class;
	}
}
