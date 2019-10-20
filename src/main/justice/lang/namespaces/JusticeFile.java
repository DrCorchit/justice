package justice.lang.namespaces;

import justice.lang.code.types.Code;
import justice.lang.code.types.CodeType;
import justice.lang.code.types.DataType;

import java.util.LinkedHashMap;

public class JusticeFile implements Namespace {

	private final String name;
	private final Namespace parent;

	private final LinkedHashMap<String, DataType> memberTypes;
	private final LinkedHashMap<String, NamespaceObject> members;

	public JusticeFile(Namespace parent, String name) {
		this.parent = parent;
		this.name = name;
		memberTypes = new LinkedHashMap<>();
		members = new LinkedHashMap<>();
	}

	public <T extends CodeType> void addEntry(String name, Code code) {
		memberTypes.put(name, code.getType());
		members.put(name, code);
	}

	@Override
	public NamespaceObject get(String name) {
		return members.get(name);
	}

	@Override
	public DataType getType(String name) {
		return memberTypes.get(name);
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
