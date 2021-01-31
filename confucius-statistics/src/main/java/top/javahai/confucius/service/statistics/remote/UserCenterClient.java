package top.javahai.confucius.service.statistics.remote;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.javahai.confucius.frame.common.result.ResultVO;

/**
 * @author Hai
 * @program: confucius
 * @description: user center远程调用
 * @create 2021/1/31 - 16:58
 **/
@FeignClient(value = "confucius-user-center")
public interface UserCenterClient {

    /**
     * 获取每日注册人数
     * @param day
     * @return
     */
    @GetMapping(value = "api/uc/rpc/members/register-num-count")
    ResultVO<Integer> countRegisterNum(@RequestParam(value = "day") String day);

}
