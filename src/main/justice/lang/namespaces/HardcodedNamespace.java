package justice.lang.namespaces;

import justice.lang.code.NamedData;
import justice.lang.code.types.DataType;

import java.util.LinkedHashMap;

public class HardcodedNamespace implements Namespace {

	public static final HardcodedNamespace JUSTICE, TYPES;

	static {
		JUSTICE = new HardcodedNamespace(null, "main/resources/justice");
		TYPES = new HardcodedNamespace(JUSTICE, "types");
	}

	private final HardcodedNamespace parent;
	private final String name;
	private final LinkedHashMap<String, NamespaceObject> children;
	private final LinkedHashMap<String, DataType> types;

	public HardcodedNamespace(HardcodedNamespace parent, String name) {
		this.parent = parent;
		this.name = name;
		this.children = new LinkedHashMap<>();
		this.types = new LinkedHashMap<>();
	}

	public void put(NamedData data) {
		children.put(data.key(), data);
		types.put(data.key(), data.getType());
	}

	@Override
	public NamespaceObject get(String name) {
		return children.get(name);
	}

	@Override
	public DataType getType(String name) {
		return null;
	}

	@Override
	public Namespace parent() {
		return parent;
	}

	@Override
	public String name() {
		return name;
	}
}
