package justice.lang.code.adapters;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import justice.lang.code.types.evaluators.Implementation;
import justice.lang.code.routines.Fingerprint;
import justice.lang.code.types.evaluators.Interface;
import justice.lang.namespaces.Namespace;
import justice.lang.code.routines.Field;
import justice.lang.code.routines.Routine;
import justice.lang.code.types.DataType;
import justice.lang.code.types.evaluators.Evaluator;
import justice.lang.data.Data;

public class AdaptedImplementation<S extends Data, T extends Data> implements Implementation<S> {

	private final Evaluator<T> base;
	private final Adapter<S, T> converter;

	public AdaptedImplementation(Evaluator<T> base, Adapter<S, T> converter) {
		this.base = base;
		this.converter = converter;
	}

	@Override
	public DataType<?> getStaticDataType() {
		return base.getStaticDataType();
	}

	@Override
	public DataType<S> getInstanceDataType() {
		return converter.inputType();
	}

	@Override
	public Field<S, ?> getField(String name) {
		return base.getField(name).adapt(converter);
	}

	@Override
	public Routine<S, ?, ?> getRoutine(String name) {
		return base.getRoutine(name).adapt(converter);
	}

	@Override
	public ImmutableList<Interface> parentEvaluators() {
		return base.parentEvaluators();
	}

	@Override
	public Fingerprint get(String name) {
		return base.get(name);
	}

	@Override
	public DataType getType(String name) {
		return base.getType(name);
	}

	@Override
	public Namespace parent() {
		return base.parent();
	}

	@Override
	public String name() {
		return base.name();
	}


	@Override
	public ImmutableMap<String, Fingerprint> getFingerprints() {
		return base.getFingerprints();
	}

	@Override
	public Interface getType() {
		return base.getType();
	}

	@Override
	public Class<Interface> dataClass() {
		return null;
	}
}
