package justice.lang.data;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import justice.lang.code.types.ArrayType;
import justice.lang.code.types.DataType;

import java.util.Collection;

public class TypeData implements ArrayData {

	private static final LoadingCache<ImmutableList<DataType>, TypeData> instances = CacheBuilder.newBuilder().build(CacheLoader.from(TypeData::new));

	private final ImmutableList<DataType> types;

	private TypeData(ImmutableList<DataType> types) {
		this.types = types;
	}

	public static TypeData of(Collection<DataType> types) {
		return instances.getUnchecked(ImmutableList.copyOf(types));
	}

	@Override
	public int length() {
		return types.size();
	}

	@Override
	public DataType get(int index) {
		return types.get(index);
	}

	@Override
	public void set(int index, Data data) {
		throw new UnsupportedOperationException();
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
	public ArrayType getType() {
		return null;
	}

	@Override
	public JsonElement serialize() {
		JsonArray output = new JsonArray(types.size());
		for (int i = 0; i < types.size(); i++) output.add(types.get(i).name());
		return output;
	}
}
