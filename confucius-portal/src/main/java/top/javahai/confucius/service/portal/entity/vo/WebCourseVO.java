package top.javahai.confucius.service.portal.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Hai
 * @program: confucius
 * @description: 前台课程展示信息
 * @create 2021/1/22 - 15:39
 **/
@Data
public class WebCourseVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private BigDecimal price;
    private Integer lessonNum;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String description;
    private String teacherId;
    private String teacherName;
    private String intro;
    private String avatar;
    private String subjectLevelOneId;
    private String subjectLevelOne;
    private String subjectLevelTwoId;
    private String subjectLevelTwo;
    private List<ChapterVO> chapterVOList;
}
