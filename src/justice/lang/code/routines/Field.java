package justice.lang.code.routines;

import justice.lang.code.adapters.AdaptedField;
import justice.lang.code.adapters.Adapter;
import justice.lang.code.types.DataType;
import justice.lang.code.types.NullType;
import justice.lang.data.Data;
import justice.lang.data.NullData;
import justice.lang.namespaces.Namespace;

import java.util.Set;

//input/output
public class Field<I extends Data, O extends Data> extends Routine<I, NullData, O> {

	public Field(Namespace parent, String name, Set<Tag> tags, DataType<I> instanceType, DataType<O> outputType) {
		super(parent, name, tags, instanceType, NullType.INSTANCE, outputType);
	}

	@Override
	public final NullType getArgumentType() {
		return NullType.INSTANCE;
	}

	@Override
	public <T extends Data> Field<T, O> adapt(Adapter<T, I> adapter) {
		return new AdaptedField<>(this, adapter);
	}
}
