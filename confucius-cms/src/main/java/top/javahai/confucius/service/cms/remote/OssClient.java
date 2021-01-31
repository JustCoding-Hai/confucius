package top.javahai.confucius.service.cms.remote;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import top.javahai.confucius.frame.common.result.ResultVO;

/**
 * @author Hai
 * @program: confucius
 * @description: Oss远程调用客户端
 * @create 2021/1/23 - 12:14
 **/
@Service
@FeignClient(value = "confucius-oss")
public interface OssClient {

    /**
     * 远程调用oss的删除图片接口
     * @param url
     * @return
     */
    @DeleteMapping("/admin/rpc/oss/file/delete")
    ResultVO removeFile(@RequestParam(value = "url") String url);
}
