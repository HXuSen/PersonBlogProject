package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.UserAddDto;
import com.hxsstu.domain.dto.UserDto;
import com.hxsstu.domain.dto.UserStatusDto;
import com.hxsstu.domain.dto.UserUpdateDto;
import com.hxsstu.domain.entity.Role;
import com.hxsstu.domain.entity.User;
import com.hxsstu.domain.entity.UserRole;
import com.hxsstu.domain.vo.*;
import com.hxsstu.enums.AppHttpCodeEnum;
import com.hxsstu.exception.SystemException;
import com.hxsstu.mapper.UserMapper;
import com.hxsstu.service.RoleService;
import com.hxsstu.service.UserRoleService;
import com.hxsstu.service.UserService;
import com.hxsstu.utils.BeanCopyUtils;
import com.hxsstu.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-08-20 18:39:45
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult userInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装成userVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user,UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        //对数据进行非空判断
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        //对数据进行是否存在的判断
        if (userNameExists(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (emailExists(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //存入数据库
        save(user);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult userList(Integer pageNum, Integer pageSize, UserDto userDto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(userDto.getUserName()),User::getUserName,userDto.getUserName());
        queryWrapper.eq(StringUtils.hasText(userDto.getPhonenumber()),User::getPhonenumber,userDto.getPhonenumber());
        queryWrapper.eq(StringUtils.hasText(userDto.getStatus()),User::getStatus,userDto.getStatus());

        Page<User> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        Page<User> userPage = page(page, queryWrapper);
        List<UserVo> userVos = BeanCopyUtils.copyBeanList(userPage.getRecords(), UserVo.class);
        PageVo pageVo = new PageVo(userVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addUser(UserAddDto userDto) {
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        register(user);
        List<UserRole> userRoles = userDto.getRoleIds().stream()
                .map(roleId -> new UserRole(user.getId(), Long.parseLong(roleId)))
                .collect(Collectors.toList());
        userRoleService.saveBatch(userRoles);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteUserById(Long id) {
        removeById(id);
        userRoleService.removeByUserId(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUserById(Long id) {
        User user = getById(id);
        UserUpdateVo userUpdateVo = BeanCopyUtils.copyBean(user, UserUpdateVo.class);
        List<String> roleIds = userRoleService.selectByUserId(id);
        List<Role> roles = (List<Role>) roleService.listAllRole().getData();
        return ResponseResult.okResult(new UserUpdateVo2(userUpdateVo,roleIds,roles));
    }

    @Override
    public ResponseResult updateUser(UserUpdateDto userDto) {
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        updateById(user);
        userRoleService.removeByUserId(user.getId());
        List<UserRole> userRoles = userDto.getRoleIds().stream()
                .map(roleId -> new UserRole(user.getId(), Long.parseLong(roleId)))
                .collect(Collectors.toList());
        userRoleService.saveBatch(userRoles);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult changeUserStatus(UserStatusDto userStatusDto) {
        User user = new User();
        user.setId(userStatusDto.getUserId());
        user.setStatus(userStatusDto.getStatus());
        updateById(user);
        return ResponseResult.okResult();
    }

    private boolean emailExists(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getEmail,email);
        return count(queryWrapper) > 0;
    }

    private boolean userNameExists(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUserName,userName);
        return count(queryWrapper) > 0;
    }
}

