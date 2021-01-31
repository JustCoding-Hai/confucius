package top.javahai.confucius.service.education.service;

import jdk.internal.util.xml.impl.Input;
import top.javahai.confucius.service.education.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import top.javahai.confucius.service.education.entity.vo.SubjectVO;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 批量导入Excel中的数据
     * @param inputStream
     */
    void batchImport(InputStream inputStream);

    /**
     * 获取嵌套的课程分类数据
     * @return
     */
    List<SubjectVO> getNestedSubjectList();
}
