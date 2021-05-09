package com.simplecode.service.controller;

import com.simplecode.common.utils.SDResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/service")
public class HomeController {


    @GetMapping("index")
    public SDResponse index(){
        return SDResponse.ok();
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        log.error("------没有权限-------");
        return "403";
    }

}
