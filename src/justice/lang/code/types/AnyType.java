package justice.lang.code.types;

import justice.lang.data.Data;

public class AnyType implements DataType<Data> {

	public static final AnyType INSTANCE = new AnyType();

	private AnyType() {
		if (INSTANCE != null) throw new IllegalStateException();
	}

	@Override
	public Class<Data> dataClass() {
		return Data.class;
	}
}
