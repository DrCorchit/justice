package justice.lang.code.types.evaluators;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import justice.lang.code.adapters.Adapter;
import justice.lang.code.routines.Field;
import justice.lang.code.routines.Fingerprint;
import justice.lang.code.routines.Routine;
import justice.lang.code.types.DataType;
import justice.lang.data.Data;
import justice.lang.namespaces.Namespace;

import java.util.HashSet;
import java.util.LinkedHashMap;

public class Evaluator<T extends Data> implements Implementation<T> {

	private final Namespace parent;
	private final String name;
	//private final ImmutableSet<Interface> implemented;
	private final DataType<?> staticType;
	private final DataType<T> instanceType;
	private final ImmutableList<Evaluator<?, T>> parentEvaluators;
	private final ImmutableMap<String, Field<T, ?>> fields;
	private final ImmutableMap<String, Routine<T, ?, ?>> routines;

	public Evaluator(Namespace parent, String name) {
		this.parent = parent;
		this.name = name;
	}

	@Override
	public Field<T, ?> getField(String name) {
		return null;
	}

	@Override
	public Routine<T, ?, ?> getRoutine(String name) {
		return null;
	}

	@Override
	public ImmutableList<Interface> parentEvaluators() {
		return null;
	}

	@Override
	public ImmutableMap<String, Fingerprint> getFingerprints() {
		return null;
	}

	@Override
	public Class<Interface> dataClass() {
		return null;
	}

	@Override
	public Interface getType() {
		return null;
	}

	@Override
	public Fingerprint get(String name) {
		return null;
	}

	@Override
	public DataType getType(String name) {
		return null;
	}

	@Override
	public Namespace parent() {
		return null;
	}

	@Override
	public String name() {
		return null;
	}

	public static class Builder<T extends Data> {
		private final Namespace parent;
		private final String name;
		private final HashSet<Interface> interfaces;
		private final LinkedHashMap<Implementation, Adapter> parents;

		public static Builder builder(Namespace parent, String name) {
			return new Builder(parent, name);
		}

		public Builder(Namespace parent, String name) {
			this.parent = parent;
			this.name = name;
			interfaces = new HashSet<>();
			parents = new LinkedHashMap<>();
		}

		public Builder<T> addInterface(Interface i) {
			interfaces.add(i);
			return this;
		}

		public <R extends Data> Builder<T> addParent(Implementation<R> parent, Adapter<T, R> adapter) {
			parents.put(parent, adapter);
			return this;
		}


		public Evaluator<T> build() {
			//TODO
			return null;
		}
	}
}
