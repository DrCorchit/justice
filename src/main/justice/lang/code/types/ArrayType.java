package justice.lang.code.types;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import justice.lang.data.ArrayData;
import justice.lang.data.Data;
import justice.lang.namespaces.HardcodedNamespace;
import justice.lang.util.Triple;

import javax.annotation.Nonnull;

public class ArrayType implements DataType<ArrayData> {

	private static final LoadingCache<Triple<DataType<?>, Integer, Boolean>, ArrayType> instances = CacheBuilder.newBuilder().build(CacheLoader.from(ArrayType::new));

	private final DataType<?> type;
	private final int length;
	private final boolean immutable;

	private ArrayType(Triple<DataType<?>, Integer, Boolean> triple) {
		type = triple.e1;
		length = triple.e2;
		immutable = triple.e3;
		if (length < 0) throw new IllegalArgumentException();

		parent().put(this);
	}

	public static ArrayType obtain(@Nonnull DataType<?> type, int length, boolean immutable) {
		return instances.getUnchecked(new Triple<>(type, length, immutable));
	}

	@Override
	public boolean accept(Data data) {
		if (data instanceof ArrayData) {
			ArrayData array = (ArrayData) data;
			if (array.length() == length && array.isImmutable() == immutable) {
				for (int i = 0; i < array.length(); i++) if (!type.accept(array.get(i))) return false;
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayData cast(Data data) {
		if (accept(data)) {
			return (ArrayData) data;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean isAncestorType(DataType other) {
		if (other instanceof ArrayType) {
			ArrayType arrayType = (ArrayType) other;
			return length == arrayType.length
					&& immutable == arrayType.immutable
					&& type.isAncestorType(arrayType.type);
		}
		return false;
	}

	@Override
	public HardcodedNamespace parent() {
		return HardcodedNamespace.TYPES;
	}

	@Override
	public String name() {
		return "array";
	}

	@Override
	public String toString() {
		return String.format("%s<%s>[%d]%s", getFullname(), type.name(), length, immutable ? "" : "*");
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
		JsonObject output = new JsonObject();
		output.addProperty("type", name());
		output.add("member_type", type.serialize());
		output.addProperty("length", length);
		output.addProperty("immutable", immutable);
		return output;
	}
}
