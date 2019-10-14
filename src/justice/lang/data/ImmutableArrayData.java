package justice.lang.data;

import com.google.common.collect.ImmutableList;
import justice.lang.code.types.ImmutableArrayType;

public class ImmutableArrayData implements ArrayData<ImmutableArrayType> {

	private final boolean isDeeplyImmutable;
	private final ImmutableList<Data> data;

	public ImmutableArrayData(ImmutableList<Data> data) {
		this.data = data;
		isDeeplyImmutable = checkDeeplyImmutable();
	}

	private boolean checkDeeplyImmutable() {
		for (Data datum : data) if (!datum.isDeeplyImmutable()) return false;
		return true;
	}

	@Override
	public ImmutableArrayType getType() {
		return ImmutableArrayType.INSTANCE;
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
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isImmutable() {
		return true;
	}

	@Override
	public boolean isDeeplyImmutable() {
		return isDeeplyImmutable;
	}
}
