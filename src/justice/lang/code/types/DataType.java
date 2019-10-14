package justice.lang.code.types;

import justice.lang.data.Data;

public interface DataType<D extends Data> {

	Class<D> dataClass();

	default boolean accept(Data<?> data) {
		return dataClass().isAssignableFrom(data.getClass());
	}

	default D cast(Data<?> data) {
		return dataClass().cast(data);
	}

	default boolean isAncestorType(DataType other) {
		return getClass().isAssignableFrom(other.getClass());
	}

}
