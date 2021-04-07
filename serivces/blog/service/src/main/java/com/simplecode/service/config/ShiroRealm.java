package com.simplecode.service.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.simplecode.service.entity.Permission;
import com.simplecode.service.entity.Role;
import com.simplecode.service.entity.RolePermissionRelation;
import com.simplecode.service.entity.Users;
import com.simplecode.service.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Configuration
@MapperScan("com.simplecode.service.mapper")
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private UsersService usersService;

    // 必须重写此方法，不然Shiro会报错
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 能进入这里说明用户已经通过验证了
        Users users = (Users) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : users.getRoles()) {
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permission.getPermissionName());
            }
        }
        return simpleAuthorizationInfo;
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
        String token = (String) authenticationToken.getCredentials();
//        String encryptToken = UofferUtil.encryptToken(token); //加密token
        String username = JwtUtil.getUsername(token); //从token中获取username
        Integer userId = JwtUtil.getUserId(token);    //从token中获取userId

        // 通过redis查看token是否过期
//        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
//        String ip = IPUtil.getIpAddr(request);
//        String encryptTokenInRedis = redisUtil.get(Constant.RM_TOKEN_CACHE + token + StringPool.UNDERSCORE + ip);
//        if (!token.equalsIgnoreCase(encryptTokenInRedis)) {
//            throw new AuthenticationException("token已经过期");
//        }
//
//        // 如果找不到，说明已经失效
//        if (StringUtils.isBlank(encryptTokenInRedis)) {
//            throw new AuthenticationException("token已经过期");
//        }
//
//        if (StringUtils.isBlank(username)) {
//            throw new AuthenticationException("token校验不通过");
//        }

        // 通过用户id查询用户信息
        Users user = usersService.getById(userId);

        if (user == null) {
            throw new AuthenticationException("用户名或密码错误");
        }
        if (!JwtUtil.verify(token, username, user.getUserPassword())) {
            throw new AuthenticationException("token校验不通过");
        }
        return new SimpleAuthenticationInfo(token, token, "febs_shiro_realm");
    }
}
