package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hxsstu.constants.SystemConstants;
import com.hxsstu.domain.entity.LoginUser;
import com.hxsstu.domain.entity.User;
import com.hxsstu.mapper.MenuMapper;
import com.hxsstu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * ClassName: UserDetailsServiceImpl
 * Package: com.hxsstu.service.impl
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/20-16:27
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否查到用户
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        //返回用户信息
        //询权限信息封装 后台用户才需要
        if (user.getType().equals(SystemConstants.ADMIN)){
            List<String> list = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user,list);
        }

        return new LoginUser(user,null);
    }
}
