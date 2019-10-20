package justice.lang.code.adapters;

import justice.lang.code.routines.Field;
import justice.lang.data.Data;
import justice.lang.data.primitive.NullData;
import justice.lang.namespaces.Namespace;

public class AdaptedField<S extends Data, T extends Data, O extends Data> extends Field<S, O> {
	private Field<T, O> base;
	private Adapter<S, T> adapter;

	public AdaptedField(Field<T, O> base, Adapter<S, T> adapter) {
		super(base.parent(), base.name(), null, null, null);
		this.base = base;
		this.adapter = adapter;
	}

	@Override
	public O execute(S instance, NullData arguments) {
		return base.execute(adapter.adapt(instance), NullData.INSTANCE);
	}

	@Override
	public Namespace parent() {
		return base.parent();
	}

	@Override
	public String name() {
		return base.name();
	}
}
