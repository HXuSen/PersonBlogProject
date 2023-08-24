package com.hxsstu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxsstu.domain.entity.Menu;
import com.hxsstu.domain.vo.TreeSelectVo;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-21 18:26:54
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<TreeSelectVo> selectMenu();
}

