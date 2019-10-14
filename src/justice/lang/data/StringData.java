package justice.lang.data;

import justice.lang.code.types.StringType;

public class StringData extends AbstractData<StringType> {

	public StringData(String value) {
		super(value);
	}

	public String getValue() {
		return value.getAsString();
	}

	@Override
	public StringType getType() {
		return StringType.INSTANCE;
	}
}
