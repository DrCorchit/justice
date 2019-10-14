package justice.lang.code.types;

import justice.lang.data.RealData;

public class RealType implements NumberType<RealData> {

	public static final RealType INSTANCE = new RealType();

	private RealType() {
		if (INSTANCE != null) throw new IllegalStateException();
	}

	@Override
	public Class<RealData> dataClass() {
		return RealData.class;
	}

}
