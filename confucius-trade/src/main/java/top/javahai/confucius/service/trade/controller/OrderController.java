package top.javahai.confucius.service.trade.controller;


import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.javahai.confucius.frame.common.entity.JwtInfo;
import top.javahai.confucius.frame.common.helper.JwtHelper;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.trade.entity.Order;
import top.javahai.confucius.service.trade.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2021-01-27
 */
@RestController
@RequestMapping("api/trade/order")
@Api(tags = "网站订单管理")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("新增订单")
    @PostMapping("auth/save/{courseId}")
    public ResultVO<String> save(@PathVariable String courseId, HttpServletRequest request) {

        JwtInfo jwtInfo = JwtHelper.getMemberIdByJwtToken(request);
        String orderId = orderService.saveOrder(courseId, jwtInfo.getId());
        return new ResultVO<String>().success().data(orderId);
    }

    @ApiOperation("获取订单")
    @GetMapping("auth/get/{orderId}")
    public ResultVO<Order> get(@PathVariable String orderId, HttpServletRequest request) {
        JwtInfo jwtInfo = JwtHelper.getMemberIdByJwtToken(request);
        Order order = orderService.getByOrderId(orderId, jwtInfo.getId());
        return new ResultVO<Order>().success().data(order);
    }

    @ApiOperation( "判断课程是否购买")
    @GetMapping("auth/is-buy/{courseId}")
    public ResultVO<Boolean> isBuyByCourseId(@PathVariable String courseId, HttpServletRequest request) {

        JwtInfo jwtInfo = JwtHelper.getMemberIdByJwtToken(request);
        Boolean isBuy = orderService.isBuyByCourseId(courseId, jwtInfo.getId());
        return new ResultVO<Boolean>().success().data(isBuy);
    }

    @ApiOperation(value = "获取当前用户订单列表")
    @GetMapping("auth/list")
    public ResultVO<List<Order>> list(HttpServletRequest request) {
        JwtInfo jwtInfo = JwtHelper.getMemberIdByJwtToken(request);
        List<Order> list = orderService.selectByMemberId(jwtInfo.getId());
        return new ResultVO<List<Order>>().success().data(list);
    }

    @ApiOperation(value = "删除订单")
    @DeleteMapping("auth/remove/{orderId}")
    public ResultVO remove(@PathVariable String orderId, HttpServletRequest request) {
        JwtInfo jwtInfo = JwtHelper.getMemberIdByJwtToken(request);
        boolean result = orderService.removeOrderById(orderId, jwtInfo.getId());
        if (!result) {
            return ResultVO.error().message("数据不存在");
        }
        return ResultVO.ok().message("删除成功");
    }
}

