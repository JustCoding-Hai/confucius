package top.javahai.confucius.service.admin.mapper;

import org.apache.ibatis.annotations.Param;
import top.javahai.confucius.service.admin.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.javahai.confucius.service.admin.entity.vo.SubjectVO;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
public interface SubjectMapper extends BaseMapper<Subject> {

    /**
     * 该循环查找方式会导致访问数据库次数很多，前台应用不能使用该方式。
     * 而是使用Stream进行数据嵌套设置。
     * get Nested Subject List
     * @param parentId
     * @return
     */
    List<SubjectVO> getNestedSubjectList(@Param("parentId") String parentId);
}
