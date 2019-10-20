package justice.lang.data.primitive;

import justice.lang.code.types.DataType;
import justice.lang.code.types.SimpleDataType;

public class StringData extends AbstractData {

	public StringData(String value) {
		super(value);
	}

	public String getValue() {
		return value.getAsString();
	}

	@Override
	public DataType<StringData> getType() {
		return SimpleDataType.STRING;
	}
}
