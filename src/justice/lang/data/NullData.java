package justice.lang.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import justice.lang.code.types.NullType;

public class NullData implements Data<NullType> {

	public static final NullData INSTANCE = new NullData();

	private NullData() {
		if (INSTANCE != null) throw new IllegalStateException();
	}

	@Override
	public NullType getType() {
		return NullType.INSTANCE;
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
