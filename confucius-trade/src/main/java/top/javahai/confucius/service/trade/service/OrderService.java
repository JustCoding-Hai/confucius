package top.javahai.confucius.service.trade.service;

import top.javahai.confucius.service.trade.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-27
 */
public interface OrderService extends IService<Order> {

    /**
     * 生成订单
     * @param courseId
     * @param memberId
     * @return
     */
    String saveOrder(String courseId, String memberId);

    /**
     * getByOrderId
     * @param orderId
     * @param memberId
     * @return
     */
    Order getByOrderId(String orderId, String memberId);

    /**
     * 根据课程id和当前用户查询课程是否已购买
     * @param courseId
     * @param memberId
     * @return
     */
    Boolean isBuyByCourseId(String courseId, String memberId);

    /**
     * 通过memberId获取订单
     * @param id
     * @return
     */
    List<Order> selectByMemberId(String id);

    /**
     * 通过orderId和memberId删除订单
     * @param orderId
     * @param memberId
     * @return
     */
    boolean removeOrderById(String orderId, String memberId);

    /**
     * 根据orderNo获取订单信息
     * @param orderNo
     * @return
     */
    Order getOrderByOrderNo(String orderNo);

    /**
     * 更新订单支付状态并记录支付日志，将微信返回的支付结果全部记录进数据库的json字段中
     * @param notifyMap
     */
    void updateOrderStatus(Map<String, String> notifyMap);

    /**
     * 根据订单号查询订单是否支付成功
     * @param orderNo
     * @return
     */
    boolean queryPayStatus(String orderNo);
}
