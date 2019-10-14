package justice.lang.data;

import justice.lang.code.types.NumberType;

public interface NumberData<T extends NumberType> extends NonNullData<T> {

	double getValue();
}
