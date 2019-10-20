package justice.lang.code.types;

import justice.lang.code.NamedData;
import justice.lang.data.Data;

public interface DataType<D extends Data> extends NamedData {

	boolean accept(Data data);

	D cast(Data data);

	boolean isAncestorType(DataType other);

	@Override
	default DataType<?> getType() {
		return TypeType.INSTANCE;
	}
}
