package top.javahai.confucius.service.portal.entity.vo;

import lombok.Data;

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
