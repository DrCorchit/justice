package justice.lang.code.routines;

import justice.lang.code.adapters.AdaptedRoutine;
import justice.lang.code.adapters.Adapter;
import justice.lang.code.types.DataType;
import justice.lang.data.Data;
import justice.lang.namespaces.Namespace;

import java.util.Set;

//input/args/output
public class Routine<I extends Data, A extends Data, O extends Data> extends Fingerprint<A, O> {

	private final DataType<I> instanceType;

	public Routine(Namespace parent, String name, Set<Tag> tags, DataType<I> instanceType, DataType<A> argumentType, DataType<O> outputType) {
		super(parent, name, tags, argumentType, outputType);
		this.instanceType = instanceType;
	}

	public DataType<I> getInstanceType() {
		return instanceType;
	}

	public O execute(I instance, A argument) {
		//TODO
		return null;
	}

	public O castAndExecute(Data instance, Data argument) {
		return execute(getInstanceType().cast(instance), getArgumentType().cast(argument));
	}

	public <T extends Data> Routine<T, A, O> adapt(Adapter<T, I> adapter) {
		return new AdaptedRoutine<>(this, adapter);
	}
}
