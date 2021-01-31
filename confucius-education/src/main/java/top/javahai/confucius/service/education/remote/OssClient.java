package top.javahai.confucius.service.education.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.education.remote.fallback.OssClientFallBack;


/**
 * @author Hai
 * @program: zhkuschool-frame
 * @description: oss服务调用方
 * @create 2021/1/12 - 15:06
 **/
@FeignClient(value = "confucius-oss",fallback = OssClientFallBack.class)
public interface OssClient {

    @DeleteMapping("/admin/rpc/oss/file/delete")
    ResultVO removeFile(@RequestParam(value = "url") String url);
}
