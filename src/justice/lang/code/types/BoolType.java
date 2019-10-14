package justice.lang.code.types;

import justice.lang.data.BoolData;

public class BoolType implements NonNullType<BoolData> {

	public static final BoolType INSTANCE = new BoolType();

	private BoolType() {
		if (INSTANCE != null) throw new IllegalStateException();
	}

	@Override
	public Class<BoolData> dataClass() {
		return BoolData.class;
	}
}
