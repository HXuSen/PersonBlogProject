package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.constants.SystemConstants;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.RoleAddDto;
import com.hxsstu.domain.dto.RoleListDto;
import com.hxsstu.domain.dto.RoleStatusDto;
import com.hxsstu.domain.dto.RoleUpdateDto;
import com.hxsstu.domain.entity.Role;
import com.hxsstu.domain.entity.RoleMenu;
import com.hxsstu.domain.vo.PageVo;
import com.hxsstu.domain.vo.RoleAllListVo;
import com.hxsstu.domain.vo.RoleUpdateVo1;
import com.hxsstu.domain.vo.RoleVo;
import com.hxsstu.mapper.RoleMapper;
import com.hxsstu.mapper.RoleMenuMapper;
import com.hxsstu.service.RoleMenuService;
import com.hxsstu.service.RoleService;
import com.hxsstu.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-08-21 18:30:17
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是admin 如果是返回集合中只需有admin
        if (id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult roleList(Integer pageNum, Integer pageSize, RoleListDto roleListDto) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.hasText(roleListDto.getRoleName()),Role::getRoleName,roleListDto.getRoleName());
        queryWrapper.eq(StringUtils.hasText(roleListDto.getStatus()),Role::getStatus,roleListDto.getStatus());
        queryWrapper.orderByAsc(Role::getRoleSort);

        Page<Role> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);
        List<RoleVo> roleVos = BeanCopyUtils.copyBeanList(page.getRecords(), RoleVo.class);
        PageVo pageVo = new PageVo(roleVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult changeRoleStatus(RoleStatusDto roleStatusDto) {
        Role role = new Role();
        role.setId(roleStatusDto.getRoleId());
        role.setStatus(roleStatusDto.getStatus());
        updateById(role);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult addRole(RoleAddDto addDto) {
        Role role = BeanCopyUtils.copyBean(addDto, Role.class);
        save(role);
        List<RoleMenu> roleMenus = addDto.getMenuIds().stream()
                .map(id -> new RoleMenu(role.getId(), id))
                .collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenus);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getRoleById(Long id) {
        Role role = getById(id);
        RoleUpdateVo1 roleUpdateVo1 = BeanCopyUtils.copyBean(role, RoleUpdateVo1.class);
        return ResponseResult.okResult(roleUpdateVo1);
    }

    @Override
    public ResponseResult updateRole(RoleUpdateDto roleUpdateDto) {
        Role role = BeanCopyUtils.copyBean(roleUpdateDto, Role.class);
        updateById(role);
        roleMenuService.removeByUserId(role.getId());
        List<RoleMenu> roleMenus = roleUpdateDto.getMenuIds().stream()
                .map(id -> new RoleMenu(role.getId(), Long.parseLong(id)))
                .collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenus);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteRoleById(Long id) {
        removeById(id);
        roleMenuService.removeByUserId(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult listAllRole() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getStatus, SystemConstants.ROLE_STATUS_NORMAL);
        List<Role> list = list(queryWrapper);
        List<RoleAllListVo> roleAllListVos = BeanCopyUtils.copyBeanList(list, RoleAllListVo.class);
        return ResponseResult.okResult(roleAllListVos);
    }

}

