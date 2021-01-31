package top.javahai.confucius.service.portal.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hai
 * @program: confucius
 * @description: 前台课程查询数据实体
 * @create 2021/1/21 - 16:10
 **/
@Data
public class WebCourseQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String subjectParentId;
    private String subjectId;
    private String buyCountSort;
    private String gmtCreateSort;
    private String priceSort;
    /**
     * 1表示升序，2表示降序
     */
    private Integer type;
}
