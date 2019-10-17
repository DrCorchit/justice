package justice.lang.code.adapters;

import justice.lang.code.types.DataType;
import justice.lang.data.Data;

//an adapter is a set of ASTs (one for each data
public interface Adapter<S extends Data, T extends Data> {

	DataType<S> inputType();

	DataType<T> outputType();

	T adapt(S other);

}
