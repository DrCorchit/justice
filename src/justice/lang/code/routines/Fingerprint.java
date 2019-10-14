package justice.lang.code.routines;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import justice.lang.code.types.DataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Fingerprint {

	private final String name;
	private final DataType returnType;
	private final ImmutableList<DataType> argumentTypes;
	private final ImmutableSet<Tag> tags;

	//field fingerprint
	public Fingerprint(String name, Set<Tag> tags, DataType returnType) {
		this.name = name;
		this.argumentTypes = ImmutableList.of();
		this.returnType = returnType;
		this.tags = ImmutableSet.copyOf(tags);
	}

	//routine/function fingerprint
	public Fingerprint(String name, Set<Tag> tags, DataType returnType, List<DataType> argumentTypes) {
		this.name = name;
		this.argumentTypes = ImmutableList.copyOf(argumentTypes);
		this.returnType = returnType;
		this.tags = ImmutableSet.copyOf(tags);
	}

	public boolean accept(Fingerprint other) {
		//require same name
		if (!name.equals(other.name)) return false;
		//other's return type must be a subclass
		if (!returnType.isAncestorType(other.returnType)) return false;

		//the other must meet our guarantees
		if (!other.tags.containsAll(tags)) return false;

		//if we're a field, they must be a field too
		if (argumentTypes.isEmpty()) return other.argumentTypes.isEmpty();

		//guarantee that other can handle any data this can handle
		ArrayList<DataType> unmatchedTypes = new ArrayList<>(argumentTypes);
		boolean changed;
		do {
			changed = false;
			for (DataType type : other.argumentTypes) {
				if (type.isAncestorType(unmatchedTypes.get(0))) {
					unmatchedTypes.remove(0);
					changed = true;
					break;
				}
			}
		} while (changed && !unmatchedTypes.isEmpty());
		//if empty, they met all our adaptor types
		return unmatchedTypes.isEmpty();
	}
}
