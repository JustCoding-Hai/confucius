package top.javahai.confucius.service.admin.remote.fallback;

import org.springframework.stereotype.Service;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.admin.remote.OssClient;


/**
 * @author Hai
 * @program: zhkuschool-frame
 * @description:
 * @create 2021/1/12 - 18:34
 **/
@Service
public class OssClientFallBack implements OssClient {

    @Override
    public ResultVO removeFile(String url) {

        return ResultVO.ok();
    }
}
