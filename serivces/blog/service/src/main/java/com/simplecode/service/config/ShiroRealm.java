package com.simplecode.service.config;

import com.simplecode.service.entity.Permission;
import com.simplecode.service.entity.Role;
import com.simplecode.service.entity.RolePermissionRelation;
import com.simplecode.service.entity.Users;
import com.simplecode.service.service.UsersService;
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

@Configuration
@MapperScan("com.simplecode.service.mapper")
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private UsersService usersService;

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
        // 获取用户输入的账户
        String username = (String) authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getPrincipal());
//         通过username从数据库中查找 Users 对象
//         实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        Users userInfo  = usersService.findByUsername(username);
        if (null == userInfo ) {
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userInfo , // 用户名
                userInfo.getUserPassword(), // 密码
                ByteSource.Util.bytes(userInfo.getSalt()), // salt=username+salt
                getName() // realm name
        );
        return simpleAuthenticationInfo;
    }
}
