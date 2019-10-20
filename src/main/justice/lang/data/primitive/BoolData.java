package justice.lang.data.primitive;

import justice.lang.code.types.DataType;
import justice.lang.code.types.SimpleDataType;
import justice.lang.data.Data;

public class BoolData extends AbstractData implements Data {

	public static final BoolData TRUE = new BoolData(true), FALSE = new BoolData(false);

	private BoolData(boolean value) {
		super(value);
	}

	@Override
	public DataType<BoolData> getType() {
		return SimpleDataType.BOOL;
	}
}
