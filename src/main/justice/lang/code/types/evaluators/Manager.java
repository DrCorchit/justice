package justice.lang.code.types.evaluators;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import justice.lang.code.types.DataType;
import justice.lang.data.Data;
import justice.lang.namespaces.Namespace;

import java.util.NoSuchElementException;
import java.util.WeakHashMap;

public class Manager implements Implementation {

	private final DataType<?> staticType;
	private final Data staticData;

	//private final DataType instanceType;
	//private final Data instanceData;

	private int nextId = 0;
	private final WeakHashMap<Integer, Instance> instances;

	public Manager(Namespace parent, String name) {
		instances = new WeakHashMap<>();
		staticType = null;
		staticData = null;
	}

	public Instance create(Data data) {
		//Instance output = new Instance(nextId++, data);
		//instances.put(output.id, output);
		//return output;
		return null;
	}

	public int size() {
		return nextId;
	}

	boolean has(int index) {
		return instances.containsKey(index);
	}

	public Instance get(int index) {
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

	@Override
	public Namespace parent() {
		return null;
	}

	@Override
	public String name() {
		return null;
	}

	public class Instance implements Data {
		private final int id;
		private final Data data;

		private Instance(int id, Data data) {
			this.id = id;
			this.data = data;
		}

		Manager getManager() {
			return Manager.this;
		}

		public int getId() {
			return id;
		}

		@Override
		public DataType getType() {
			return null;
		}

		@Override
		public boolean isImmutable() {
			return data.isImmutable();
		}

		@Override
		public boolean isDeeplyImmutable() {
			return data.isDeeplyImmutable();
		}

		@Override
		public JsonElement serialize() {
			return new JsonPrimitive(id);
		}
	}
}