package top.javahai.confucius.service.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.statistics.entity.Daily;
import top.javahai.confucius.service.statistics.mapper.DailyMapper;
import top.javahai.confucius.service.statistics.remote.UserCenterClient;
import top.javahai.confucius.service.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-31
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    UserCenterClient userCenterClient;

    @Override
    public void createStatisticsByDay(String day) {

        //如果当日的统计记录已存在，则删除重新统计|或 提示用户当日记录已存在
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated", day);
        baseMapper.delete(queryWrapper);

        //生成统计记录
        ResultVO<Integer> resultVO = userCenterClient.countRegisterNum(day);
        Integer registerNum = resultVO.getData();
        int loginNum = RandomUtils.nextInt(100, 200);
        int videoViewNum = RandomUtils.nextInt(100, 200);
        int courseNum = RandomUtils.nextInt(100, 200);

        //在本地数据库创建统计信息
        Daily daily = new Daily();
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);

        baseMapper.insert(daily);
    }

    @Override
    public Map<String, Map<String, Object>> getChartData(String begin, String end) {
        Map<String, Map<String, Object>> map = new HashMap<>(16);
        Map<String, Object> registerNum = this.getChartDataByType(begin, end, "register_num");
        Map<String, Object> loginNum = this.getChartDataByType(begin, end, "login_num");
        Map<String, Object> videoViewNum = this.getChartDataByType(begin, end, "video_view_num");
        Map<String, Object> courseNum = this.getChartDataByType(begin, end, "course_num");
        map.put("registerNum", registerNum);
        map.put("loginNum", loginNum);
        map.put("videoViewNum", videoViewNum);
        map.put("courseNum", courseNum);
        return map;
    }

    /**
     * 根据type获取统计数据
     * @param begin
     * @param end
     * @param type
     * @return
     */
    private Map<String, Object> getChartDataByType(String begin, String end,String type) {
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<>();
        List<String> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        queryWrapper.select(type,"date_calculated");
        queryWrapper.between("date_calculated",begin,end);

        //selectMaps方法一条数据就是一个map
        List<Map<String, Object>> mapData = baseMapper.selectMaps(queryWrapper);
        System.out.println(mapData);
        for (Map<String, Object> data : mapData) {
            String dateCalculated = ((String) data.get("date_calculated"));
            xList.add(dateCalculated);
            Integer count = ((Integer) data.get(type));
            yList.add(count);
        }

        HashMap<String, Object> map = new HashMap<>(16);
        map.put("xData",xList);
        map.put("yData",yList);

        return map;
    }

}
