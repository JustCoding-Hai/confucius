package top.javahai.confucius.service.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.javahai.confucius.service.cms.entity.Ad;
import com.baomidou.mybatisplus.extension.service.IService;
import top.javahai.confucius.service.cms.entity.vo.AdVO;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-23
 */
public interface AdService extends IService<Ad> {

    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    IPage<AdVO> selectPage(Long page, Long limit);

    /**
     * 远程调用oss删除广告图片
     * @param id
     * @return
     */
    boolean removeAdImageById(String id);

    /**
     * 根据广告位id查询广告
     * @param adTypeId
     * @return
     */
    List<Ad> selectByAdTypeId(String adTypeId);
}
