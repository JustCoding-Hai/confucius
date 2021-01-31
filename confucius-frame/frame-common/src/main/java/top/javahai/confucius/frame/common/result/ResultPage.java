package top.javahai.confucius.frame.common.result;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Hai
 * @program: zhkuschool-frame
 * @description: 分页数据实体
 * @create 2020/11/15 - 17:05
 **/
@Data
@AllArgsConstructor
@ApiModel(value = "ResultPage",description = "统一分页数据结果")
public class ResultPage {
    private Long total;
    private List<?> records;
}
