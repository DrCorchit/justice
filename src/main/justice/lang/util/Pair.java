package justice.lang.util;

import javax.annotation.Nonnull;

public class Pair<K, V> {

	@Nonnull
	public final K key;
	@Nonnull
	public final V value;

	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pair) {
			Pair other = (Pair) obj;
			return key.equals(other.key) && value.equals(other.value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return key.hashCode() + 1000 * value.hashCode();
	}
}
