package justice.lang.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import justice.lang.code.types.ArrayType;

public interface ArrayData<T extends ArrayType> extends NonNullData<T> {

	int length();

	Data get(int index);

	void set(int index, Data data);

	boolean isImmutable();

	boolean isDeeplyImmutable();

	@Override
	default JsonElement serialize() {
		JsonArray output = new JsonArray(length());
		for (int i = 0; i < length(); i++) output.add(get(i).serialize());
		return output;
	}
}
