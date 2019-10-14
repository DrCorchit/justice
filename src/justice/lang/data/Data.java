package justice.lang.data;

import com.google.gson.JsonElement;
import justice.lang.code.types.DataType;

public interface Data<T extends DataType> {

	T getType();

	boolean isImmutable();

	boolean isDeeplyImmutable();

	JsonElement serialize();
}
