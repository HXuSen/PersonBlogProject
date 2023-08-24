package com.hxsstu.service.impl;

import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.entity.LoginUser;
import com.hxsstu.domain.entity.User;
import com.hxsstu.domain.vo.BlogUserLoginVo;
import com.hxsstu.domain.vo.UserInfoVo;
import com.hxsstu.service.AdminLoginService;
import com.hxsstu.service.BlogLoginService;
import com.hxsstu.utils.BeanCopyUtils;
import com.hxsstu.utils.JwtUtil;
import com.hxsstu.utils.RedisCache;
import com.hxsstu.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName: BlogLoginServiceImpl
 * Package: com.hxsstu.service.impl
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/20-16:22
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("login:" + userId,loginUser);
        //把token封装返回
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        Long userId = SecurityUtils.getUserId();
        redisCache.deleteObject("login:" + userId);
        return ResponseResult.okResult();
    }
}
