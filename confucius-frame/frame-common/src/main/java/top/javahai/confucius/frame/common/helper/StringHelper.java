package top.javahai.confucius.frame.common.helper;

import org.apache.commons.lang3.StringUtils;

/**
 * The utility of String.
 *
 * @author Bob Li
 * @date 13 :59 2018/10/30
 */
public class StringHelper extends StringUtils {

    /**
     * Erase line separator string.
     *
     * @param str the str
     * @return the string
     */
    public static String eraseLineSeparator(String str) {

        if(str == null){
            return null;
        }

        if("".equals(str)){
            return "";
        }

        return str.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
    }
}
