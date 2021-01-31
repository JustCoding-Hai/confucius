package top.javahai.confucius.service.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import top.javahai.confucius.frame.base.service.model.BaseEntity;

/**
 * <p>
 * 课程简介
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("edu_course_description")
@ApiModel(value="CourseDescription", description="课程简介")
public class CourseDescription extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "id",type = IdType.NONE)
    @ApiModelProperty(value = "课程id")
    private String id;

    @ApiModelProperty(value = "课程简介")
    private String description;


}
