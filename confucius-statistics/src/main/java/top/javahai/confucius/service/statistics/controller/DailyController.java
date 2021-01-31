package top.javahai.confucius.service.statistics.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.statistics.service.DailyService;

import javax.xml.transform.Result;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2021-01-31
 */
@Api(value = "统计分析管理")
@RestController
@RequestMapping("/admin/statistics/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @ApiOperation("生成统计记录")
    @PostMapping("/create/{day}")
    public ResultVO createStatisticsByDay(
            @ApiParam("统计日期")
            @PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return ResultVO.ok().message("数据统计生成成功");
    }

    @ApiOperation("显示统计数据")
    @GetMapping("show-chart/{begin}/{end}")
    public ResultVO<Map<String, Map<String, Object>>> showChart(
            @ApiParam("开始时间") @PathVariable String begin,
            @ApiParam("结束时间") @PathVariable String end){

        Map<String, Map<String, Object>> map = dailyService.getChartData(begin, end);
        ResultVO<Map<String, Map<String, Object>>> resultVO = new ResultVO<>();
        return resultVO.success().data(map);
    }

}

