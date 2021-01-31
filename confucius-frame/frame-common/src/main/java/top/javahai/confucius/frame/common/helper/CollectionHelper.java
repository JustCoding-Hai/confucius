package top.javahai.confucius.frame.common.helper;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * The type Collection helper.
 *
 * @author Ethan Huang
 * @date 13 :45 2018/10/30
 */
public class CollectionHelper {

	private CollectionHelper() {
	}

	/**
	 * Is empty boolean.
	 *
	 * @param coll the coll
	 * @return the boolean
	 */
	public static boolean isEmpty(final Collection<?> coll) {
		return CollectionUtils.isEmpty(coll);
	}

	/**
	 * Is not empty boolean.
	 *
	 * @param coll the coll
	 * @return the boolean
	 */
	public static boolean isNotEmpty(final Collection<?> coll) {
		return CollectionUtils.isNotEmpty(coll);
	}


	public static <E> List<E> createArrayList() {
		return new ArrayList<>();
	}

	public static <E> List<E> createArrayList(int initialCapacity) {
		return new ArrayList<>(initialCapacity);
	}

	public static <K, V> Map<K, V> createHashMap(int initialCapacity) {
		return new HashMap<>(initialCapacity);
	}

	/**
	 * Contains all boolean.
	 *
	 * @param coll1 the coll 1
	 * @param coll2 the coll 2
	 * @return the boolean
	 */
	public static boolean containsAll(final Collection<?> coll1, final Collection<?> coll2) {
		return CollectionUtils.containsAll(coll1, coll2);
	}

}
