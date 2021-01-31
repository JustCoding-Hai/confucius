package top.javahai.confucius.frame.common.helper;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author Ethan Huang
 * @Date 12:41 2018/10/30
 **/
public class ArrayHelper extends ArrayUtils {

	private static final Logger logger = LoggerFactory.getLogger(ArrayHelper.class);

	private static final String DEFAULT_SEPARATOR = ",";

	public static String toSimpleString(Object[] array) {
		return toSimpleString(array, DEFAULT_SEPARATOR);
	}

	public static String toSimpleString(Object[] array, String separator) {
		if (array == null) {
			return "";
		}
		return Stream.of(array).map(String::valueOf).collect(Collectors.joining(separator));
	}

	public static boolean containsAll(Object[] array, Object[] arrayToFind) {
		for (Object obj : arrayToFind) {
			if (!ArrayUtils.contains(array, obj)) {
				logger.warn("{} is not contains", obj);
				return false;
			}
		}
		return true;
	}

}
