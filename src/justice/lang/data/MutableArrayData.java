package justice.lang.data;

import justice.lang.code.types.MutableArrayType;

import java.util.ArrayList;

public class MutableArrayData implements ArrayData<MutableArrayType> {

	private final ArrayList<Data> data;

	public MutableArrayData(ArrayList<Data> data) {
		this.data = data;
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
		this.data.set(index, data);
	}

	@Override
	public MutableArrayType getType() {
		return MutableArrayType.INSTANCE;
	}

	@Override
	public boolean isImmutable() {
		return false;
	}

	@Override
	public boolean isDeeplyImmutable() {
		return false;
	}
}
