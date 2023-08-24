package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.domain.entity.RoleMenu;
import com.hxsstu.mapper.RoleMenuMapper;
import com.hxsstu.service.RoleMenuService;
import com.hxsstu.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2023-08-22 18:48:41
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public List<String> selectMenuIdByUserId(Long id) {
        List<RoleMenu> list;
        if (id.equals(1L)){
           list = list();
        }else {
            LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(RoleMenu::getRoleId, id);
            list = list(queryWrapper);
        }
        List<String> menuIds = list.stream()
                .map(rm -> rm.getMenuId().toString())
                .collect(Collectors.toList());
        System.out.println(menuIds);
        return menuIds;
    }

    @Override
    public void removeByUserId(Long id) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(RoleMenu::getRoleId,id);
        remove(queryWrapper);
    }
}

