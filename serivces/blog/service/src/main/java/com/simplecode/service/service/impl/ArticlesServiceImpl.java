package com.simplecode.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simplecode.common.exception.GlobalException;
import com.simplecode.service.entity.Articles;
import com.simplecode.service.mapper.ArticlesMapper;
import com.simplecode.service.service.ArticlesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Articles> implements ArticlesService {

    private static final Logger log = LoggerFactory.getLogger(ArticlesServiceImpl.class);

    @Override
    public Articles findArticleById(String articleId) throws GlobalException {
        if (!articleId.isEmpty()){
            log.info("find article by Id" + articleId);
            return this.getById(articleId);
        }else{
            throw new GlobalException();
        }
    }

    @Override
    public boolean removeArticleById(String articleId) throws GlobalException {
        if (!articleId.isEmpty()){
            log.info("find article by Id" + articleId);
            return this.removeById(articleId);
        }else{
            throw new GlobalException();
        }
    }

    @Override
    public boolean updateArticle(Articles articles) {
        return this.saveOrUpdate(articles);
    }
}
