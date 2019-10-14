package justice.lang.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import justice.lang.code.types.NonNullType;

public abstract class AbstractData<T extends NonNullType> implements NonNullData<T> {

	final JsonPrimitive value;

	AbstractData(boolean value) {
		this.value = new JsonPrimitive(value);
	}

	AbstractData(int value) {
		this.value = new JsonPrimitive(value);
	}

	AbstractData(double value) {
		this.value = new JsonPrimitive(value);
	}

	AbstractData(String value) {
		if (value == null) throw new IllegalArgumentException();
		this.value = new JsonPrimitive(value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AbstractData) {
			return ((AbstractData) obj).value.equals(value);
		}
		return false;
	}

	@Override
	public JsonElement serialize() {
		return value;
	}

	@Override
	public boolean isImmutable() {
		return true;
	}

	@Override
	public boolean isDeeplyImmutable() {
		return true;
	}
}
