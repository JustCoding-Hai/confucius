package top.javahai.confucius.service.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.common.result.ResultPage;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.cms.entity.Ad;
import top.javahai.confucius.service.cms.entity.vo.AdVO;
import top.javahai.confucius.service.cms.service.AdService;

import java.util.List;

/**
 * <p>
 * 广告推荐 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2021-01-23
 */
@Api(value = "AdController",tags = "广告推荐管理")
@RestController
@RequestMapping("/cms/ads")
@Slf4j
public class AdController {
    @Autowired
    private AdService adService;

    @ApiOperation("新增推荐")
    @PostMapping
    public ResultVO save(@ApiParam(value = "推荐对象", required = true) @RequestBody Ad ad) {

        boolean result = adService.save(ad);
        if (result) {
            return ResultVO.ok().message("保存成功");
        }
        return ResultVO.error().message("保存失败");
    }

    @ApiOperation("更新推荐")
    @PutMapping
    public ResultVO updateById(@ApiParam(value = "讲师推荐", required = true) @RequestBody Ad ad) {
        //TODO 更新图片的时候也需要删除原本的图片
        boolean result = adService.updateById(ad);
        if (result) {
            return ResultVO.ok().message("修改成功");
        }
        return ResultVO.error().message("数据不存在");
    }

    @ApiOperation("根据id获取推荐信息")
    @GetMapping("/{id}")
    public ResultVO<Ad> getById(@ApiParam(value = "推荐ID", required = true) @PathVariable String id) {
        Ad ad = adService.getById(id);
        if (ad != null) {
            return new ResultVO<Ad>().success().data(ad);
        }
        return new ResultVO<Ad>().fail().message("数据不存在");
    }

    @ApiOperation("推荐分页列表")
    @GetMapping("/page-list")
    public ResultVO<ResultPage> listPage(@ApiParam(value = "当前页码", required = true) @RequestParam Long page,
                                       @ApiParam(value = "每页记录数", required = true) @RequestParam Long limit) {

        IPage<AdVO> pageModel = adService.selectPage(page, limit);
        List<AdVO> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        ResultPage resultPage = new ResultPage(total,records);
        return new ResultVO<ResultPage>().success().data(resultPage);
    }

    @ApiOperation(value = "根据ID删除推荐")
    @DeleteMapping("/{id}")
    public ResultVO removeById(@ApiParam(value = "推荐ID", required = true) @PathVariable String id) {

        //删除图片
        adService.removeAdImageById(id);

        //删除推荐
        boolean result = adService.removeById(id);
        if (result) {
            return ResultVO.ok().message("删除成功");
        }
        return ResultVO.error().message("数据不存在");
    }

}

