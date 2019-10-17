package justice.lang.code.types.evaluators;

import com.google.common.collect.ImmutableList;
import justice.lang.code.routines.Field;
import justice.lang.code.routines.Fingerprint;
import justice.lang.code.routines.Routine;
import justice.lang.code.types.DataType;
import justice.lang.data.Data;

import java.util.Iterator;

//types of implementation are evaluator, manager, enumerator
public interface Implementation<T extends Data> extends Interface {

	//the type of data that acts as static data for the class
	//often is
	DataType<?> getStaticDataType();

	//the type of data the class operates on
	DataType<T> getInstanceDataType();

	Field<T, ?> getField(String name);

	Routine<T, ?, ?> getRoutine(String name);

	ImmutableList<Interface> parentEvaluators();

	default Data evaluate(String name, T instanceData, Data argumentData) {
		Fingerprint f = getField(name);
		Iterator<Interface> iter = parentEvaluators().iterator();
		while (f == null && iter.hasNext()) {
			f = iter.next().get(name);
		}
		if (f == null) {
			f = AnyEvaluator.INSTANCE.getField(name);
		}
		if (f == null) {
			throw new RuntimeException("No such field in " + name() + ": " + name);
		} else if (f instanceof Routine) {
			Routine r = (Routine) f;
			return r.castAndExecute(instanceData, argumentData);
		} else {
			throw new RuntimeException("The default implementation of " + name + " in " + name() + " is not overridden.");
		}
	}

}
