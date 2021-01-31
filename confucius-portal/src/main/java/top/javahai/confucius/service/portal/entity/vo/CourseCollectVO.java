package top.javahai.confucius.service.portal.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Hai
 * @program: confucius
 * @description: 课程收集
 * @create 2021/1/28 - 12:21
 **/
@Data
public class CourseCollectVO {
    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 课程id
     */
    private String courseId;
    /**
     * 标题
     */
    private String title;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 课时数
     */
    private Integer lessonNum;
    /**
     * 封面
     */
    private String cover;
    /**
     * 收藏时间
     */
    private String gmtCreate;
    /**
     * 讲师
     */
    private String teacherName;
}

