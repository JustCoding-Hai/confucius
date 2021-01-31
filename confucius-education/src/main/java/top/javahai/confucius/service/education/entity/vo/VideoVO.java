package top.javahai.confucius.service.education.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hai
 * @program: confucius
 * @description: 视频视图层对象
 * @create 2021/1/18 - 15:39
 **/
@Data
public class VideoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Boolean free;
    private Integer sort;

    private String videoSourceId;
}
