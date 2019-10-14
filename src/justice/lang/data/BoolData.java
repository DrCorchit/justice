package justice.lang.data;

import justice.lang.code.types.BoolType;

public class BoolData extends AbstractData<BoolType> implements Data<BoolType> {

	public static final BoolData TRUE = new BoolData(true), FALSE = new BoolData(false);

	private BoolData(boolean value) {
		super(value);
	}

	@Override
	public BoolType getType() {
		return BoolType.INSTANCE;
	}
}
