package top.javahai.confucius.service.education.service.impl;

import top.javahai.confucius.service.education.entity.Comment;
import top.javahai.confucius.service.education.mapper.CommentMapper;
import top.javahai.confucius.service.education.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
