package justice.lang.code.adapters;

import com.google.common.collect.ImmutableList;
import justice.lang.namespaces.Namespace;
import justice.lang.code.routines.Fingerprint;
import justice.lang.code.routines.Routine;
import justice.lang.data.Data;

public class AdaptedRoutine<S extends Data, T extends Data, A extends Data, O extends Data> implements Routine<S, A, O> {

	private Routine<T, A, O> base;
	private Adapter<S, T> adapter;

	public AdaptedRoutine(Routine<T, A, O> base, Adapter<S, T> adapter) {
		this.base = base;
		this.adapter = adapter;
	}

	@Override
	public Fingerprint<O> getFingerprint() {
		return base.getFingerprint();
	}

	@Override
	public ImmutableList<Adapter<?, A>> getAdapters() {
		return base.getAdapters();
	}

	@Override
	public O execute(S instance, A arguments) {
		return base.execute(adapter.adapt(instance), arguments);
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
