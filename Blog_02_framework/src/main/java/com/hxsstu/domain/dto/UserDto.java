package com.hxsstu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: UserDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-19:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userName;
    private String phonenumber;
    private String status;
}
