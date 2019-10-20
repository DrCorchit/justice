package justice.lang.code.types;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import justice.lang.data.*;
import justice.lang.data.primitive.*;
import justice.lang.namespaces.HardcodedNamespace;

import javax.annotation.Nonnull;

public class SimpleDataType<D extends Data> implements DataType<D> {

	public static final SimpleDataType<NullData> NULL;
	public static final SimpleDataType<NonNullData> NON_NULL;
	public static final SimpleDataType<BoolData> BOOL;
	public static final SimpleDataType<NumberData> NUMBER;
	public static final SimpleDataType<IntData> INT;
	public static final SimpleDataType<RealData> REAL;
	public static final SimpleDataType<StringData> STRING;
	public static final SimpleDataType<DataType> TYPE;
	public static final SimpleDataType<Data> ANY;

	static {
		ANY = new SimpleDataType<>("Any", Data.class);
		ANY.parent().put(ANY);
		NULL = new SimpleDataType<>("Null", NullData.class);
		NULL.parent().put(NULL);
		NON_NULL = new SimpleDataType<>("NonNull", NonNullData.class);
		NON_NULL.parent().put(NON_NULL);
		BOOL = new SimpleDataType<>("Bool", BoolData.class);
		BOOL.parent().put(BOOL);
		NUMBER = new SimpleDataType<>("Number", NumberData.class);
		NUMBER.parent().put(NUMBER);
		INT = new SimpleDataType<>("Int", IntData.class);
		INT.parent().put(INT);
		REAL = new SimpleDataType<>("Real", RealData.class);
		REAL.parent().put(REAL);
		STRING = new SimpleDataType<>("String", StringData.class);
		STRING.parent().put(STRING);
		TYPE = new SimpleDataType<>("Type", DataType.class);
		TYPE.parent().put(TYPE);

	}

	@Nonnull
	private final String name;
	@Nonnull
	private final Class<D> clazz;

	private SimpleDataType(String name, Class<D> clazz) {
		this.name = name;
		this.clazz = clazz;
	}

	@Override
	public HardcodedNamespace parent() {
		return HardcodedNamespace.TYPES;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public boolean accept(Data data) {
		return clazz.isInstance(data);
	}

	@Override
	public D cast(Data data) {
		return clazz.cast(data);
	}

	@Override
	public boolean isAncestorType(DataType other) {
		if (other instanceof SimpleDataType) {
			SimpleDataType sdt = (SimpleDataType) other;
			return clazz.isAssignableFrom(sdt.clazz);
		} else {
			return false;
		}
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
		return new JsonPrimitive(name());
	}
}
