package justice.lang.code.routines;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonElement;
import justice.lang.code.types.Code;
import justice.lang.code.types.CodeType;
import justice.lang.code.types.DataType;
import justice.lang.data.Data;
import justice.lang.namespaces.Namespace;
import justice.lang.namespaces.NamespaceObject;

import java.util.Set;

public class Fingerprint implements CodeType, NamespaceObject {

	private final Namespace parent;
	private final String name;
	private final DataType argumentType, returnType;
	private final ImmutableSet<Tag> tags;

	public Fingerprint(Namespace parent, String name, Set<Tag> tags, DataType argumentType, DataType returnType) {
		this.parent = parent;
		this.name = name;
		this.argumentType = argumentType;
		this.returnType = returnType;
		this.tags = ImmutableSet.copyOf(tags);
	}

	public boolean accept(Fingerprint other) {
		//require same name
		if (!name.equals(other.name)) return false;

		//the other must meet our guarantees
		if (!other.tags.containsAll(tags)) return false;

		//other's return type must be a subclass
		if (!returnType.isAncestorType(other.returnType)) return false;

		//if we're a field, they must be a field too
		if (argumentType == null) return other.argumentType == null;
		else return other.argumentType != null && argumentType.isAncestorType(other.argumentType);
	}

	public DataType getArgumentType() {
		return argumentType;
	}

	public DataType getReturnType() {
		return returnType;
	}

	@Override
	public boolean accept(Data data) {
		return false;
	}

	@Override
	public Code cast(Data data) {
		return null;
	}

	@Override
	public boolean isAncestorType(DataType other) {
		return false;
	}

	@Override
	public Fingerprint getType() {
		return this;
	}

	@Override
	public boolean isImmutable() {
		return false;
	}

	@Override
	public boolean isDeeplyImmutable() {
		return false;
	}

	@Override
	public JsonElement serialize() {
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
