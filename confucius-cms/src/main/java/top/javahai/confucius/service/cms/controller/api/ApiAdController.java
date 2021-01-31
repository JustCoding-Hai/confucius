package top.javahai.confucius.service.cms.controller.api;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.cms.entity.Ad;
import top.javahai.confucius.service.cms.service.AdService;

import java.util.List;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/23 - 15:28
 **/
@Api(tags = "广告推荐")
@RestController
@RequestMapping("/api/cms/ads")
public class ApiAdController {
    @Autowired
    private AdService adService;

    @ApiOperation("根据推荐位id显示广告推荐")
    @GetMapping("/{adTypeId}")
    public ResultVO<List<Ad>> listByAdTypeId(@ApiParam(value = "推荐位id", required = true) @PathVariable String adTypeId) {

        List<Ad> ads = adService.selectByAdTypeId(adTypeId);
        return new ResultVO<List<Ad>>().success().data(ads);
    }
}
