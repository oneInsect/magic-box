package com.simplecode.filemgt.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.simplecode.common.utils.SelfDefineResponse;
import com.simplecode.filemgt.entity.Accesskey;
import com.simplecode.filemgt.entity.Categories;
import com.simplecode.filemgt.service.AccesskeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * categories 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-08-17
 */
@RestController
@CrossOrigin
@RequestMapping("/filemgt/accesskey")
public class AccesskeyController {

    @Autowired
    private AccesskeyService accesskeyService;

    @GetMapping("all")
    public SelfDefineResponse getAllAK(){
        List<Accesskey> akList = accesskeyService.list(null);
        return SelfDefineResponse.ok().data("data", akList);
    }

    @PostMapping("ak")
    public SelfDefineResponse genAK(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Accesskey accesskey = new Accesskey();
        accesskey.setAccesskey(uuid);
        // get ak Id?
        boolean res = accesskeyService.save(accesskey);
        return SelfDefineResponse.ok().data("accesskey", uuid);
    }

    @DeleteMapping("{AKId}")
    public SelfDefineResponse deleteAK(@PathVariable String AKId){
        Boolean isDeleted = accesskeyService.removeById(AKId);
        return SelfDefineResponse.ok();
    }

    @GetMapping("page/{current}/{limit}")
    public SelfDefineResponse getAKByPage(@PathVariable long limit, @PathVariable long current){
        Page<Accesskey> page = new Page<>(current, limit);
        accesskeyService.page(page, null);
        long total = page.getTotal();
        List<Accesskey> records = page.getRecords();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("records", records);
        return SelfDefineResponse.ok().data(map);
    }



}

