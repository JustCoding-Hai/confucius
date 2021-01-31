package top.javahai.confucius.service.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.javahai.confucius.frame.base.service.dto.CourseDTO;
import top.javahai.confucius.frame.base.service.dto.MemberDTO;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.helper.JsonHelper;
import top.javahai.confucius.frame.common.helper.ObjectHelper;
import top.javahai.confucius.frame.common.helper.OrderNoHelper;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.service.trade.entity.Order;
import top.javahai.confucius.service.trade.entity.PayLog;
import top.javahai.confucius.service.trade.mapper.OrderMapper;
import top.javahai.confucius.service.trade.remote.PortalClient;
import top.javahai.confucius.service.trade.remote.UserCenterClient;
import top.javahai.confucius.service.trade.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javahai.confucius.service.trade.service.PayLogService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    PortalClient portalClient;

    @Autowired
    UserCenterClient userCenterClient;

    @Autowired
    PayLogService payLogService;

    @Override
    public String saveOrder(String courseId, String memberId) {
        //查询当前用户是否已有当前课程的订单
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("member_id",memberId);
        Order existedOrder = baseMapper.selectOne(queryWrapper);
        if (ObjectHelper.isNotEmpty(existedOrder)){
            return existedOrder.getId();
        }
        //查询课程信息
        CourseDTO courseDto = portalClient.getCourseDtoById(courseId);
        if (courseDto == null) {
            throw new BaseException(ResultCodeEnum.PARAM_ERROR);
        }

        //查询用户信息
        MemberDTO memberDto = userCenterClient.getMemberDtoByMemberId(memberId);
        if (memberDto == null) {
            throw new BaseException(ResultCodeEnum.PARAM_ERROR);
        }

        //创建订单
        Order order = new Order();
        //生成随机的订单号
        order.setOrderNo(OrderNoHelper.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        order.setTeacherName(courseDto.getTeacherName());
        // 现在以分为单位
        order.setTotalFee(courseDto.getPrice().multiply(new BigDecimal(100)));
        order.setMemberId(memberId);
        order.setMobile(memberDto.getMobile());
        order.setNickname(memberDto.getNickname());
        //未支付
        order.setStatus(0);
        //微信支付
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getId();
    }

    @Override
    public Order getByOrderId(String orderId, String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("id", orderId)
                .eq("member_id", memberId);
        Order order = baseMapper.selectOne(queryWrapper);
        return order;
    }

    @Override
    public Boolean isBuyByCourseId(String courseId, String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",memberId)
                .eq("course_id",courseId)
                .eq("status",1);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count>0;
    }

    @Override
    public List<Order> selectByMemberId(String id) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        queryWrapper.eq("member_id",id);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean removeOrderById(String orderId, String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",orderId);
        queryWrapper.eq("member_id",memberId);
        return this.remove(queryWrapper);
    }

    @Override
    public Order getOrderByOrderNo(String orderNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        return baseMapper.selectOne(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(Map<String, String> notifyMap) {
        //1.更新订单状态
        String orderNo = notifyMap.get("out_trade_no");
        Order order = this.getOrderByOrderNo(orderNo);
        //支付成功状态
        order.setStatus(1);
        baseMapper.updateById(order);

        //2.记录支付日志
        PayLog payLog = new PayLog();
        payLog.setOrderNo(orderNo);
        payLog.setPayTime(new Date());
        //支付类型-1：微信支付
        payLog.setPayType(1);
        //总金额(分)
        payLog.setTotalFee(Long.parseLong(notifyMap.get("total_fee")));
        //支付状态
        payLog.setTradeState(notifyMap.get("result_code"));
        payLog.setTransactionId(notifyMap.get("transaction_id"));
        payLog.setAttr(JsonHelper.toJSONString(notifyMap));
        payLogService.save(payLog);

        //3.更新课程销量
        portalClient.updateBuyCountById(order.getCourseId());
    }

    @Override
    public boolean queryPayStatus(String orderNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);
        Order order = baseMapper.selectOne(queryWrapper);
        return order.getStatus()==1;
    }
}
