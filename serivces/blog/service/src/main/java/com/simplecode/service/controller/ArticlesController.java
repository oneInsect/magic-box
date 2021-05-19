package com.simplecode.service.controller;


import com.simplecode.common.exception.GlobalException;
import com.simplecode.common.utils.SDResponse;
import com.simplecode.service.entity.Articles;
import com.simplecode.service.service.ArticlesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
@RestController
@RequestMapping("/service/articles")
public class ArticlesController {
    @RequiresPermissions("bus:careerTalk:query")
    public SDResponse hello(){
        return SDResponse.ok();
    }

    @Autowired
    ArticlesService articlesService;

    @PostMapping("article")
    public SDResponse addArticle(@RequestBody(required = true) Articles articles){
        articlesService.save(articles);
        return SDResponse.ok();
    }

    @GetMapping("{articleId}")
    public SDResponse addArticle(@PathVariable String articleId) throws GlobalException {
        articlesService.findArticleById(articleId);
        return SDResponse.ok();
    }





}

