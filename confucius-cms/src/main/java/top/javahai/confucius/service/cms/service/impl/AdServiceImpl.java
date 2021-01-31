package top.javahai.confucius.service.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import top.javahai.confucius.frame.common.helper.StringHelper;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.cms.entity.Ad;
import top.javahai.confucius.service.cms.entity.vo.AdVO;
import top.javahai.confucius.service.cms.mapper.AdMapper;
import top.javahai.confucius.service.cms.remote.OssClient;
import top.javahai.confucius.service.cms.service.AdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-23
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

    @Autowired
    OssClient ossClient;

    @Override
    public IPage<AdVO> selectPage(Long page, Long limit) {

        QueryWrapper<AdVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("a.type_id", "a.sort");

        Page<AdVO> pageParam = new Page<>(page, limit);

        List<AdVO> records = baseMapper.selectPageByQueryWrapper(pageParam, queryWrapper);
        pageParam.setRecords(records);
        return pageParam;
    }

    @Override
    public boolean removeAdImageById(String id) {
        Ad ad = baseMapper.selectById(id);
        if(ad != null) {
            String imagesUrl = ad.getImageUrl();
            if(!StringHelper.isEmpty(imagesUrl)){
                //删除图片
                ResultVO resultVO = ossClient.removeFile(imagesUrl);
                return resultVO.getSuccess();
            }
        }
        return false;
    }

    @Cacheable(value = "index",key = "'selectByAdTypeId'")
    @Override
    public List<Ad> selectByAdTypeId(String adTypeId) {
        QueryWrapper<Ad> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort", "id");
        queryWrapper.eq("type_id", adTypeId);
        return baseMapper.selectList(queryWrapper);
    }
}
