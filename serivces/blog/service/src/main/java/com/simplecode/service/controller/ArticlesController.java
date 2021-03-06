package com.simplecode.service.controller;


import com.simplecode.common.utils.SDResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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


}

