package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.domain.entity.UserRole;
import com.hxsstu.mapper.UserRoleMapper;
import com.hxsstu.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2023-08-22 19:46:52
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public void removeByUserId(Long id) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(UserRole::getUserId,id);
        remove(queryWrapper);
    }

    @Override
    public List<String> selectByUserId(Long id) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(UserRole::getUserId,id);
        List<String> roleIds = list(queryWrapper).stream()
                .map(userRole -> userRole.getRoleId().toString())
                .collect(Collectors.toList());
        return roleIds;
    }
}

