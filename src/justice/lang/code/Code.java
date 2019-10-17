package justice.lang.code;

import com.google.gson.JsonElement;
import justice.lang.code.types.DataType;
import justice.lang.data.Data;

//code is its own type.
public interface Code<T extends Code> extends Data<T>, DataType<T> {

	//code should be deeply immutable
	@Override
	default boolean isImmutable() {
		return true;
	}

	@Override
	default boolean isDeeplyImmutable() {
		return true;
	}

	@Override
	default JsonElement serialize() {
		throw new UnsupportedOperationException();
	}
}
