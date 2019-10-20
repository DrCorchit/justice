package justice.lang.code.adapters;

import justice.lang.code.types.DataType;
import justice.lang.data.Data;

//a special case adapter that doesn't actually do any adapting.
public class IdentityAdapter<T extends Data> implements Adapter<T, T> {

	private final DataType<T> type;

	public IdentityAdapter(DataType<T> type) {
		this.type = type;
	}

	@Override
	public DataType<T> inputType() {
		return type;
	}

	@Override
	public DataType<T> outputType() {
		return type;
	}

	@Override
	public T adapt(T other) {
		return other;
	}
}
