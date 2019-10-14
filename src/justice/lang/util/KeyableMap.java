package justice.lang.util;

import java.util.Set;

public interface KeyableMap<K, V extends Keyable<K>> {

	V get(K key);

	V put(V value);

	boolean has(V value);

	Set<V> values();

}
