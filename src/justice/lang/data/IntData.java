package justice.lang.data;

import justice.lang.code.types.IntType;

public class IntData extends AbstractData<IntType> implements NumberData<IntType> {

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
	public IntType getType() {
		return IntType.INSTANCE;
	}
}
