package justice.lang.code.types;

import justice.lang.code.routines.Field;
import justice.lang.code.routines.Fingerprint;
import justice.lang.code.routines.Routine;
import justice.lang.data.Data;
import justice.lang.namespaces.Namespace;

//describes a class which uses static data to perform its tasks. note that this is not an instance of a class,
//but an instance of an implementation
public interface Code<S extends Data, T extends Data> extends Data, DataType<T>, Namespace<Fingerprint> {

	@Override
	default Namespace parent() {
		return getType().parent();
	}

	@Override
	default String name() {
		return String.format("%s<%s>", getType().name(), getStaticData());
	}

	S getStaticData();

	Field<T, ?> getField();

	Routine<T, ?, ?> getRoutine(String name);
}
