package justice.lang.data.primitive;

import justice.lang.code.types.DataType;
import justice.lang.code.types.SimpleDataType;
import justice.lang.data.NumberData;

public class IntData extends AbstractData implements NumberData {

	public IntData(int value) {
		super(value);
	}

	public int getIntValue() {
		return value.getAsInt();
	}

	@Override
	public double getValue() {
		return value.getAsDouble();
	}

	@Override
	public DataType<IntData> getType() {
		return SimpleDataType.INT;
	}
}
