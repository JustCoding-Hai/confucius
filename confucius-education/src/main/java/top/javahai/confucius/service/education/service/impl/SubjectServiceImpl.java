package top.javahai.confucius.service.education.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import top.javahai.confucius.service.education.entity.Subject;
import top.javahai.confucius.service.education.entity.excel.ExcelSubjectData;
import top.javahai.confucius.service.education.entity.vo.SubjectVO;
import top.javahai.confucius.service.education.listener.ExcelSubjectDataListener;
import top.javahai.confucius.service.education.mapper.SubjectMapper;
import top.javahai.confucius.service.education.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    SubjectMapper subjectMapper;


    /**
     *
     * @param inputStream
     */
    @Override
    public void batchImport(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelSubjectData.class,new ExcelSubjectDataListener(this))
                .excelType(ExcelTypeEnum.XLS)
                .sheet()
                .doRead();
    }

    @Override
    public List<SubjectVO> getNestedSubjectList() {
        return subjectMapper.getNestedSubjectList("0");
    }
}
