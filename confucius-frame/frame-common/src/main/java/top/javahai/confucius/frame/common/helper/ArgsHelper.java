package top.javahai.confucius.frame.common.helper;

/**
 * @Description
 * @Author Ethan Huang
 * @Date 12:40 2018/10/30
 **/
public class ArgsHelper {

    private ArgsHelper() {
    }

    public static String[] buildArgs(Object arg) {
        return new String[]{String.valueOf(arg)};
    }

    public static String[] buildArgs(Object arg, Object arg1) {
        return new String[]{String.valueOf(arg), String.valueOf(arg1)};
    }

    public static String[] buildArgs(Object arg, Object arg1, Object arg2) {
        return new String[]{String.valueOf(arg), String.valueOf(arg1), String.valueOf(arg2)};
    }

    public static String[] buildArgs(Object... args) {
        return ArrayHelper.toStringArray(args);
    }
}
