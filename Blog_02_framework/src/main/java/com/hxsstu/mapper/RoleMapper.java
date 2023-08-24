package com.hxsstu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxsstu.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-21 18:30:17
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long userId);
}

