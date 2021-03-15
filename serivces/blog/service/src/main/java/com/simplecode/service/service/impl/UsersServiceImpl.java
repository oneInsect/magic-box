package com.simplecode.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simplecode.service.entity.Users;
import com.simplecode.service.mapper.UsersMapper;
import com.simplecode.service.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public Users findByUsername(String username) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        return baseMapper.selectOne(queryWrapper);
    }

}
