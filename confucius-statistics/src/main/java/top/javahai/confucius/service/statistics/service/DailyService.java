package top.javahai.confucius.service.statistics.service;

import top.javahai.confucius.service.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-31
 */
public interface DailyService extends IService<Daily> {

    /**
     * 生成每日统计数据
     * @param day
     */
    void createStatisticsByDay(String day);

    /**
     * 获取表格数据
     * 返回值：
     * key(String):registerNum,loginNum,videoViewNum,courseNum
     * value(Map<String, Object>):
     *     key:日期1，日期2，。。
     *     value: 日期1统计数，日期2统计数，。。。
     * @param begin
     * @param end
     * @return
     */
    Map<String, Map<String, Object>> getChartData(String begin, String end);
}
