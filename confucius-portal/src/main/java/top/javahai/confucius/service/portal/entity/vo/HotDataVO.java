package top.javahai.confucius.service.portal.entity.vo;

import lombok.Data;
import top.javahai.confucius.service.portal.entity.Course;
import top.javahai.confucius.service.portal.entity.Teacher;

import java.util.List;

/**
 * @author Hai
 * @program: confucius
 * @description: 热门数据
 * @create 2021/1/23 - 16:24
 **/
@Data
public class HotDataVO {
    private List<Course> courseList;
    private List<Teacher> teacherList;
}
