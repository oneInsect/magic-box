package com.simplecode.service.service.impl;

import com.simplecode.service.entity.Articles;
import com.simplecode.service.mapper.ArticlesMapper;
import com.simplecode.service.service.ArticlesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
