package top.javahai.confucius.service.education.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import top.javahai.confucius.frame.base.service.model.BaseEntity;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("edu_comment")
@ApiModel(value="Comment", description="评论")
public class Comment extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "会员id")
    private String memberId;

    @ApiModelProperty(value = "会员昵称")
    private String nickname;

    @ApiModelProperty(value = "会员头像")
    private String avatar;

    @ApiModelProperty(value = "评论内容")
    private String content;


}
