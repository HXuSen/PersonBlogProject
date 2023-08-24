package com.hxsstu.service.impl;

import com.hxsstu.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: PermissionServiceImpl
 * Package: com.hxsstu.service.impl
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-15:00
 */
@Service("ps")
public class PermissionServiceImpl {

    public boolean hasPermission(String permission){

        if (SecurityUtils.isAdmin()){
            return true;
        }
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}
