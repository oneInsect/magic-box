package com.simplecode.service.controller;


import com.simplecode.common.utils.AESUtils;
import com.simplecode.common.utils.SDResponse;
import com.simplecode.service.config.JwtUtil;
import com.simplecode.service.entity.Users;
import com.simplecode.service.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
@Slf4j
@RestController
@RequestMapping("/service/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping("login")
    public SDResponse login (@RequestBody(required = true) Users users){
        String userName = users.getUserName();
        String userPassword = users.getUserPassword();
        Users userEntity = null;
        try {
            userEntity = usersService.findByUsername(userName);
        } catch (Exception e){
            log.error(e.getMessage());
            return SDResponse.error().message(e.getMessage());
        }
        if (!verifyPassword(userPassword, userEntity.getUserPassword())){
            return SDResponse.error().message("username or password incorrect!");
        }
        String token = JwtUtil.sign(userName, userEntity.getUserPassword(), userEntity.getUserId());
        return SDResponse.ok().data("Authorization", token).data("users", userEntity);

    }

    @PutMapping("user")
    public SDResponse register(@RequestBody(required = true) Users users){
        String userName = users.getUserName();
        String userPassword = users.getUserPassword();
        if (userName.isEmpty() || userPassword.isEmpty()){
            return SDResponse.error().message("username or password can not be empty");
        }
        users.setUserPassword(AESUtils.AESEncode(userPassword));
        try{
            usersService.save(users);}
        catch (Exception e){
            log.error(e.getMessage());
            return SDResponse.error().message(e.getMessage());
        }
        return SDResponse.ok().message("success");
    }


    private boolean verifyPassword(String userPassword, String encryptPassword){
        return Objects.equals(AESUtils.AESDecode(encryptPassword), userPassword);
    }

}

