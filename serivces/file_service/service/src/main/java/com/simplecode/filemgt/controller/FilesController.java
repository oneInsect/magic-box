package com.simplecode.filemgt.controller;


import com.simplecode.filemgt.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin
@RestController
@RequestMapping("/filemgt/files")
public class FilesController {

    @Autowired
    private FilesService filesService;


}

