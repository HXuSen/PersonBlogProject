package com.hxsstu.domain.vo;

import com.hxsstu.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: UserUpdateVo2
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-21:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateVo2 {
    private UserUpdateVo user;
    private List<String> roleIds;
    private List<Role> roles;
}
