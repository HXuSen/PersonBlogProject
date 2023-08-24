package com.hxsstu.controller;

import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.*;
import com.hxsstu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-19:24
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseResult userList(Integer pageNum, Integer pageSize, UserDto userDto){
        return userService.userList(pageNum,pageSize,userDto);
    }

    @PostMapping
    public ResponseResult addUser(@RequestBody UserAddDto userDto){
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable("id")Long id){
        return userService.deleteUserById(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getUser(@PathVariable("id")Long id){
        return userService.getUserById(id);
    }

    @PutMapping
    public ResponseResult updateUser(@RequestBody UserUpdateDto userDto){
        return userService.updateUser(userDto);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody UserStatusDto userStatusDto){
        return userService.changeUserStatus(userStatusDto);
    }
}
