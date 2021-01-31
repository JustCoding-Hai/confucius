package top.javahai.confucius.service.education.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Hai
 * @program: confucius
 * @description: 课程目录导入
 * @create 2021/1/14 - 10:44
 **/
@Data
public class ExcelSubjectData {

    @ExcelProperty(value = "一级分类")
    private String levelOneTitle;

    @ExcelProperty(value = "二级分类")
    private String levelTwoTitle;

}
