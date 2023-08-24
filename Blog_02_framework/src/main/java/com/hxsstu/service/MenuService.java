package com.hxsstu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.MenuSelectDto;
import com.hxsstu.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-08-21 18:26:53
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeById(Long userId);

    ResponseResult menuList(MenuSelectDto menuSelectDto);

    ResponseResult getMenuById(Long id);

    ResponseResult updateMenu(Menu menu);

    ResponseResult deleteMenuById(Long id);

    ResponseResult treeSelect();

    ResponseResult roleMenuTreeselect(Long id);
}

