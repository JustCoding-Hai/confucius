package top.javahai.confucius.service.admin.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/17 - 12:35
 **/
@Data
public class CourseQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String teacherId;
    private String subjectParentId;
    private String subjectId;
}
