package top.javahai.confucius.frame.common.helper;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * The type Object helper.
 *
 * @author Bob Li
 * @date 13 :53 2018/10/30
 */
public class ObjectHelper extends ObjectUtils {

    /**
     * Gets property.
     *
     * @param bean the bean
     * @param name the name
     * @return the property
     * @throws IllegalAccessException    the illegal access exception
     * @throws InvocationTargetException the invocation target exception
     * @throws NoSuchMethodException     the no such method exception
     */
    public static Object getProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return PropertyUtils.getProperty(bean, name);
    }

}
