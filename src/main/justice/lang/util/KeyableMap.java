package justice.lang.util;

import java.util.Set;

public interface KeyableMap<T extends Keyable> {

	T get(String key);

	T put(T value);

	boolean has(T value);

	Set<String> keys();

}
