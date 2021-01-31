package top.javahai.confucius.service.education.entity.vo;

import lombok.Data;
import top.javahai.confucius.service.education.entity.Course;
import top.javahai.confucius.service.education.entity.Teacher;

import java.io.Serializable;
import java.util.List;

/**
 * @author Hai
 * @program: confucius
 * @description: 讲师详细信息
 * @create 2021/1/21 - 12:01
 **/
@Data
public class WebTeacherInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Teacher teacher;

    private List<CourseInfoVO> courseInfoVOList;

}
