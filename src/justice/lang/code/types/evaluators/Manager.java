package justice.lang.code.types.evaluators;

import justice.lang.data.Data;
import justice.lang.data.Instance;
import justice.lang.namespaces.Namespace;

import java.util.NoSuchElementException;
import java.util.WeakHashMap;

public class Manager<T extends Data> extends Evaluator<Instance<T>> {

	private int nextId = 0;
	private final WeakHashMap<Integer, Instance<T>> instances;

	public Manager(Namespace parent, String name) {
		super(parent, name);
		instances = new WeakHashMap<>();
	}

	public Instance<T> create() {

	}

	public int size() {
		return nextId;
	}

	boolean has(int index) {
		return instances.containsKey(index);
	}

	public Instance<T> get(int index) {
		if (instances.containsKey(index)) {
			return instances.get(index);
		} else {
			String message;
			if (index < 0) {
				message = String.format("Instance id provided to %s is negative", getFullname());
			} else if (index >= nextId) {
				message = String.format("Instance id %d provided to %s has not yet been issued", index, getFullname());
			} else {
				message = String.format("Instance if %d provided to %s has been garbage collected.", index, getFullname());
			}

			throw new NoSuchElementException(message);
		}
	}
}
