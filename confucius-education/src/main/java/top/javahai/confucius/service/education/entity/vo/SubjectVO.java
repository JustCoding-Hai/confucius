package top.javahai.confucius.service.education.entity.vo;

import lombok.Data;
import top.javahai.confucius.service.education.entity.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/14 - 22:26
 **/
@Data
public class SubjectVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;
    private List<SubjectVO> children=new ArrayList<>();
}
