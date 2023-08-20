package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.domain.entity.User;
import com.hxsstu.mapper.UserMapper;
import com.hxsstu.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-08-20 18:39:45
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

