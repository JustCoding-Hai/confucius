package top.javahai.confucius.service.portal.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/17 - 12:37
 **/
@Data
public class CourseInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    private Integer lessonNum;
    private String price;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String status;
    private String gmtCreate;
}
