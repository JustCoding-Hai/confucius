package top.javahai.confucius.service.portal.service.impl;

import top.javahai.confucius.frame.common.helper.BeanHelper;
import top.javahai.confucius.frame.common.helper.CollectionHelper;
import top.javahai.confucius.service.portal.constant.CourseConstants;
import top.javahai.confucius.service.portal.entity.Subject;
import top.javahai.confucius.service.portal.entity.vo.SubjectVO;
import top.javahai.confucius.service.portal.mapper.SubjectMapper;
import top.javahai.confucius.service.portal.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    /**
     * 当前仅做了二级分类的处理
     * @return
     */
    @Override
    public List<SubjectVO> getNestedSubjectList() {
        List<Subject> subjectList = list();
        List<SubjectVO> subjectVOList = new ArrayList<>();
        subjectList.forEach(subject -> {
                    if (CourseConstants.COURSE_PARENT_SUBJECT.equals(subject.getParentId())){
                        SubjectVO subjectVO = new SubjectVO();
                        BeanHelper.copyProperties(subject,subjectVO);
                        //寻找子分类
                        List<SubjectVO> children = new ArrayList<>();

                        subjectList.forEach(child->{
                            if(subject.getId().equals(child.getParentId())){
                                SubjectVO childSubjectVO = new SubjectVO();
                                BeanHelper.copyProperties(child,childSubjectVO);
                                children.add(childSubjectVO);
                            }
                        });
                        subjectVO.setChildren(children);
                        subjectVOList.add(subjectVO);
                    }
                });
        return subjectVOList;

    }
}
