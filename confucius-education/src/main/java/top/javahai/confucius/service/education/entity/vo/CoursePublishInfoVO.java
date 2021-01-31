package top.javahai.confucius.service.education.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hai
 * @program: confucius
 * @description: 课程发布信息数据
 * @create 2021/1/17 - 22:29
 **/
@Data
public class CoursePublishInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    /**
     * 只用于显示
     */
    private String price;
}
