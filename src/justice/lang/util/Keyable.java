package justice.lang.util;

public interface Keyable<T> {
	T key();

	default String keyAsString() {
		return normalize(key().toString());
	}

	static String normalize(String key) {
		//convert spaces/dash/slash/backslash to underscore _
		key = key.replaceAll("[/\\\\ -]", "_");
		//remove non-word characters
		key = key.replaceAll("\\W+", "");
		//capitalize all
		key = key.toUpperCase();
		return key;
	}
}
