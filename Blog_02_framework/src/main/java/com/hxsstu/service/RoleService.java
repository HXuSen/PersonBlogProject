package com.hxsstu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.RoleAddDto;
import com.hxsstu.domain.dto.RoleListDto;
import com.hxsstu.domain.dto.RoleStatusDto;
import com.hxsstu.domain.dto.RoleUpdateDto;
import com.hxsstu.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-08-21 18:30:16
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    ResponseResult roleList(Integer pageNum, Integer pageSize, RoleListDto roleListDto);

    ResponseResult changeRoleStatus(RoleStatusDto roleStatusDto);

    ResponseResult addRole(RoleAddDto addDto);

    ResponseResult getRoleById(Long id);

    ResponseResult updateRole(RoleUpdateDto roleUpdateDto);

    ResponseResult deleteRoleById(Long id);

    ResponseResult listAllRole();


}

