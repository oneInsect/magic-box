package com.simplecode.filemgt.controller;

import com.simplecode.common.utils.SelfDefineResponse;
import com.simplecode.filemgt.entity.vo.Login;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/filemgt/user")
public class LoginController {
    @PostMapping("login")
    public SelfDefineResponse login(@RequestBody(required = true) Login login){
        return SelfDefineResponse.ok().data("token", "admin");
    }

    @GetMapping("info")
    public SelfDefineResponse info(){
        return SelfDefineResponse.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "http://5b0988e595225.cdn.sohucs.com/images/20190324/26b14ff8956b4661a456a7e6751ce085.jpeg");
    }
}
