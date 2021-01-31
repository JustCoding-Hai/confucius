package top.javahai.confucius.service.cms.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hai
 * @program: confucius
 * @description: 广告展示数据
 * @create 2021/1/23 - 11:56
 **/
@Data
public class AdVO implements Serializable {
    private static final long serialVersionUID=1L;
    private String id;
    private String title;
    private Integer sort;
    private String type;
}
