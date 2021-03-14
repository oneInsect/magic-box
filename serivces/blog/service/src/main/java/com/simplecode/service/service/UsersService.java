package com.simplecode.service.service;

import com.simplecode.service.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
public interface UsersService extends IService<Users> {

    public Users findByUsername(String username);

}
