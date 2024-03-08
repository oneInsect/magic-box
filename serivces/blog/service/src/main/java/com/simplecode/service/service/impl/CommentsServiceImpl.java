package com.simplecode.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simplecode.common.exception.GlobalException;
import com.simplecode.service.entity.Comments;
import com.simplecode.service.mapper.CommentsMapper;
import com.simplecode.service.service.CommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

    @Override
    public List<Comments> findCommentsByArticleId(String articleId) {

        QueryWrapper<Comments> commentsQueryWrapper = new QueryWrapper<>();
        commentsQueryWrapper.eq("article_id", articleId);
        return this.list(commentsQueryWrapper);
    }

    @Override
    public Boolean addComment(Comments comments) {
        return this.save(comments);
    }

    @Override
    public Boolean removeComment(String commentId) {
        return this.removeById(commentId);
    }
}
