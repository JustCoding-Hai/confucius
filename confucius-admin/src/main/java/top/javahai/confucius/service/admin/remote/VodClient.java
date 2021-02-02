package top.javahai.confucius.service.admin.remote;

import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import top.javahai.confucius.frame.common.result.ResultVO;

import java.util.List;

/**
 * @author Hai
 * @program: confucius
 * @description: vod微服务远程调用客户端
 * @create 2021/1/20 - 16:55
 **/
@FeignClient(value = "confucius-vod")
public interface VodClient {

    /**
     * 远程调用vod的删除视频接口
     * @param vodId 格式：id1,id2,..
     * @return
     */
    @DeleteMapping("/admin/rpc/vod/media")
    ResultVO removeVideo(@ApiParam(value="阿里云视频id", required = true)
                         @RequestParam(value = "vodId") String vodId);

    /**
     * 根据id列表批量删除视频
     * @param idList
     * @return
     */
    @DeleteMapping("/admin/rpc/vod/media/batch-remove")
    ResultVO removeVideoByIdList(@ApiParam(value="阿里云视频id列表", required = true)
                                 @RequestBody List<String> idList);

}
