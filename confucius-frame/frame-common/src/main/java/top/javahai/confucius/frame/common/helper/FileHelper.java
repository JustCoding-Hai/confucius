package top.javahai.confucius.frame.common.helper;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author libo
 * @Title: FileHelper
 * @ProjectName venus-frame
 * @Description: TODO
 * @date 2019/4/312:01
 */
public class FileHelper extends FileUtils {

    public static String getClassPath() {

        Properties properties = System.getProperties();
        String os = properties.getProperty("os.name");

        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        if(os != null && os.toLowerCase().contains("window")){
            return path.startsWith("/") ? path.replaceFirst("/", "") : path;
        }
        return path;
    }

    public static String readClassPathFile(String filePath) {
        try {
            return readFileToString(new File(getClassPath() + filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperties().getProperty("os.name"));
    }

}
