package com.hxsstu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxsstu.domain.entity.RoleMenu;

import java.util.List;


/**
 * 角色和菜单关联表(RoleMenu)表服务接口
 *
 * @author makejava
 * @since 2023-08-22 18:48:41
 */
public interface RoleMenuService extends IService<RoleMenu> {

    List<String> selectMenuIdByUserId(Long id);

    void removeByUserId(Long id);
}

