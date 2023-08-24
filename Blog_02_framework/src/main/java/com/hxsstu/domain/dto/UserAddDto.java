package com.hxsstu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: UserAddDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-19:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDto {
    private String userName;
    private String nickName;
    private String password;
    private String phonenumber;
    private String email;
    private String sex;
    private String status;
    private List<String> roleIds;
}
