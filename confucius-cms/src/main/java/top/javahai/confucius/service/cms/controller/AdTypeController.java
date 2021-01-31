package top.javahai.confucius.service.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.common.result.ResultPage;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.cms.entity.AdType;
import top.javahai.confucius.service.cms.service.AdTypeService;

import java.util.List;

/**
 * <p>
 * 推荐位 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2021-01-23
 */
@Api(value = "AdTypeController",tags = "推荐位管理")
@RestController
@Slf4j
@RequestMapping("/cms/ad-types")
public class AdTypeController {
    @Autowired
    private AdTypeService adTypeService;

    @ApiOperation("所有推荐类别列表")
    @GetMapping
    public ResultVO<List<AdType>> listAll() {
        List<AdType> list = adTypeService.list();
        return new ResultVO<List<AdType>>().success().data(list);
    }

    @ApiOperation("推荐类别分页列表")
    @GetMapping("/page-list")
    public ResultVO<ResultPage> listPage(@ApiParam(value = "当前页码", required = true) @RequestParam Long page,
                                         @ApiParam(value = "每页记录数", required = true) @RequestParam Long limit) {

        Page<AdType> pageParam = new Page<>(page, limit);
        IPage<AdType> pageModel = adTypeService.page(pageParam);
        List<AdType> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        ResultPage resultPage = new ResultPage(total,records);
        return new ResultVO<ResultPage>().success().data(resultPage);
    }

    @ApiOperation(value = "根据ID删除推荐类别")
    @DeleteMapping("/{id}")
    public ResultVO removeById(@ApiParam(value = "推荐类别ID", required = true) @PathVariable String id) {

        boolean result = adTypeService.removeById(id);
        if (result) {
            return ResultVO.ok().message("删除成功");
        }
        return ResultVO.error().message("数据不存在");
    }

    @ApiOperation("新增推荐类别")
    @PostMapping
    public ResultVO save(@ApiParam(value = "推荐类别对象", required = true) @RequestBody AdType adType) {

        boolean result = adTypeService.save(adType);
        if (result) {
            return ResultVO.ok().message("保存成功");
        }
        return ResultVO.error().message("保存失败");
    }

    @ApiOperation("更新推荐类别")
    @PutMapping
    public ResultVO updateById(@ApiParam(value = "讲师推荐类别", required = true) @RequestBody AdType adType) {
        boolean result = adTypeService.updateById(adType);
        if (result) {
            return ResultVO.ok().message("修改成功");
        }
       return ResultVO.error().message("数据不存在");
    }

    @ApiOperation("根据id获取推荐类别信息")
    @GetMapping("/{id}")
    public ResultVO<AdType> getById(@ApiParam(value = "推荐类别ID", required = true) @PathVariable String id) {
        AdType adType = adTypeService.getById(id);
        if (adType != null) {
            return new ResultVO<AdType>().success().data(adType);
        }
        return new ResultVO<AdType>().fail().message("数据不存在");
    }
}

