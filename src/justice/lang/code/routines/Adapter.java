package justice.lang.code.routines;

import justice.lang.code.types.DataType;
import justice.lang.data.Data;

//an adapter is a set of ASTs (one for each data
public interface Adapter<I extends DataType, O extends DataType> {

	I inputType();

	O outputType();

	Data<O> adapt(Data<I> other);
}
