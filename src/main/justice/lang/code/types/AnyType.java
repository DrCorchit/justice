package justice.lang.code.types;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import justice.lang.data.Data;
import justice.lang.namespaces.HardcodedNamespace;

public class AnyType implements DataType<Data> {

	public static final AnyType INSTANCE = new AnyType();

	private AnyType() {
		if (INSTANCE != null) throw new IllegalStateException();
		parent().put(this);
	}


	@Override
	public boolean accept(Data data) {
		return true;
	}

	@Override
	public Data cast(Data data) {
		return data;
	}

	@Override
	public boolean isAncestorType(DataType other) {
		return true;
	}

	@Override
	public boolean isImmutable() {
		return true;
	}

	@Override
	public boolean isDeeplyImmutable() {
		return true;
	}

	@Override
	public JsonElement serialize() {
		return new JsonPrimitive( name());
	}

	@Override
	public HardcodedNamespace parent() {
		return HardcodedNamespace.TYPES;
	}

	@Override
	public String name() {
		return "Any";
	}
}
