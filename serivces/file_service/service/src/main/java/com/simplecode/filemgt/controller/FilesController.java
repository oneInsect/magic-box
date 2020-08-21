package com.simplecode.filemgt.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.simplecode.common.utils.SelfDefineResponse;
import com.simplecode.filemgt.entity.Files;
import com.simplecode.filemgt.entity.vo.FileQuery;
import com.simplecode.filemgt.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin
@RestController
@RequestMapping("/filemgt/files")
public class FilesController {

    @Autowired
    private FilesService filesService;

    @GetMapping("all")
    public SelfDefineResponse getAll(){
        List<Files> files = filesService.list(null);
        return SelfDefineResponse.ok().data("files", files);
    }

    @GetMapping("page/{current}/{limit}")
    public SelfDefineResponse pageWithoutCondition(@PathVariable Long current, @PathVariable Long limit){
        Page<Files> page = new Page<>(current, limit);
        filesService.page(page, null);
        long total = page.getTotal();
        List<Files> records = page.getRecords();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("records", records);
        return SelfDefineResponse.ok().data(map);
    }

    @PostMapping("page/{current}/{limit}")
    public SelfDefineResponse pageWithCondition(@PathVariable String current,
                                                @PathVariable String limit,
                                                @RequestBody(required = true) FileQuery fileQuery){

        return SelfDefineResponse.ok();
    }

}

