package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.constants.SystemConstants;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.MenuSelectDto;
import com.hxsstu.domain.entity.Menu;
import com.hxsstu.domain.vo.MenuListVo;
import com.hxsstu.domain.vo.RoleMenuLoadVo;
import com.hxsstu.domain.vo.TreeSelectVo;
import com.hxsstu.enums.AppHttpCodeEnum;
import com.hxsstu.mapper.MenuMapper;
import com.hxsstu.service.MenuService;
import com.hxsstu.service.RoleMenuService;
import com.hxsstu.utils.BeanCopyUtils;
import com.hxsstu.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-08-21 18:26:54
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<String> selectPermsByUserId(Long id) {
        //admin
        if (SecurityUtils.isAdmin()){
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.in(Menu::getMenuType, SystemConstants.MENU_TYPE_MENU,SystemConstants.MENU_TYPE_BUTTON);
            queryWrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(queryWrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }

        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeById(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus;
        //isAdmin?allMenu:nowUserHolderMenu
        if (SecurityUtils.isAdmin()){
            menus = menuMapper.selectAllRouterMenu();
        }else{
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        //构建MenuTree
        List<Menu> menuTree = buildMenuTree(menus,0L);
        return menuTree;
    }

    @Override
    public ResponseResult menuList(MenuSelectDto menuSelectDto) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(menuSelectDto.getMenuName()),Menu::getMenuName,menuSelectDto.getMenuName());
        queryWrapper.eq(StringUtils.hasText(menuSelectDto.getStatus()),Menu::getStatus,menuSelectDto.getStatus());
        queryWrapper.orderByAsc(Menu::getParentId,Menu::getOrderNum);

        List<Menu> menus = list(queryWrapper);
        List<MenuListVo> menuListVos = BeanCopyUtils.copyBeanList(menus, MenuListVo.class);

        return ResponseResult.okResult(menuListVos);
    }

    @Override
    public ResponseResult getMenuById(Long id) {
        Menu menu = getById(id);
        MenuListVo menuListVo = BeanCopyUtils.copyBean(menu, MenuListVo.class);
        return ResponseResult.okResult(menuListVo);
    }

    @Override
    public ResponseResult updateMenu(Menu menu) {
        if (menu.getId().equals(menu.getParentId())){
            return ResponseResult.errorResult(AppHttpCodeEnum.MENU_UPDATE_ERROR.getCode(),"修改菜单'" + menu.getMenuName() + "'失败,上级菜单不能选择自己");
        }
        updateById(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenuById(Long id) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Menu::getParentId,id);

        if (!list(queryWrapper).isEmpty()){
            return ResponseResult.errorResult(AppHttpCodeEnum.MENU_DELETE_ERROR.getCode(),"存在子菜单,不允许删除");
        }
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult treeSelect() {
        MenuMapper menuMapper = getBaseMapper();
        List<TreeSelectVo> treeSelectVos = menuMapper.selectMenu();
        List<TreeSelectVo> menuTree = buildTreeSelect(treeSelectVos,0L);
        return ResponseResult.okResult(menuTree);
    }

    @Override
    public ResponseResult roleMenuTreeselect(Long id) {
        List<TreeSelectVo> menuTree = (List<TreeSelectVo>) treeSelect().getData();
        List<String> menuIds = roleMenuService.selectMenuIdByUserId(id);
        RoleMenuLoadVo roleMenuLoadVo = new RoleMenuLoadVo(menuTree,menuIds);
        return ResponseResult.okResult(roleMenuLoadVo);
    }


    private List<Menu> buildMenuTree(List<Menu> menus, long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getChildren(m,menus)))
                .collect(Collectors.toList());
        return childrenList;
    }

    private List<TreeSelectVo> buildTreeSelect(List<TreeSelectVo> menus, long parentId) {
        List<TreeSelectVo> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getTreeSelectChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    private List<TreeSelectVo> getTreeSelectChildren(TreeSelectVo menu, List<TreeSelectVo> menus) {
        List<TreeSelectVo> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getTreeSelectChildren(m,menus)))
                .collect(Collectors.toList());
        return childrenList;
    }

}

