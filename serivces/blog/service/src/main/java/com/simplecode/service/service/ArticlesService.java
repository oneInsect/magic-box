package com.simplecode.service.service;

import com.simplecode.common.exception.GlobalException;
import com.simplecode.service.entity.Articles;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
public interface ArticlesService extends IService<Articles> {

    public Articles findArticleById(String articleId) throws GlobalException;

    public boolean removeArticleById(String articleId) throws GlobalException;

    public boolean updateArticle(Articles articles);

}
