package com.hxsstu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxsstu.domain.entity.UserRole;

import java.util.List;


/**
 * 用户和角色关联表(UserRole)表服务接口
 *
 * @author makejava
 * @since 2023-08-22 19:46:52
 */
public interface UserRoleService extends IService<UserRole> {

    void removeByUserId(Long id);

    List<String> selectByUserId(Long id);
}

