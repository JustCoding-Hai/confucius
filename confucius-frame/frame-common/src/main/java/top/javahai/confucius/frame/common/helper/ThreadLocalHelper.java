package top.javahai.confucius.frame.common.helper;

import org.slf4j.MDC;

/**
 * @author libo
 * @Title: ThreadLocalHelper
 * @ProjectName venus-frame
 * @Description: use MDC to deal with threadLocal
 * @date 2018/11/2914:57
 */
public class ThreadLocalHelper {

    private ThreadLocalHelper() {
    }

    public static String get(String key) {
        return MDC.get(key);
    }

    public static void put(String key, String value) {
        MDC.put(key, value);
    }
}
