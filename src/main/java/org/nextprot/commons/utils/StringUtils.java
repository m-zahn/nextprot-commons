package org.nextprot.commons.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StringUtils {

	public static String mkString(Iterable<?> values, String start, String sep,
			String end) {
		// if the array is null or empty return an empty string
		if (values == null || !values.iterator().hasNext())
			return "";

		// move all non-empty values from the original array to a new list
		// (empty is a null, empty or all-whitespace string)
		List<String> nonEmptyVals = new LinkedList<String>();
		for (Object val : values) {
			//if (val != null && val.toString().trim().length() > 0) {
				if(val == null) {
					nonEmptyVals.add(null);
				}
				else {
					nonEmptyVals.add(start + val.toString() + end);
				}
			//}
		}

		// if there are no "non-empty" values return an empty string
		if (nonEmptyVals.size() == 0)
			return "";

		// iterate the non-empty values and concatenate them with the separator,
		// the entire string is surrounded with "start" and "end" parameters
		StringBuilder result = new StringBuilder();
		int i = 0;
		for (String val : nonEmptyVals) {
			if (i > 0)
				result.append(sep);
			result.append(val);
			i++;
		}

		return result.toString();
	}

	public static String mkString(Object[] values, String start, String sep,
			String end) {
		return mkString(Arrays.asList(values), start, sep, end);
	}
}
