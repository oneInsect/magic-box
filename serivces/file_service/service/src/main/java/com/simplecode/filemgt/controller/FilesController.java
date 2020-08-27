package com.simplecode.filemgt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.simplecode.common.utils.FileUtil;
import com.simplecode.common.utils.SelfDefineResponse;
import com.simplecode.filemgt.entity.Files;
import com.simplecode.filemgt.entity.vo.FileQuery;
import com.simplecode.filemgt.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
    public SelfDefineResponse pageWithCondition(@PathVariable Long current,
                                                @PathVariable Long limit,
                                                @RequestBody(required = true) FileQuery fileQuery){
        Page<Files> page = new Page<>(current, limit);
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        String name = fileQuery.getName();
        String begin = fileQuery.getBegin();
        String end = fileQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.like("created_time", begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.like("modified_time", end);
        }
        queryWrapper.orderByDesc("modified_time");
        filesService.page(page, queryWrapper);
        long total = page.getTotal();
        List<Files> records = page.getRecords();
        return SelfDefineResponse.ok().data("total", total).data("records", records);
    }
    @PostMapping("file")
    public SelfDefineResponse addFile(MultipartFile uploadFile, @RequestBody(required = true) Files files){
        boolean saved = filesService.save(files);
        String cateId = files.getCateId();
        if (saved){
            FileUtil.saveFile(cateId, uploadFile);
        }
        return SelfDefineResponse.ok();
    }

    @DeleteMapping("{fileId}")
    public SelfDefineResponse deleteFile(@PathVariable String fileId){
        boolean removed = filesService.removeById(fileId);
        return SelfDefineResponse.ok();
    }

    @PutMapping("{fileId}")
    public SelfDefineResponse modifyFile(@PathVariable String fileId, MultipartFile multipartFile, @RequestBody(required = true) Files files){

        return SelfDefineResponse.ok();
    }

    @GetMapping("{fileId}")
    public SelfDefineResponse downloadFile(@PathVariable String fileId){
        Files file = filesService.getById(fileId);
        String path = file.getPath();
        String fileName = file.getName();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端解析
        String type = new MimetypesFileTypeMap().getContentType(fileName);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        assert response != null;
        response.setHeader("Content-type",type);
        // 设置编码
        String fileNameEncode = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + fileNameEncode);
        try {
            FileUtil.download(path, response);
        }catch (IOException e){
            return SelfDefineResponse.error().message("get local file error");
        }
        return SelfDefineResponse.ok();
    }
}

