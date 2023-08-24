package com.hxsstu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: UserUpdateDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private String email;
    private Long id;
    private String nickName;
    private String sex;
    private String status;
    private String userName;
    private List<String> roleIds;
    private String phonenumber;
}
