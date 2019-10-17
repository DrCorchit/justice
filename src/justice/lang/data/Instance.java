package justice.lang.data;

import justice.lang.code.types.DataType;
import justice.lang.code.types.evaluators.Manager;

public interface Instance<D extends Data> extends Data {

	Manager<D> getManager();

	@Override
	default DataType<D> getType() {
		return getManager().getInstanceDataType();
	}

	int getId();
}