package com.simplecode.service.service;

import com.simplecode.service.entity.Comments;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
public interface CommentsService extends IService<Comments> {

    public List<Comments> findCommentsByArticleId(String articleId);

    public Boolean addComment(Comments comments);

    public Boolean removeComment(String commentId);


}
