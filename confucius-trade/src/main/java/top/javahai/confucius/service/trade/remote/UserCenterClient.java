package top.javahai.confucius.service.trade.remote;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.javahai.confucius.frame.base.service.dto.MemberDTO;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/27 - 23:12
 **/
@Service
@FeignClient(value = "confucius-user-center")
public interface UserCenterClient {

    /**
     * getMemberDtoByMemberId
     * @param memberId
     * @return
     */
    @GetMapping("api/uc/rpc/members/member-dto/{memberId}")
    MemberDTO getMemberDtoByMemberId(@PathVariable(value = "memberId") String memberId);
}
