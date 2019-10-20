package justice.lang.data.primitive;

import justice.lang.code.types.DataType;
import justice.lang.code.types.SimpleDataType;
import justice.lang.data.NumberData;

public class RealData extends AbstractData implements NumberData {

	public RealData(double value) {
		super(value);
	}

	@Override
	public double getValue() {
		return value.getAsDouble();
	}

	@Override
	public DataType<RealData> getType() {
		return SimpleDataType.REAL;
	}
}
