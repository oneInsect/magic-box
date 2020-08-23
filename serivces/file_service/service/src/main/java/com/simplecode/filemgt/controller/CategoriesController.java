package com.simplecode.filemgt.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.simplecode.common.utils.SelfDefineResponse;
import com.simplecode.filemgt.entity.Categories;
import com.simplecode.filemgt.entity.vo.CateQuery;
import com.simplecode.filemgt.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

    @GetMapping("all")
    public SelfDefineResponse getAll(){
        List<Categories> categories = categoriesService.list(null);
        return SelfDefineResponse.ok().data("categories", categories);
    }

    @GetMapping("page/{current}/{limit}")
    public SelfDefineResponse pageWithoutCondition(@PathVariable Long current, @PathVariable Long limit){
        Page<Categories> page = new Page<>(current, limit);
        categoriesService.page(page, null);
        long total = page.getTotal();
        List<Categories> records = page.getRecords();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("records", records);
        return SelfDefineResponse.ok().data(map);
    }

    @PostMapping("page/{current}/{limit}")
    public SelfDefineResponse pageWithCondition(@PathVariable Long current, @PathVariable Long limit,
                                                @RequestBody(required = true) CateQuery cateQuery){
        Page<Categories> page = new Page<>(current, limit);
        QueryWrapper<Categories> queryWrapper = new QueryWrapper<>();
        String name = cateQuery.getName();
        String begin = cateQuery.getBegin();
        String end = cateQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("created_time",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("modified_time",end);
        }
        queryWrapper.orderByDesc("modified_time");
        categoriesService.page(page, queryWrapper);

        long total = page.getTotal();
        List<Categories> records = page.getRecords();
        return SelfDefineResponse.ok().data("total", total).data("records", records);
    }

    @PostMapping("category")
    public SelfDefineResponse addCategory(@RequestBody(required = true) Categories categories){
        boolean saved = categoriesService.save(categories);
        return SelfDefineResponse.ok();
    }

    @DeleteMapping("{categoryId}")
    public SelfDefineResponse deleteCategory(@PathVariable String categoryId){
        boolean deleted = categoriesService.removeById(categoryId);
        return SelfDefineResponse.ok();
    }

    @PutMapping("{categoryId}")
    public SelfDefineResponse modifyCategory(@PathVariable String categoryId, @RequestBody(required = true) Categories categories){
        boolean updated = categoriesService.updateById(categories);
        return SelfDefineResponse.ok();
    }





}

