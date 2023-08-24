package com.hxsstu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: UserUpdateVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-20:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateVo {
    private String email;
    private Long id;
    private String nickName;
    private String sex;
    private String status;
    private String userName;
    private String phonenumber;
}
