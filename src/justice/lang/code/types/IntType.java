package justice.lang.code.types;

import justice.lang.data.IntData;

public class IntType implements NumberType<IntData> {

	public static final IntType INSTANCE = new IntType();

	private IntType() {
		if (INSTANCE != null) throw new IllegalStateException();
	}

	@Override
	public Class<IntData> dataClass() {
		return IntData.class;
	}
}
