package justice.lang.util;

import javax.annotation.Nonnull;

public class Triple<S, T, U> {

	@Nonnull
	public final S e1;
	@Nonnull
	public final T e2;
	@Nonnull
	public final U e3;

	public Triple(S e1, T e2, U e3) {
		this.e1 = e1;
		this.e2 = e2;
		this.e3 = e3;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Triple) {
			Triple other = (Triple) obj;
			return e1.equals(other.e1) && e2.equals(other.e2) && e3.equals(other.e3);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return e1.hashCode() + 1000 * e2.hashCode() + 1000000 * e3.hashCode();
	}
}
