package justice.lang.code.types;

import justice.lang.data.StringData;

public class StringType implements NonNullType<StringData> {

	public static final StringType INSTANCE = new StringType();

	private StringType() {
		if (INSTANCE != null) throw new IllegalStateException();
	}

	@Override
	public Class<StringData> dataClass() {
		return StringData.class;
	}
}
