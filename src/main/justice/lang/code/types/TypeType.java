package justice.lang.code.types;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import justice.lang.data.Data;
import justice.lang.namespaces.HardcodedNamespace;

public class TypeType implements DataType<DataType<?>> {

	public static final TypeType INSTANCE = new TypeType();

	private TypeType() {
		if (INSTANCE != null) throw new IllegalStateException();
		parent().put(this);
	}

	@Override
	public boolean accept(Data data) {
		return data instanceof DataType;
	}

	@Override
	public DataType<?> cast(Data data) {
		return (DataType<?>) data;
	}

	@Override
	public boolean isAncestorType(DataType other) {
		return other == this;
	}

	@Override
	public DataType<?> getType() {
		return this;
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
		return new JsonPrimitive("type");
	}

	@Override
	public HardcodedNamespace parent() {
		return HardcodedNamespace.TYPES;
	}

	@Override
	public String name() {
		return "Type";
	}
}
