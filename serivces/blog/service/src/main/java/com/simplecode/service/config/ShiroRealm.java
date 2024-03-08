package com.simplecode.service.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.simplecode.service.entity.*;
import com.simplecode.service.service.UserRoleRelationService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.yaml.snakeyaml.scanner.Constant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;

@Configuration
@MapperScan("com.simplecode.service.mapper")
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private UsersService usersService;

    @Resource
    private UserRoleRelationService userRoleRelationService;

    // 必须重写此方法，不然Shiro会报错
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 能进入这里说明用户已经通过验证了
        Users users = (Users) principalCollection.getPrimaryPrincipal();
        Long userId = users.getUserId();
        List<UserRoleRelation> UserRoleRelations = userRoleRelationService.findRolesByUserId(userId);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (UserRoleRelation UserRoleRelation : UserRoleRelations) {
            Integer roleId = UserRoleRelation.getRoleId();

//            simpleAuthorizationInfo.addRole(role.getRoleName());
//            for (Permission permission : role.getPermissions()) {
//                simpleAuthorizationInfo.addStringPermission(permission.getPermissionName());
//            }
        }
        return simpleAuthorizationInfo;
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String username = JwtUtil.getUsername(token); //从token中获取username
        Integer userId = JwtUtil.getUserId(token);    //从token中获取userId

//         通过redis查看token是否过期
        HttpServletRequest request = getHttpServletRequest();
        String encryptTokenInRedis = redisUtil.get("Constant.RM_TOKEN_CACHE" + token + StringPool.UNDERSCORE);
        if (!token.equalsIgnoreCase(encryptTokenInRedis)) {
            throw new AuthenticationException("token已经过期");
        }

        // 如果找不到，说明已经失效
        if (StringUtils.isBlank(encryptTokenInRedis)) {
            throw new AuthenticationException("token已经过期");
        }

        if (StringUtils.isBlank(username)) {
            throw new AuthenticationException("token校验不通过");
        }

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
