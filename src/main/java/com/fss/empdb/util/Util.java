package com.fss.empdb.util;


import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.function.Predicate;
@Service
public class Util {

	private Util() {
		
	}
	
	public static Date convertStringToDate(final String str) throws ParseException {		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");		
		return sf.parse(str);		
	}

	public static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors)
	{
		final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

		return t ->
		{
			final List<?> keys = Arrays.stream(keyExtractors)
					.map(ke -> ke.apply(t))
					.collect(Collectors.toList());

			return seen.putIfAbsent(keys, Boolean.TRUE) == null;
		};
	}
	
}
