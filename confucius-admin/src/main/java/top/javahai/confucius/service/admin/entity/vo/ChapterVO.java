package top.javahai.confucius.service.admin.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Hai
 * @program: confucius
 * @description: 章节视图层对象
 * @create 2021/1/18 - 15:41
 **/
@Data
public class ChapterVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;
    private List<VideoVO> children;
}
