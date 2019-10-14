package justice.lang.code.routines;

import justice.lang.code.types.DataType;
import justice.lang.data.Data;

//a special case adapter that doesn't actually do any adapting.
public class IdentityAdapter<T extends DataType> implements Adapter<T, T> {

	private final T type;

	public IdentityAdapter(T type) {
		this.type = type;
	}

	@Override
	public T inputType() {
		return type;
	}

	@Override
	public T outputType() {
		return type;
	}

	@Override
	public Data<T> adapt(Data<T> other) {
		return other;
	}
}
