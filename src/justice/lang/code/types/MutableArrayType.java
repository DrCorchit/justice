package justice.lang.code.types;

import justice.lang.data.MutableArrayData;

public class MutableArrayType implements ArrayType<MutableArrayData> {

	public static final MutableArrayType INSTANCE = new MutableArrayType();

	private MutableArrayType() {
		if (INSTANCE != null) throw new IllegalStateException();
	}

	@Override
	public Class<MutableArrayData> dataClass() {
		return MutableArrayData.class;
	}

}
