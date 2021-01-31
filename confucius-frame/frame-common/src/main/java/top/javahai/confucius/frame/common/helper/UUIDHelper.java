package top.javahai.confucius.frame.common.helper;

import java.util.UUID;

/**
 * The type Uuid helper.
 *
 * @author Bob Li
 * @date 13 :59 2018/10/30
 */
public class UUIDHelper {

	private UUIDHelper() {
	}

	/**
	 * Gets uuid.
	 *
	 * @return the uuid
	 */
	public static String getUuid() {
		return UUID.randomUUID().toString();
	}

}
