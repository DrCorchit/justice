package justice.lang.code.types;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;

import javax.annotation.Nullable;

public class DataTypeArray {

	private static final LoadingCache<ImmutableList<DataType>, DataTypeArray> instances = CacheBuilder.newBuilder().build(CacheLoader.from(DataTypeArray::new));



	private DataTypeArray(@Nullable ImmutableList<DataType> types) {

	}


}
