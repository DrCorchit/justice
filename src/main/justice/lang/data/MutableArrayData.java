package justice.lang.data;

import justice.lang.code.types.AnyType;
import justice.lang.code.types.ArrayType;

import java.util.ArrayList;

public class MutableArrayData implements ArrayData {

	private final Data[] data;

	public MutableArrayData(ArrayList<Data> data) {
		this.data = data.toArray(new Data[0]);
	}

	@Override
	public int length() {
		return data.length;
	}

	@Override
	public Data get(int index) {
		return data[index];
	}

	@Override
	public void set(int index, Data data) {
		this.data[index] = data;
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
	public ArrayType getType() {
		return ArrayType.obtain(AnyType.INSTANCE, length(), false);
	}
}
