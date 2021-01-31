package top.javahai.confucius.service.uc.remote;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.base.service.dto.MemberDTO;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.uc.service.MemberService;

/**
 * @author Hai
 * @program: confucius
 * @description: 会员信息远程调用控制器
 * @create 2021/1/27 - 23:03
 **/
@Api(value = "会员远程调用",tags = "Member RPC Controller")
@RestController
@RequestMapping("api/uc/rpc/members")
public class MemberRpcController {
    @Autowired
    MemberService memberService;

    @ApiOperation("根据会员id查询会员信息")
    @GetMapping("/member-dto/{memberId}")
    public MemberDTO getMemberDtoByMemberId(
            @ApiParam(value = "会员ID", required = true)
            @PathVariable String memberId){
        return memberService.getMemberDTOByMemberId(memberId);
    }

    @ApiOperation(value = "根据日期统计注册人数")
    @GetMapping(value = "/register-num-count")
    public ResultVO<Integer> countRegisterNum(
            @ApiParam(name = "day", value = "统计日期")
            @RequestParam(value = "day") String day){
        Integer num = memberService.countRegisterNumByDay(day);
        return new ResultVO<Integer>().success().data(num);
    }


}
