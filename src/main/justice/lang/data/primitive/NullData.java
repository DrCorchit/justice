package justice.lang.data.primitive;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import justice.lang.code.types.DataType;
import justice.lang.code.types.SimpleDataType;
import justice.lang.data.Data;

public class NullData implements Data {

	public static final NullData INSTANCE = new NullData();

	private NullData() {
		if (INSTANCE != null) throw new IllegalStateException();
	}

	@Override
	public DataType<NullData> getType() {
		return SimpleDataType.NULL;
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
		return JsonNull.INSTANCE;
	}
}
