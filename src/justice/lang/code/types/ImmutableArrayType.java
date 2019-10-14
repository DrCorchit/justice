package justice.lang.code.types;

import justice.lang.data.ImmutableArrayData;

public class ImmutableArrayType implements ArrayType<ImmutableArrayData> {

	public static final ImmutableArrayType INSTANCE = new ImmutableArrayType();

	private ImmutableArrayType() {
		if (INSTANCE != null) throw new IllegalStateException();
	}

	@Override
	public Class<ImmutableArrayData> dataClass() {
		return ImmutableArrayData.class;
	}
}
