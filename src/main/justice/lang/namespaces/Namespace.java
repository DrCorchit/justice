package justice.lang.namespaces;

import justice.lang.code.types.DataType;

public interface Namespace<T extends NamespaceObject> extends NamespaceObject {
	T get(String name);

	DataType getType(String name);

	default boolean has(String name) {
		return get(name) != null;
	}
}