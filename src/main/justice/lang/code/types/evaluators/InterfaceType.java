package justice.lang.code.types.evaluators;

import com.google.gson.JsonElement;
import justice.lang.code.types.Code;
import justice.lang.code.types.CodeType;
import justice.lang.code.types.DataType;
import justice.lang.data.Data;
import justice.lang.namespaces.JusticeFile;
import justice.lang.data.TypeData;
import justice.lang.namespaces.Namespace;

public class InterfaceType implements CodeType<Interface> {

	private final String name;
	private final JusticeFile parent;
	private final int typeParameters;


	public InterfaceType(JusticeFile file, String name, int typeParameters) {
		this.parent = file;
		this.name = name;
		this.typeParameters = typeParameters;
	}

	@Override
	public Namespace parent() {
		return parent;
	}

	@Override
	public String name() {
		return name;
	}

	public InterfaceImpl obtain(TypeData types) {
		if (types.length() != typeParameters) {
			throw new IllegalArgumentException();
		}

		return null;
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

	public class InterfaceImpl {

	}

}
