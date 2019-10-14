package justice.lang.code.types;

import justice.lang.data.NullData;

public class NullType implements DataType<NullData> {

	public static final NullType INSTANCE = new NullType();

	private NullType() {
		if (INSTANCE != null) throw new IllegalStateException();
	}

	@Override
	public Class<NullData> dataClass() {
		return NullData.class;
	}
}
