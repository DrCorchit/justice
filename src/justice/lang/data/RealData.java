package justice.lang.data;

import justice.lang.code.types.RealType;

public class RealData extends AbstractData<RealType> implements NumberData<RealType> {

	public RealData(double value) {
		super(value);
	}

	@Override
	public double getValue() {
		return value.getAsDouble();
	}

	@Override
	public RealType getType() {
		return RealType.INSTANCE;
	}
}
