package com.simplecode.service.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.simplecode.common.utils.AESUtils;
import com.simplecode.common.utils.SDResponse;
import com.simplecode.service.config.JwtUtil;
import com.simplecode.service.config.RedisUtil;
import com.simplecode.service.entity.Users;
import com.simplecode.service.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Objects;

import static com.simplecode.service.config.MGTConstants.REDIS_SESSION_TIMEOUT;
import static com.simplecode.service.config.MGTConstants.TOKEN;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/service/users")
public class UsersController {
    @Autowired
    private RedisUtil redisUtil;

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
        redisUtil.set("Constant.RM_TOKEN_CACHE" + token + StringPool.UNDERSCORE, token, REDIS_SESSION_TIMEOUT);

        return SDResponse.ok().data(TOKEN, token).data("users", userEntity);

    }

    @GetMapping("info")
    public SDResponse info(@RequestParam(required = true) String token){
        Integer userId = JwtUtil.getUserId(token);
        Users user = usersService.findUserById(userId);
        return SDResponse.ok().data("users", user);

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

