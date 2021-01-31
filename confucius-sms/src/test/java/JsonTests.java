import org.junit.jupiter.api.Test;
import top.javahai.confucius.frame.common.helper.JsonHelper;

import java.util.HashMap;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/25 - 11:15
 **/
public class JsonTests {


    @Test
    public void test01(){
        String data="{\n" +
                "\t\"Message\": \"OK\",\n" +
                "\t\"RequestId\": \"873043ac-bcda-44db-9052-2e204c6ed20f\",\n" +
                "\t\"BizId\": \"607300000000000000^0\",\n" +
                "\t\"Code\": \"OK\"\n" +
                "}";
        HashMap hashMap = JsonHelper.parseObject(data, HashMap.class);
        System.out.println(hashMap.get("Message"));
        System.out.println(hashMap.get("RequestId"));

    }
}
