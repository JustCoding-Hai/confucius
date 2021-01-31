package top.javahai.confucius.service.portal.service;

import top.javahai.confucius.service.portal.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import top.javahai.confucius.service.portal.entity.vo.SubjectVO;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 获取嵌套的课程分类数据
     * @return
     */
    List<SubjectVO> getNestedSubjectList();
}
