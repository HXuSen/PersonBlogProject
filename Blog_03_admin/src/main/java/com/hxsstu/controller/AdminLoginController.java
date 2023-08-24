package com.hxsstu.controller;

import com.hxsstu.annotation.SystemLog;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.entity.LoginUser;
import com.hxsstu.domain.entity.Menu;
import com.hxsstu.domain.entity.User;
import com.hxsstu.domain.vo.AdminUserInfoVo;
import com.hxsstu.domain.vo.RoutersVo;
import com.hxsstu.domain.vo.UserInfoVo;
import com.hxsstu.enums.AppHttpCodeEnum;
import com.hxsstu.exception.SystemException;
import com.hxsstu.service.AdminLoginService;
import com.hxsstu.service.RoleService;
import com.hxsstu.utils.BeanCopyUtils;
import com.hxsstu.utils.RedisCache;
import com.hxsstu.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.hxsstu.service.MenuService;

import java.util.List;

/**
 * ClassName: AdminLoginControlle
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/21-16:35
 */
@RestController
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }

    @SystemLog(businessName = "获取用户权限")
    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        //封装UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        //封装返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @SystemLog(businessName = "获取路由")
    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        Long userId = SecurityUtils.getUserId();
        List<Menu> menus = menuService.selectRouterMenuTreeById(userId);

        return ResponseResult.okResult(new RoutersVo(menus));
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){

        return adminLoginService.logout();
    }
}
