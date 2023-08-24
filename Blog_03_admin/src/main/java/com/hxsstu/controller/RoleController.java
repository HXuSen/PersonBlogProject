package com.hxsstu.controller;

import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.*;
import com.hxsstu.service.MenuService;
import com.hxsstu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: RoleController
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-17:58
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult roleList(Integer pageNum, Integer pageSize, RoleListDto roleListDto){
        return roleService.roleList(pageNum,pageSize,roleListDto);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleStatusDto roleStatusDto){
        return roleService.changeRoleStatus(roleStatusDto);
    }

    @PostMapping
    public ResponseResult addRole(@RequestBody RoleAddDto addDto){
        return roleService.addRole(addDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getRole(@PathVariable("id")Long id){
        return roleService.getRoleById(id);
    }

    @PutMapping
    public ResponseResult updateRole(@RequestBody RoleUpdateDto roleUpdateDto){
        return roleService.updateRole(roleUpdateDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteRole(@PathVariable("id")Long id){
        return roleService.deleteRoleById(id);
    }

    @GetMapping("/listAllRole")
    public ResponseResult listAllRole(){
        return roleService.listAllRole();
    }
}
