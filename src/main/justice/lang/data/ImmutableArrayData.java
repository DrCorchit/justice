package justice.lang.data;

import com.google.common.collect.ImmutableList;
import justice.lang.code.types.AnyType;
import justice.lang.code.types.ArrayType;

public class ImmutableArrayData implements ArrayData {

	private final boolean deeplyImmutable;
	private final ImmutableList<Data> data;

	public ImmutableArrayData(ImmutableList<Data> data) {
		this.data = data;
		deeplyImmutable = checkDeeplyImmutable();
	}

	private boolean checkDeeplyImmutable() {
		for (Data datum : data) if (!datum.isDeeplyImmutable()) return false;
		return true;
	}

	@Override
	public int length() {
		return data.size();
	}

	@Override
	public Data get(int index) {
		return data.get(index);
	}

	@Override
	public void set(int index, Data data) {
		throw new IllegalStateException();
	}

	@Override
	public boolean isImmutable() {
		return true;
	}

	@Override
	public boolean isDeeplyImmutable() {
		return deeplyImmutable;
	}

	@Override
	public ArrayType getType() {
		return ArrayType.obtain(AnyType.INSTANCE, length(), true);
	}
}
