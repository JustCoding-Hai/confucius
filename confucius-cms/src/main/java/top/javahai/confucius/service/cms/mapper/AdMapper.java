package top.javahai.confucius.service.cms.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.javahai.confucius.service.cms.entity.Ad;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.javahai.confucius.service.cms.entity.vo.AdVO;

import java.util.List;

/**
 * <p>
 * 广告推荐 Mapper 接口
 * </p>
 *
 * @author Ethan
 * @since 2021-01-23
 */
public interface AdMapper extends BaseMapper<Ad> {

    /**
     * 分页查询
     * @param pageParam
     * @param queryWrapper
     * @return
     */
    List<AdVO> selectPageByQueryWrapper(Page<AdVO> pageParam,
                                        @Param(Constants.WRAPPER) QueryWrapper<AdVO> queryWrapper);
}
