package top.javahai.confucius.frame.base.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Hai
 * @program: confucius
 * @description: 课程数据传输对象
 * @create 2021/1/27 - 22:38
 **/
@Data
public class CourseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *     课程ID
     */
    private String id;
    /**
     *课程标题
     */
    private String title;
    /**
     *课程销售价格，设置为0则可免费观看
     */
    private BigDecimal price;
    /**
     *课程封面图片路径
     */
    private String cover;
    /**
     *课程讲师
     */
    private String teacherName;
}
