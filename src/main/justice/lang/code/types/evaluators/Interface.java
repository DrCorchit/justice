package justice.lang.code.types.evaluators;

import justice.lang.code.types.Code;
import justice.lang.data.TypeData;
import justice.lang.data.primitive.NullData;

public interface Interface extends Code<TypeData, NullData> {

	TypeData getStaticData();

}

