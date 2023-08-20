package com.hxsstu.service;

import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.entity.User;

/**
 * ClassName: BlogLoginService
 * Package: com.hxsstu.service
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/20-16:22
 */
public interface BlogLoginService {
    ResponseResult login(User user);
}
