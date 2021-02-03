package top.javahai.confucius.service.acl.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import top.javahai.confucius.frame.common.result.ResultVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/2/3 - 23:33
 **/
public class ResponseHelper {

    public static void out(HttpServletResponse response, ResultVO r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
