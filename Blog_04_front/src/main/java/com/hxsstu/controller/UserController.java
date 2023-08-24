package com.hxsstu.controller;

import com.hxsstu.annotation.SystemLog;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.entity.User;
import com.hxsstu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/20-19:23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}
