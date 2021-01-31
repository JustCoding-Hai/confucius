package top.javahai.confucius.service.trade.remote;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.javahai.confucius.frame.base.service.dto.CourseDTO;
import top.javahai.confucius.frame.common.result.ResultVO;

/**
 * @author Hai
 * @program: confucius
 * @description: portal
 * @create 2021/1/27 - 23:11
 **/
@Service
@FeignClient(value = "confucius-portal")
public interface PortalClient {
    /**
     * getCourseDtoById
     * @return
     * @param courseId
     */
    @ApiOperation("根据课程id查询课程信息")
    @GetMapping("/portal/rpc/courses/course-dto/{courseId}")
    CourseDTO getCourseDtoById(@PathVariable(value = "courseId") String courseId);

    /**
     * updateBuyCountById
     * @param id
     * @return
     */
    @ApiOperation("根据课程id查询课程信息")
    @GetMapping("/portal/rpc/courses/update-buy-count/{id}")
    ResultVO updateBuyCountById(@PathVariable(value = "id") String id);

}
