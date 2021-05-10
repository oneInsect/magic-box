package com.simplecode.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simplecode.service.entity.Role;
import com.simplecode.service.entity.UserRoleRelation;
import com.simplecode.service.entity.Users;
import com.simplecode.service.mapper.UserRoleRelationMapper;
import com.simplecode.service.service.UserRoleRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
@Service
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation> implements UserRoleRelationService {

    public List<UserRoleRelation> findRolesByUserId(Long userId){
        QueryWrapper<UserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return baseMapper.selectList(queryWrapper);
    }

}
