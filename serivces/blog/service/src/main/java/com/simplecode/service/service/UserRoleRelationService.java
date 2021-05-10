package com.simplecode.service.service;

import com.simplecode.service.entity.Role;
import com.simplecode.service.entity.UserRoleRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
public interface UserRoleRelationService extends IService<UserRoleRelation> {
    public List<UserRoleRelation> findRolesByUserId(Long userId);

}
