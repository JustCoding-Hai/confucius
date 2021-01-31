package top.javahai.confucius.service.portal.service.impl;

import top.javahai.confucius.service.portal.entity.Comment;
import top.javahai.confucius.service.portal.mapper.CommentMapper;
import top.javahai.confucius.service.portal.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
