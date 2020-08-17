package com.simplecode.filemgt.controller;


import com.simplecode.filemgt.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * categories 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-08-16
 */
@RestController
@CrossOrigin
@RequestMapping("/filemgt/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

}

