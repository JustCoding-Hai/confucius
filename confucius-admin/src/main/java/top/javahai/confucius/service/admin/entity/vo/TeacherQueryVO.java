package top.javahai.confucius.service.admin.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hai
 * @program: zhkuschool-frame
 * @description: 讲师查询条件
 * @create 2020/11/15 - 17:38
 **/
@Data
public class TeacherQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer level;
    private String joinDateBegin;
    private String joinDateEnd;
}
