package top.javahai.confucius.service.admin.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import top.javahai.confucius.service.admin.entity.Subject;
import top.javahai.confucius.service.admin.entity.excel.ExcelSubjectData;
import top.javahai.confucius.service.admin.entity.vo.SubjectVO;
import top.javahai.confucius.service.admin.listener.ExcelSubjectDataListener;
import top.javahai.confucius.service.admin.mapper.SubjectMapper;
import top.javahai.confucius.service.admin.service.SubjectService;
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
