package com.hxsstu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.UserAddDto;
import com.hxsstu.domain.dto.UserDto;
import com.hxsstu.domain.dto.UserStatusDto;
import com.hxsstu.domain.dto.UserUpdateDto;
import com.hxsstu.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-08-20 18:39:45
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult userList(Integer pageNum, Integer pageSize, UserDto userDto);

    ResponseResult addUser(UserAddDto userDto);

    ResponseResult deleteUserById(Long id);

    ResponseResult getUserById(Long id);

    ResponseResult updateUser(UserUpdateDto userDto);

    ResponseResult changeUserStatus(UserStatusDto userStatusDto);
}

